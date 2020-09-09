package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

public class ClientProjectViewActivity extends AppCompatActivity {
    //api
    //get userid from eg sharedPreference...(for now I'm using static data)
    private static int clientId = 1;
    private static int freelancerId;
    private static  String URL_DATA_PROJECT_VIEW;
    ClientProject clientProject;
    TextView clientProjectStatus;
    TextView clientProjectDescription;
    TextView clientProjectItemFreelancerName;
    TextView clientProjectItemFreelancerPhone;
    TextView clientProjectItemFreelancerLocation;
    TextView clientProgressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_project_view);

        // receive intent from ClientProjectsActivity
        Intent openClientProjectViewIntent = getIntent();

        //get project id to URLs
        freelancerId = openClientProjectViewIntent.getIntExtra("appuser_freelancer_id", 1);

        //not necessary any more as I am getting the data from the intents
        //using static one for now to test
        //freelancerId = 3;
        //URL_DATA_PROJECT_VIEW = "http://localhost:8000/api/appusers/freelancer/" + freelancerId + "/client/" + clientId + "/project";
        //URL_DATA_PROJECT_VIEW = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/freelancer_api_v1/public/api/appusers/freelancer/" + freelancerId + "/client/" + clientId + "/project";
        //not necessary any more as I am getting the data from the intents
        // get client project details- make call to API
        // getClientProjectDetails();

        // edit text in project status text view
        String project_status_display = openClientProjectViewIntent.getStringExtra("project_status");
        clientProjectStatus = findViewById(R.id.client_project_status);
        if(project_status_display.equals("complete")){
            clientProjectStatus.setText("Completed");
        }
        else{
            clientProjectStatus.setText("In Progress");
        }
        clientProjectDescription = findViewById(R.id.client_project_description);
        clientProjectItemFreelancerName = findViewById(R.id.client_project_item_freelancer_name2);
        clientProjectItemFreelancerPhone = findViewById(R.id.client_project_item_freelancer_phone2);
        clientProjectItemFreelancerLocation = findViewById(R.id.client_project_item_freelancer_location2);
        clientProgressText = findViewById(R.id.client_progress_text);

        clientProjectDescription.setText(openClientProjectViewIntent.getStringExtra("project_description"));
        clientProjectItemFreelancerName.setText(openClientProjectViewIntent.getStringExtra("project_freelancer_name"));
        clientProjectItemFreelancerPhone.setText(openClientProjectViewIntent.getStringExtra("project_freelancer_phone"));
        clientProjectItemFreelancerLocation.setText(openClientProjectViewIntent.getStringExtra("project_freelancer_location"));
        String project_progress = openClientProjectViewIntent.getStringExtra("project_progress") + "%";
        clientProgressText.setText(project_progress);

    }

    //not necessary any more as I am getting the data from the intents
    // get client project details- make call to API
    // depending on the result of what we get on the project status, this will determine what the project status will be on the layout
//    public void getClientProjectDetails(){
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading");
//        progressDialog.show();
//
//        // Making HTTP Request and getting Response
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA_PROJECT_VIEW, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.w("res", "Response:"+ response);
//                progressDialog.dismiss();
//                try{
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray clientProjectsArrayJSON = jsonObject.getJSONArray("data");
//                    for(int i=0; i < clientProjectsArrayJSON.length();i++){
//                        JSONObject clientProjectJSONObject = clientProjectsArrayJSON.getJSONObject(i);
//
//                        clientProject = new ClientProject(Integer.parseInt(clientProjectJSONObject.getString("project_id")),
//                                clientProjectJSONObject.getString("project_status"),
//                                clientProjectJSONObject.getString("project_review"),
//                                clientProjectJSONObject.getString("project_description"),
//                                Double.parseDouble(clientProjectJSONObject.getString("project_price")),
//                                clientProjectJSONObject.getString("project_delivery_time"),
//                                clientProjectJSONObject.getString("project_progress"),
//                                Integer.parseInt(clientProjectJSONObject.getString("appuser_inviter_id")),
//                                Integer.parseInt(clientProjectJSONObject.getString("appuser_freelancer_id")),
//                                clientProjectJSONObject.getString("project_item_freelancer_name"),
//                                clientProjectJSONObject.getString("project_item_freelancer_location"),
//                                clientProjectJSONObject.getString("project_item_freelancer_phone")
//                        );
//
//                        // not sure whether code below will work
//                        Log.d("res", "developers"+clientProject);
//                    }
//
//                }
//                catch(JSONException e){
//                    e.printStackTrace();
//                }
//                Log.d("Tag", response);
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ClientProjectViewActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
//                Log.d("Tag", error.toString());
//            }
//        });
//
//        //Increase timeout of StringRequest
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
//
//        //Add request to queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
    //not necessary any more as I am getting the data from the intents

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
