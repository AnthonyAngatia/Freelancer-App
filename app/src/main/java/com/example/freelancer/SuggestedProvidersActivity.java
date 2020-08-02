package com.example.freelancer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.freelancer.classes.FreelanceServiceManager;

import java.util.List;

/*
* This activity handles the display of various artist performing a certain
* subcategory of freelance service
* We should be able to filter and search from this Activity
* */
public class SuggestedProvidersActivity extends AppCompatActivity {

    public final static String SUB_CATEGORY_ID = "com.example.freelancer.SUB_CATEGORY_ID";
    private static final int POSITION_NOT_SET = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_providers);
        final RecyclerView freelancersRecycler = findViewById(R.id.recycler_providers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        freelancersRecycler.setLayoutManager(linearLayoutManager);
        //Pass context and Data to the Adapter
        //getIntent from the subCategories
        Intent intent = getIntent();
        int subCategoryId = intent.getIntExtra(SUB_CATEGORY_ID, POSITION_NOT_SET);



        List<String> freelancerNames = FreelanceServiceManager.getInstance().getFreelancer(subCategoryId);
        SuggestedProvidersAdapter freelancerAdapter = new SuggestedProvidersAdapter(this, freelancerNames);
        freelancersRecycler.setAdapter(freelancerAdapter);
    }


}
