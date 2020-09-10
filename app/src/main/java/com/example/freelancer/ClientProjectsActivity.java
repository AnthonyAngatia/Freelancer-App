package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

public class ClientProjectsActivity extends AppCompatActivity {

    //api
    //get userid from eg sharedPreference...(for now I'm using static data)
    SharedPreferences preferences;
    private int userId;
    private String URL_DATA;

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
        Intent openClientProjectsIntent = getIntent();

        //get userid from eg sharedPreference...(for now I'm using static data)
        //userId = 16;
        preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        userId = preferences.getInt("id", 18);
		URL_DATA = "http://sheltered-plains-24359.herokuapp.com/api/appusers/client/" + userId + "/projects";
        //URL_DATA = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/public/api/appusers/client/" + userId + "/projects";

        //Test
        Log.d("User ID", String.valueOf(userId));

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
//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 900000000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 900000000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//                Log.d("Request Timeout Error:", error.toString());
//            }
//        });
        int socketTimeout = 600000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        //Add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.freelancer_mode:
                Intent toFreelancerMode = new Intent(this, FreelancerHomeActivity.class);
                startActivity(toFreelancerMode);
            case R.id.client_projects:
                Intent toClientProjects = new Intent(this, ClientProjectsActivity.class);
                startActivity(toClientProjects);
        }
        return super.onOptionsItemSelected(item);
    }

}


