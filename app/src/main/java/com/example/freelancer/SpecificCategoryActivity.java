package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.freelancer.classes.FreelanceServiceManager;
import com.example.freelancer.classes.ServiceSubCategory;

/*
* This is an Activity contains
* */
public class SpecificCategoryActivity extends AppCompatActivity {
//    The category ID is the position of the category in its datastructure i.e the arraylist index
    public static final String CATEGORY_ID = "com.example.freelancer.CATEGORY_ID";
    private static final int POSITION_NOT_SET = -1;//Helps in debugging


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_category);
        //RecyclerView stuff & adapter
        RecyclerView recyclerSubCategories = findViewById(R.id.recycler_category);
        //LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerSubCategories.setLayoutManager(linearLayoutManager);
        //get The items under the general category
        Intent intent = getIntent();
        int id = intent.getIntExtra(CATEGORY_ID, POSITION_NOT_SET);
        if(id <= 0) {
            //The intent was not passed from the ServiceAdapter
        }
        ;
        //Use id to fetch from the API //For now I get fromFreeLanceServiceManager
        ServiceSubCategory serviceSubCategory = FreelanceServiceManager.getInstance()
                .getSubCategory( SpecificCategoryActivity.this, id);//Returns an object of subCategory

        //Pass the SubCategory Object to the adapter
        ServiceSubCategoryAdapter subCategoryAdapter = new ServiceSubCategoryAdapter(this,serviceSubCategory);
        recyclerSubCategories.setAdapter(subCategoryAdapter);


    }
}
