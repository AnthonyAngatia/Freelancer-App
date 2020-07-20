package com.example.freelancer.classes;

import java.util.List;
/*
* This model class is used ti store information that populated the
* recyclerview in ServiceSubCategoryAdapter
* */
public class ServiceSubCategory {
    //TODO Add subcategory name
    private List<String> subCategoryTitles;
    private List<Integer> subCategoryImages;//Might be changed to Uri

    public ServiceSubCategory(List<String> subCategoryTitles, List<Integer> subCategoryImages) {
        this.subCategoryTitles = subCategoryTitles;
        this.subCategoryImages = subCategoryImages;
    }

    public List<String> getSubCategoryTitles() {
        return subCategoryTitles;
    }

    public void setSubCategoryTitles(List<String> subCategoryTitles) {
        this.subCategoryTitles = subCategoryTitles;
    }

    public List<Integer> getSubCategoryImages() {
        return subCategoryImages;
    }

    public void setSubCategoryImages(List<Integer> subCategoryImages) {
        this.subCategoryImages = subCategoryImages;
    }
}
