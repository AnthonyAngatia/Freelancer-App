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

    //get projectid from eg sharedPreference...(for now I'm using static data)
    private static int projectId = 1;
    private static final String URL_DATA = "http://localhost:8000/api/projects/" + projectId;
    private static final String URL_DATA2 = "http://localhost:8000/api/projects/" + projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        projectViewIntent.putExtra(KEY_PROJECT_ID, currentProject.getProjectId());
        projectViewIntent.putExtra(KEY_PROJECT_STATUS, currentProject.getProjectStatus());
        projectViewIntent.putExtra(KEY_PROJECT_REVIEW, currentProject.getProjectItemRequestorName());
        projectViewIntent.putExtra(KEY_PROJECT_DESCRIPTION, currentProject.getProjectItemRequestorLocation());
        projectViewIntent.putExtra(KEY_PROJECT_PRICE, currentProject.getProjectItemRequestorPhone());
        projectViewIntent.putExtra(KEY_PROJECT_DELIVERY_TIME, currentProject.getProjectItemRequestorName());
        projectViewIntent.putExtra(KEY_APPUSER_INVITER_ID, currentProject.getProjectItemRequestorLocation());
        projectViewIntent.putExtra(KEY_APPUSER_FREELANCER_ID, currentProject.getProjectItemRequestorPhone());
        projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_NAME, currentProject.getProjectItemRequestorName());
        projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_LOCATION, currentProject.getProjectItemRequestorLocation());
        projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_PHONE, currentProject.getProjectItemRequestorPhone());

        //others
        projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_NAME, currentProject.getProjectItemRequestorName());
        projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_LOCATION, currentProject.getProjectItemRequestorLocation());
        projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_PHONE, currentProject.getProjectItemRequestorPhone());

        // receive intent from Projects view
        Intent openProjectViewIntent = getIntent();
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
            changeProjectProgress();
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

    public void changeProjectProgress(){
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA2,
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
                params.put("projectStatus", "pending");
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);
    }

    public void finishProject(){
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA,
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
                params.put("projectStatus", "pending");
                //params.put("domain", "http://itsalif.info");

                return params;
            }

        };

        queue.add(putRequest);
    }


}
