package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.freelancer.req.RequestActivity;
import com.example.freelancer.ski.AllAdded;
import com.example.freelancer.ski.SkiActivity;

public class FreelancerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_home);

        // Receive intent
        Intent toFreelancerHome = getIntent();

        // LATER //
        // Anto group feedback on toolbar to use
        // Already discussed
        //Toolbar toolbar = findViewById(R.id.freelancer_toolbar);
        //setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.freelancer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.back_to_client:
                Intent backToClientIntent = new Intent(this, MainActivity.class);
                startActivity(backToClientIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openRequests(View view) {
        // TODO LATER
        // Israel will create this class
        // Go to Requests
        Intent openRequestsIntent = new Intent(this, RequestActivity.class);
        startActivity(openRequestsIntent);
    }

    public void openMySkills(View view) {
        // TODO LATER
        // Israel will create this class
        // Go to MySkills
        Intent openMySkillsIntent = new Intent(this, AllAdded.class);
        startActivity(openMySkillsIntent);
    }

    public void openProjects(View view) {
        //Go to ProjectsActivity
        Intent openProjectsIntent = new Intent(this, ProjectsActivity.class);
        startActivity(openProjectsIntent);
    }
}
