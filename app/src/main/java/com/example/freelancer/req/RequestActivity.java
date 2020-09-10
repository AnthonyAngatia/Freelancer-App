package com.example.freelancer.req;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.Project;
import com.example.freelancer.req.RequestsList;
import com.example.freelancer.ProjectsActivity;
import com.example.freelancer.ProjectsAdapter;
import com.example.freelancer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.freelancer.classes.FreelanceServiceManager.loginPreference;

public class RequestActivity extends AppCompatActivity {
    //api
    //get userid from eg sharedPreference...(for now I'm using static data)
    SharedPreferences preferences;
    private int userId;
    private String URL_DATA;

    //declare recycler view
    private RecyclerView projectsRecyclerView;
    //declare adapter
    private RequestsAdapter projectsAdapter;
    //declare Developers list
    private List<RequestsList> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //receive intent from FreelancerHome
        Intent openProjectsIntent = getIntent();

        //get userid from eg sharedPreference...(for now I'm using static data)
        //userId = 17;
        preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        userId = preferences.getInt("id", 19);

        //URL_DATA = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/public/api/appusers/freelancer/"+ userId +"/requests";
        URL_DATA = "http://sheltered-plains-24359.herokuapp.com/api/appusers/freelancer/"+ userId +"/requests";

        //initialize recycler view
        projectsRecyclerView = findViewById(R.id.req_recycler_view);
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
                    JSONArray projectsArrayJSON = jsonObject.getJSONArray("data");
                    for(int i=0; i < projectsArrayJSON.length();i++){
                        JSONObject projectJSONObject = projectsArrayJSON.getJSONObject(i);

                        RequestsList request = new RequestsList(Integer.parseInt(projectJSONObject.getString("project_id")),
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
                        projectList.add(request);
                        Log.d("res", "developers"+projectList);
                    }
                    projectsAdapter = new RequestsAdapter(projectList, getApplicationContext());
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
                Toast.makeText(RequestActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        int MY_SOCKET_TIMEOUT_MS = 5000;
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }


}