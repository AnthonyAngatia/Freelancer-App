package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProjectView2Activity extends AppCompatActivity {
    private Button statusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view2);

        // get views in layout
        statusButton = findViewById(R.id.status_button);

        // Set text on status button
        statusButton.setText("OK");

    }


    public void handleStatusButtonClick(View view) {
        String statusButtonText;
        statusButtonText = String.valueOf(statusButton.getText());
        if(statusButtonText == "OK"){
            // Back to project view 1
            Intent backToProjectsView1 = new Intent(this, ProjectViewActivity.class);
            startActivity(backToProjectsView1);

            // do nothing on project status

        }
        else if(statusButtonText == "Accepted"){
            // Go to all projects view
            Intent projectsIntent = new Intent(this, ProjectsActivity.class);
            startActivity(projectsIntent);

            // call method to change status in backend
            changeProjectStatusToAccepted();
        }
        // this part below is executed when statusButtonText == "Rejected"
        else{
            // Back to project view 1
            Intent backToProjectsView1 = new Intent(this, ProjectViewActivity.class);
            startActivity(backToProjectsView1);

            // call method to change status in backend
            changeProjectStatusToRejected();
        }
    }


    public void changeProjectStatusToAccepted(){

    }

    public void changeProjectStatusToRejected(){

    }

}
