package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

    public boolean onCreateOptionsMenu(Menu menu){
        // LATER//
        // Anto group feedback on menu to use
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.freelancer_menu, menu);
        return true;
    }

    public void openRequests(View view) {
        // TODO LATER
        // Israel will create this class
        // Go to Requests
//        Intent openRequestsIntent = new Intent(this, RequestsActivity.class);
//        startActivity(openRequestsIntent);
    }

    public void openAddSkill(View view) {
        // TODO LATER
        // Israel will create this class
        // Go to AddSkill
//        Intent openAddSkillIntent = new Intent(this, AddSkillActivity.class);
//        startActivity(openAddSkillIntent);
    }

    public void openProjects(View view) {
        //Go to ProjectsActivity
        Intent openProjectsIntent = new Intent(this, ProjectsActivity.class);
        startActivity(openProjectsIntent);
    }
}
