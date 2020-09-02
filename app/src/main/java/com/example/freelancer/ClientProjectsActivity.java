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

public class ClientProjectsActivity extends AppCompatActivity {

    //api
    //get userid from eg sharedPreference...(for now I'm using static data)
    private static int userId = 1;
    //private static final String URL_DATA = "http://localhost:8000/api/appusers/client/" + userId + "/projects"; //TO CHANGE
    private static final String URL_DATA = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/freelancer_api_v1/public/api/appusers/client/" + userId + "/projects";
    //declare recycler view
    private RecyclerView clientProjectsRecyclerView;
    //declare adapter
    private ClientProjectsAdapter clientProjectsAdapter;
    //declare Developers list
    private List<ClientProject> clientProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_projects);

        //receive intent from Client Home
        //Intent openClientProjectsIntent = getIntent();

        //initialize recycler view
        clientProjectsRecyclerView = findViewById(R.id.client_projects_recycler_view);
        clientProjectsRecyclerView.setHasFixedSize(true);
        clientProjectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initialize the clientProjectsList... THIS LIST WILL HOLD THE CONTENTS OF OUR REMOTE JSON AND PASS IT TO RECYCLERVIEW
        clientProjectList = new ArrayList<>();

        loadUrlData();
    }

    // get data from API on all the client projects
    public void loadUrlData(){
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
                    JSONArray clientProjectsArrayJSON = jsonObject.getJSONArray("data");
                    for(int i=0; i < clientProjectsArrayJSON.length();i++){
                        JSONObject clientProjectJSONObject = clientProjectsArrayJSON.getJSONObject(i);

                        ClientProject clientProject = new ClientProject(Integer.parseInt(clientProjectJSONObject.getString("project_id")),
                                clientProjectJSONObject.getString("project_status"),
                                clientProjectJSONObject.getString("project_review"),
                                clientProjectJSONObject.getString("project_description"),
                                Double.parseDouble(clientProjectJSONObject.getString("project_price")),
                                clientProjectJSONObject.getString("project_delivery_time"),
                                clientProjectJSONObject.getString("project_progress"),
                                Integer.parseInt(clientProjectJSONObject.getString("appuser_inviter_id")),
                                Integer.parseInt(clientProjectJSONObject.getString("appuser_freelancer_id")),
                                clientProjectJSONObject.getString("project_item_freelancer_name"),
                                clientProjectJSONObject.getString("project_item_freelancer_location"),
                                clientProjectJSONObject.getString("project_item_freelancer_phone")
                        );
                        clientProjectList.add(clientProject);
                        Log.d("res", "developers"+clientProjectList);
                    }
                    clientProjectsAdapter = new ClientProjectsAdapter(clientProjectList, getApplicationContext());
                    clientProjectsRecyclerView.setAdapter(clientProjectsAdapter);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                Log.d("Tag", response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ClientProjectsActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
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

        //Add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

