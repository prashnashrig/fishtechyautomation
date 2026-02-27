package Fishtechy.Pages;

import jakarta.mail.*;
import jakarta.mail.search.FlagTerm;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailOtpReader {

    // Example: "\\b\\d{4,8}\\b" matches 4-8 digit OTPs
    private static final Pattern DEFAULT_OTP_PATTERN = Pattern.compile("\\b\\d{4,8}\\b");

    /**
     * Polls the inbox and returns the first OTP found in the latest unseen email.
     *
     * @param imapHost       IMAP host, e.g. "imap.gmail.com"
     * @param imapPort       IMAP port, e.g. "993"
     * @param username       email account username
     * @param password       email account password (or app password)
     * @param maxWaitSeconds how many seconds to poll before timing out
     * @return the OTP string found
     * @throws Exception if not found within timeout or on connection errors
     */
    public static String fetchOtpFromInbox(String imapHost,
                                           String imapPort,
                                           String username,
                                           String password,
                                           int maxWaitSeconds) throws Exception {
        return fetchOtpFromInbox(imapHost, imapPort, username, password, maxWaitSeconds, DEFAULT_OTP_PATTERN);
    }

    public static String fetchOtpFromInbox(String imapHost,
                                           String imapPort,
                                           String username,
                                           String password,
                                           int maxWaitSeconds,
                                           Pattern otpPattern) throws Exception {

        Properties props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.imap.host", imapHost);
        props.put("mail.imap.port", imapPort);
        props.put("mail.imap.timeout", "10000");
        props.put("mail.imap.connectiontimeout", "10000");

        long deadline = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(maxWaitSeconds);

        while (System.currentTimeMillis() < deadline) {
            Session session = Session.getInstance(props);
            Store store = null;
            Folder inbox = null;
            try {
                store = session.getStore("imap");
                store.connect(imapHost, Integer.parseInt(imapPort), username, password);

                inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_WRITE);

                // Search unseen messages (unread)
                Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

                // If none unread, optionally you can search recent messages:
                if (messages == null || messages.length == 0) {
                    // Sleep briefly and poll again
                    Thread.sleep(2000);
                    continue;
                }

                // Walk messages newest-first
                for (int i = messages.length - 1; i >= 0; i--) {
                    Message msg = messages[i];

                    String content = getTextFromMessage(msg);

                    // Try subject first
                    String subject = msg.getSubject();
                    if (subject != null) {
                        Matcher m = otpPattern.matcher(subject);
                        if (m.find()) {
                            // mark seen and return
                            msg.setFlag(Flags.Flag.SEEN, true);
                            return m.group();
                        }
                    }

                    // Try body
                    if (content != null) {
                        Matcher m = otpPattern.matcher(content);
                        if (m.find()) {
                            msg.setFlag(Flags.Flag.SEEN, true);
                            return m.group();
                        }
                    }

                    // Optionally mark as read or leave unread depending on your needs:
                    // msg.setFlag(Flags.Flag.SEEN, true);
                }

            } catch (AuthenticationFailedException ae) {
                throw new Exception("IMAP authentication failed. Check credentials/app password and IMAP settings.", ae);
            } finally {
                if (inbox != null && inbox.isOpen()) {
                    try { inbox.close(false); } catch (Exception ignored) {}
                }
                if (store != null) {
                    try { store.close(); } catch (Exception ignored) {}
                }
            }

            // Sleep before next poll
            Thread.sleep(2000);
        }

        throw new Exception("OTP not found within " + maxWaitSeconds + " seconds.");
    }

    // Utility to extract text from Message (handles multipart)
    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/*")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) message.getContent();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                Object partContent = bp.getContent();
                if (partContent instanceof String) {
                    sb.append((String) partContent).append('\n');
                } else if (partContent instanceof Multipart) {
                    sb.append(getTextFromMultipart((Multipart) partContent));
                }
            }
            return sb.toString();
        } else {
            Object content = message.getContent();
            return content == null ? "" : content.toString();
        }
    }

    private static String getTextFromMultipart(Multipart multipart) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart bp = multipart.getBodyPart(i);
            Object partContent = bp.getContent();
            if (partContent instanceof String) {
                sb.append((String) partContent).append('\n');
            } else if (partContent instanceof Multipart) {
                sb.append(getTextFromMultipart((Multipart) partContent));
            }
        }
        return sb.toString();
    }

}

