package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    RequestQueue queue = Volley.newRequestQueue(this);

    //(TO DO LATER)store project id in a shared preference for easier access
    private static int projectId;
    private static String URL_DATA_FINISH_PROJECT;
    private static String URL_DATA_UPDATE_PROGRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);



        // receive intent from Projects view
        Intent openProjectViewIntent = getIntent();
        //get project id to URLs
        projectId = openProjectViewIntent.getIntExtra("project_id", 1);
        URL_DATA_FINISH_PROJECT = "http://localhost:8000/api/projects/finish/" + projectId;
        URL_DATA_UPDATE_PROGRESS = "http://localhost:8000/api/projects/progress" + projectId;

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

        // set a change listener for SeekBar
        SeekBar seekBar = findViewById(R.id.progress_seek_bar);
        //seekBar.OnSeekBarChangeListener(seekBarChangeListener);
        int progress = seekBar.getProgress();
        tvProgressLabel = findViewById(R.id.progress_text);
        tvProgressLabel.setText("Progress: "+ progress);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
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
    };


    public void openProjectView2(View view) {
        // Go to ProjectView2Activity
        Intent openProjectView2 = new Intent(this, ProjectView2Activity.class);
        startActivity(openProjectView2);

        // code below to call API to finish project
        finishProject();

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

    public void finishProject(){
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
                params.put("project_status", "pending");
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);
    }


}
