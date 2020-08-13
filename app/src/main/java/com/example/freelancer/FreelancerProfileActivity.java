package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freelancer.classes.FreeLancer;
import com.example.freelancer.classes.FreelanceServiceManager;

public class FreelancerProfileActivity extends AppCompatActivity {
    public static final String LIST_POSITION = "com.example.freelancer.LIST_POSITION";
    Button requestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        requestBtn = findViewById(R.id.button_request);
        ImageView imageViewProfile = findViewById(R.id.imageView_Profile);//Not in use
        TextView textViewName = findViewById(R.id.name);
        TextView textViewPhone = findViewById(R.id.phone_freelancer_profile);
        TextView textViewLocation = findViewById(R.id.location);
        TextView textViewDescription = findViewById(R.id.description_freelancer_profile);

        Intent intent = getIntent();
        int position = intent.getIntExtra(LIST_POSITION, -1);
        FreeLancer freeLancer = FreelanceServiceManager.getInstance().getFreelancersList().get(position);
        Log.d("FREELANCER", freeLancer.toString());
        Log.d("FREELANCER", freeLancer.getName());
        Log.d("FREELANCER", freeLancer.getPhone_no());

        textViewName.setText(freeLancer.getName());
        textViewPhone.setText(freeLancer.getPhone_no());
        textViewDescription.setText(freeLancer.getQualification());
        textViewLocation.setText(freeLancer.getLocation());
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FreelancerProfileActivity.this, "Request made. Please wait for the response. Thank you", Toast.LENGTH_SHORT).show();
            }
        });
    }
}