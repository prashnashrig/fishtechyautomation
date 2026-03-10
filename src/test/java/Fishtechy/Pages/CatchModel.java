package Fishtechy.Pages;

public class CatchModel {

        private String fishName;
        private String date;
        private boolean hasLocation;

        public CatchModel(String fishName, String date, boolean hasLocation) {
            this.fishName = fishName;
            this.date = date;
            this.hasLocation = hasLocation;
        }

        public String getFishName() {

            return fishName;
        }

        public String getDate() {

            return date;
        }

        public boolean hasLocation() {

            return hasLocation;
        }
    }
