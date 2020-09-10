package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ProjectViewActivity extends AppCompatActivity {
    TextView tvProgressLabel;
    TextView projectDescription;
    TextView projectRequestorName;
    TextView projectRequestorPhone;
    TextView projectRequestorLocation;
    RequestQueue queue;

    //(TO DO LATER)store project id in a shared preference for easier access
    private static String projectId;
    private static String URL_DATA_FINISH_PROJECT;
    private static String URL_DATA_UPDATE_PROGRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        //instantiate volley request queue
        queue = Volley.newRequestQueue(this);

        // receive intent from Projects view
        Intent openProjectViewIntent = getIntent();

        //get projectId from intent data
        //projectId = Integer.parseInt(openProjectViewIntent.getStringExtra("project_id"));
        //projectId = 3;
        projectId = openProjectViewIntent.getStringExtra("project_id");

        //projectId = openProjectViewIntent.getIntExtra("project_id", 1);
        //URL_DATA_FINISH_PROJECT = "http://localhost:8000/api/projects/finish/" + projectId;
        //URL_DATA_FINISH_PROJECT = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/public/api/projects/finish/" + projectId;
        URL_DATA_FINISH_PROJECT = "http://sheltered-plains-24359.herokuapp.com/api/projects/finish/" + projectId;

        //URL_DATA_UPDATE_PROGRESS = "http://localhost:8000/api/projects/progress" + projectId;
        //URL_DATA_UPDATE_PROGRESS = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/public/api/projects/progress/" + projectId;
        URL_DATA_UPDATE_PROGRESS = "http://sheltered-plains-24359.herokuapp.com/api/projects/progress/" + projectId;

        String projectItemDescription = openProjectViewIntent.getStringExtra("project_description");
        String projectItemRequestorName = openProjectViewIntent.getStringExtra("project_requestor_name");
        String projectItemRequestorPhone = openProjectViewIntent.getStringExtra("project_requestor_phone");
        String projectItemRequestorLocation = openProjectViewIntent.getStringExtra("project_requestor_location");
        // get the views
        projectDescription = findViewById(R.id.project_description);
        projectRequestorName = findViewById(R.id.project_item_requestor_name2);
        projectRequestorPhone = findViewById(R.id.project_item_requestor_phone2);
        projectRequestorLocation = findViewById(R.id.project_item_requestor_location2);
        // set the views with the intent data
        projectDescription.setText(projectItemDescription);
        projectRequestorName.setText(projectItemRequestorName);
        projectRequestorPhone.setText(projectItemRequestorPhone);
        projectRequestorLocation.setText(projectItemRequestorLocation);

        //set project progress
        SeekBar seekBar = findViewById(R.id.progress_seek_bar);
        tvProgressLabel = findViewById(R.id.progress_text);
        seekBar.setMax(100);
        int initProgress = Integer.parseInt(openProjectViewIntent.getStringExtra("project_progress"));
        seekBar.setProgress(initProgress);
        tvProgressLabel.setText("Progress: "+ initProgress);

        // set a change listener for SeekBar
        //seekBar.OnSeekBarChangeListener(seekBarChangeListener);
        int progress = seekBar.getProgress();

        tvProgressLabel.setText("Progress: "+ progress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // updated continuously as user slided the thumb
                tvProgressLabel.setText("Progress: "+ progress);

                // code below to call API to update progress in backend
                updateProjectProgress(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // called when user first touches seek bar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // called after user finishes moving seek bar
            }
        });
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

    public void openProjectView2(View view) {
        // Go to ProjectView2Activity
        Intent openProjectView2 = new Intent(this, ProjectView2Activity.class);
        startActivity(openProjectView2);

        // code below to call API to finish project
        //finishProject();

    }

    public void updateProjectProgress(final String projectProgress){
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA_UPDATE_PROGRESS,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // find out how to log the response itself here
                        Log.d("Error.Response", "Error Response");
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("project_progress", projectProgress);
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);
    }

    public void updateProjectProgressTemp(View v){
        //RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA_UPDATE_PROGRESS,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // find out how to log the response itself here
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("project_progress", /*project_progress*/ "250");
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);
    }

    public void finishProject(View view){
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA_FINISH_PROJECT,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // find out how to log the response itself here
                        Log.d("Error.Response", "Error Response");
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("project_status", "complete");
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);

        // Go to ProjectView2Activity
        Intent openProjectView2 = new Intent(this, ProjectView2Activity.class);
        startActivity(openProjectView2);
    }

    public void finishProjectTemp(View view) {
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA_FINISH_PROJECT,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // find out how to log the response itself here
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("project_status", "completed");
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);
        // Go to ProjectView2Activity
        Intent openProjectView2 = new Intent(this, ProjectView2Activity.class);
        startActivity(openProjectView2);
    }
}
