package com.example.freelancer.ski;

public class DesList {


        private int skillSubId;
        private String skillSubName;
        private String skillSubImage;
        private String skillSubCreatedAt;
        private String skillSubUpdatedAt;
        private int skillSuperCategoryId;


    public DesList(int skillSubId, String skillSubName, String skillSubImage, String skillSubCreatedAt, String skillSubUpdatedAt, int skillSuperCategoryId) {
        this.skillSubId = skillSubId;
        this.skillSubName = skillSubName;
        this.skillSubImage = skillSubImage;
        this.skillSubCreatedAt = skillSubCreatedAt;
        this.skillSubUpdatedAt = skillSubUpdatedAt;
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

    public void setSkillSuperCategoryId(int skillSuperCategoryId) {
        this.skillSuperCategoryId = skillSuperCategoryId;
    }
}
