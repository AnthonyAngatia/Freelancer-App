package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freelancer.classes.FreeLancer;
import com.example.freelancer.classes.FreelanceServiceManager;

public class FreelancerProfileActivity extends AppCompatActivity {
    public static final String LIST_POSITION = "com.example.freelancer.LIST_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        ImageView imageViewProfile = findViewById(R.id.imageView_Profile);
        TextView textViewName = findViewById(R.id.textView_name);
        TextView textViewPhone = findViewById(R.id.textView_phone);
        TextView textViewEmail = findViewById(R.id.textView_email);
        //Not to be used
        TextView textViewComments = findViewById(R.id.textView_comments);
        Intent intent = getIntent();
        int position = intent.getIntExtra(LIST_POSITION, -1);
        FreeLancer freeLancer = FreelanceServiceManager.getInstance().getFreelancersList().get(position);
        textViewName.setText(freeLancer.getName());
        textViewPhone.setText(freeLancer.getPhone_no());




    }

}
