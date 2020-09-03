package com.example.freelancer.classes;

public class FreeLancer  {
    private int id;
    private String name;
    private String imageUrl;
    private String location;
    private String phone_no;
    private String rating;
    private String qualification;
    private String portfolio;

    public FreeLancer(int id, String name, String imageUrl, String location, String phone_no, String rating, String qualification, String portfolio) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.location = location;
        this.phone_no = phone_no;
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

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
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
