package com.example.freelancer.classes;

public class FreeLancer  {
    private String name;
    private String imageUrl;
    private String rating;
    private String qualification;
    private String portfolio;

    public FreeLancer(String name, String imageUrl, String rating, String qualification, String portfolio) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.qualification = qualification;
        this.portfolio = portfolio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "FreeLancer{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating='" + rating + '\'' +
                ", qualification='" + qualification + '\'' +
                ", portfolio='" + portfolio + '\'' +
                '}';
    }
}
