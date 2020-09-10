package com.example.freelancer.ski;


    public class SkiList {

        private int skillSuperId;
        private String skillSuperName;
        private String skillSuperImage;
        private String skillSuperCreatedAt;
        private String skillSuperUpdatedAt;


        public SkiList(int skillSuperId, String skillSuperName, String skillSuperImage, String skillSuperCreatedAt, String skillSuperUpdatedAt) {
            this.skillSuperId = skillSuperId;
            this.skillSuperName = skillSuperName;
            this.skillSuperImage = skillSuperImage;
            this.skillSuperCreatedAt = skillSuperCreatedAt;
            this.skillSuperUpdatedAt = skillSuperUpdatedAt;

        }

        public int getSkillSuperId() {
            return skillSuperId;
        }

        public String getSkillSuperName() {
            return skillSuperName;
        }

        public String getSkillSuperImage() {
            return skillSuperImage;
        }

        public String getSkillSuperCreatedAt() {
            return skillSuperCreatedAt;
        }

        public String getSkillSuperUpdatedAt() {
            return skillSuperUpdatedAt;
        }


        public void setSkillSuperId(int skillSuperId) {
            this.skillSuperId = skillSuperId;
        }

        public void setSkillSuperName(String skillSuperName) {
            this.skillSuperName = skillSuperName;
        }

        public void setSkillSuperImage(String skillSuperImage) {
            this.skillSuperImage = skillSuperImage;
        }

        public void setSkillSuperCreatedAt(String skillSuperCreatedAt) {
            this.skillSuperCreatedAt = skillSuperCreatedAt;
        }

        public void setSkillSuperUpdatedAt(String skillSuperUpdatedAt) {
            this.skillSuperUpdatedAt = skillSuperUpdatedAt;
        }
    }