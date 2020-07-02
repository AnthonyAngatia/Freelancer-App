package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity {
    //api
    private static final String URL_DATA = "";
    //declare recycler view
    private RecyclerView projectsRecyclerView;
    //declare adapter
    private ProjectsAdapter projectsAdapter;
    //declare Developers list
    private List<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        //receive intent from FreelancerHome
        Intent openProjectsIntent = getIntent();

        //initialize recycler view
        projectsRecyclerView = findViewById(R.id.projects_recycler_view);
        projectsRecyclerView.setHasFixedSize(true);
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initialize the projectsList... THIS LIST WILL HOLD THE CONTENTS OF OUR REMOTE JSON AND PASS IT TO RECYCLERVIEW
        projectList = new ArrayList<>();

        loadUrlData();
    }

    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        // Making HTTP Request and getting Response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.w("res", "Response:"+ response);
                progressDialog.dismiss();
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray projectsArrayJSON = jsonObject.getJSONArray("projects");
                    for(int i=0; i < projectsArrayJSON.length();i++){
                        JSONObject projectJSONObject = projectsArrayJSON.getJSONObject(i);

                        Project project = new Project(Integer.parseInt(projectJSONObject.getString("project_id")),
                                                      projectJSONObject.getString("project_status"),
                                                      projectJSONObject.getString("project_review"),
                                                      projectJSONObject.getString("project_description"),
                                                      Double.parseDouble(projectJSONObject.getString("project_price")),
                                                      projectJSONObject.getString("project_delivery_time"),
                                                      Integer.parseInt(projectJSONObject.getString("appuser_inviter_id")),
                                                      Integer.parseInt(projectJSONObject.getString("appuser_freelancer_id")),
                                                      projectJSONObject.getString("project_item_requestor_name"),
                                                      projectJSONObject.getString("project_item_requestor_location"),
                                                      projectJSONObject.getString("project_item_requestor_phone")
                                                      );
                        projectList.add(project);
                        Log.d("res", "developers"+projectList);
                    }
                    projectsAdapter = new ProjectsAdapter(projectList, getApplicationContext());
                    projectsRecyclerView.setAdapter(projectsAdapter);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                Log.d("Tag", response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProjectsActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
