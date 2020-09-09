package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ProjectView2Activity extends AppCompatActivity {
    // the plan for the commented code below was abandoned, might be revisited later
    // private Button statusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view2);

        // receive intent from Projects view
        Intent openProjectView2Intent = getIntent();

        // the plan for the commented code below was abandoned, might be revisited later
        // get views in layout
        //statusButton = findViewById(R.id.status_button);

        // Set text on status button
        //statusButton.setText("OK");

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


// the plan for the commented code below was abandoned, might be revisited later
//    public void handleStatusButtonClick(View view) {
//        String statusButtonText;
//        statusButtonText = String.valueOf(statusButton.getText());
//        if(statusButtonText == "OK"){
//            // Back to project view 1
//            Intent backToProjectsView1 = new Intent(this, ProjectViewActivity.class);
//            startActivity(backToProjectsView1);
//
//            // do nothing on project status
//
//        }
//        else if(statusButtonText == "Accepted"){
//            // Go to all projects view
//            Intent projectsIntent = new Intent(this, ProjectsActivity.class);
//            startActivity(projectsIntent);
//
//            // call method to change status in backend
//            changeProjectStatusToAccepted();
//        }
//        // this part below is executed when statusButtonText == "Rejected"
//        else{
//            // Back to project view 1
//            Intent backToProjectsView1 = new Intent(this, ProjectViewActivity.class);
//            startActivity(backToProjectsView1);
//
//            // call method to change status in backend
//            changeProjectStatusToRejected();
//        }
//    }
//
//    public void changeProjectStatusToAccepted(){
//
//    }
//
//    public void changeProjectStatusToRejected(){
//
//    }

}
