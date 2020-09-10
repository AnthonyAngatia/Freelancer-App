package com.example.freelancer.classes;

import androidx.annotation.NonNull;

public class FreeLancer  {
    private int id;
    private String name;
    private String imageUrl;
    private String location;
    private int phone_no;
    private int rating;
    private String qualification;
    private String portfolio;

    public FreeLancer(int id, String name, String imageUrl, String location, int phone_no, int rating, String qualification, String portfolio) {
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

    public int getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "FreeLancer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", location='" + location + '\'' +
                ", phone_no=" + phone_no +
                ", rating=" + rating +
                ", qualification='" + qualification + '\'' +
                ", portfolio='" + portfolio + '\'' +
                '}';
    }
}
