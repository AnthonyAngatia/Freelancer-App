package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.freelancer.classes.FreelanceServiceManager.loginPreference;

public class ProjectsActivity extends AppCompatActivity {
    //api
    //get userid from eg sharedPreference...(for now I'm using static data)
    SharedPreferences preferences;
    private int userId;
    private String URL_DATA;


    // declare recycler view
    private RecyclerView projectsRecyclerView;
    //declare adapter
    private ProjectsAdapter projectsAdapter;
    //declare Developers list
    private List<Project> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        //receive intent from FreelancerHomeIntent
        Intent openProjectsIntent = getIntent();

        //get userid from eg sharedPreference...(for now I'm using static data)
        //userId = 17;
        preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        userId = preferences.getInt("id", 19);
		URL_DATA = "http://sheltered-plains-24359.herokuapp.com/api/appusers/freelancer/" + userId + "/projects";
        //URL_DATA = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/public/api/appusers/freelancer/" + userId + "/projects";

        //Test
        Log.d("User ID", String.valueOf(userId));

        //initialize recycler view
        projectsRecyclerView = findViewById(R.id.projects_recycler_view);
        projectsRecyclerView.setHasFixedSize(true);
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initialize the projectsList... THIS LIST WILL HOLD THE CONTENTS OF OUR REMOTE JSON AND PASS IT TO RECYCLERVIEW
        projectList = new ArrayList<>();

        loadUrlData();
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

    private void loadUrlData() {
        projectsRecyclerView.setHasFixedSize(true);
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        // Making HTTP Request and getting Response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res", "Response:"+ response);
                progressDialog.dismiss();

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray projectsArrayJSON = jsonObject.getJSONArray("data");
                    //logging response
                    //String responseValue = jsonObject.getString("response");
                    Log.d("Response", response);


                    for(int i=0; i < projectsArrayJSON.length();i++){
                        JSONObject projectJSONObject = projectsArrayJSON.getJSONObject(i);

                        Project project = new Project(Integer.parseInt(projectJSONObject.getString("project_id")),
                                                      projectJSONObject.getString("project_status"),
                                                      projectJSONObject.getString("project_review"),
                                                      projectJSONObject.getString("project_description"),
                                                      Double.parseDouble(projectJSONObject.getString("project_price")),
                                                      projectJSONObject.getString("project_delivery_time"),
                                                      projectJSONObject.getString("project_progress"),
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
                Log.d("Response", error.toString());
            }
        });

        //Increase timeout of StringRequest
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 900000000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 900000000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                Log.d("Request Timeout Error:", error.toString());
            }
        });

        //adding request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
