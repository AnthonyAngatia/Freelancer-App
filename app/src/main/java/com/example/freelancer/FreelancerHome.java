package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class FreelancerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_home);

        // LATER//
        // Anto group feedback on toolbar to use
        Toolbar toolbar = findViewById(R.id.freelancer_toolbar);
        setSupportActionBar(toolbar);
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
        // LATER
        // Israel will create this class
        // Go to Requests
        //Intent openRequestsIntent = new Intent(this, RequestsActivity.class);
        //startActivity(openRequestsIntent);
    }

    public void openAddSkill(View view) {
        // LATER
        // Israel will create this class
        // Go to AddSkill
        //Intent openAddSkillIntent = new Intent(this, AddSkillActivity.class);
        //startActivity(openAddSkillIntent);
    }

    public void openProjects(View view) {
        //Go to ProjectsActivity
        Intent openProjectsIntent = new Intent(this, ProjectsActivity.class);
        startActivity(openProjectsIntent);
    }
}
