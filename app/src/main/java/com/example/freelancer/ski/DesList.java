package com.example.freelancer.ski;

public class DesList {


        private int skillSubId;
        private String skillSubName;
        private String skillSubImage;
        private String skillSubCreatedAt;
        private String skillSubUpdatedAt;
        private String skillSubDescription;
        private int skillSubAppUserId;
        private int skillSuperCategoryId;


    public DesList(int skillSubId, String skillSubName, String skillSubImage, String skillSubCreatedAt, String skillSubUpdatedAt, String skillSubDescription, int skillSubAppUserId, int skillSuperCategoryId) {
        this.skillSubId = skillSubId;
        this.skillSubName = skillSubName;
        this.skillSubImage = skillSubImage;
        this.skillSubCreatedAt = skillSubCreatedAt;
        this.skillSubUpdatedAt = skillSubUpdatedAt;
        this.skillSubDescription = skillSubDescription;
        this.skillSubAppUserId = skillSubAppUserId;
        this.skillSuperCategoryId = skillSuperCategoryId;
    }


    public int getSkillSubId() {
        return skillSubId;
    }

    public String getSkillSubName() {
        return skillSubName;
    }

    public String getSkillSubImage() {
        return skillSubImage;
    }

    public String getSkillSubCreatedAt() {
        return skillSubCreatedAt;
    }

    public String getSkillSubUpdatedAt() {
        return skillSubUpdatedAt;
    }

    public String getSkillSubDescription() {
        return skillSubDescription;
    }

    public int getSkillSubAppUserId() {
        return skillSubAppUserId;
    }

    public int getSkillSuperCategoryId() {
        return skillSuperCategoryId;
    }

    public void setSkillSubId(int skillSubId) {
        this.skillSubId = skillSubId;
    }

    public void setSkillSubName(String skillSubName) {
        this.skillSubName = skillSubName;
    }

    public void setSkillSubImage(String skillSubImage) {
        this.skillSubImage = skillSubImage;
    }

    public void setSkillSubCreatedAt(String skillSubCreatedAt) {
        this.skillSubCreatedAt = skillSubCreatedAt;
    }

    public void setSkillSubUpdatedAt(String skillSubUpdatedAt) {
        this.skillSubUpdatedAt = skillSubUpdatedAt;
    }

    public void setSkillSubDescription(String skillSubDescription) {
        this.skillSubDescription = skillSubDescription;
    }

    public void setSkillSubAppUserId(int skillSubAppUserId) {
        this.skillSubAppUserId = skillSubAppUserId;
    }

    public void setSkillSuperCategoryId(int skillSuperCategoryId) {
        this.skillSuperCategoryId = skillSuperCategoryId;
    }
}
