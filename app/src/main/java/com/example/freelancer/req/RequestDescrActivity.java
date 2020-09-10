package com.example.freelancer.req;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.FreelancerProfileActivity;
import com.example.freelancer.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class RequestDescrActivity extends AppCompatActivity {

    private static String URL_DATA_UPDATE_PROGRESS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_descr);

        //ImageView profileImageView = findViewById(R.id.profileImageView);
        //TextView userNameTextView = findViewById(R.id.usernameTextView);
        //ImageButton shareProfile = findViewById(R.id.shareProfile);
        //TextView developerUrl = findViewById(R.id.developerUrl);

        TextView reqDescriptionView = findViewById(R.id.reqdes_description);
        TextView reqRequestorNameView = findViewById(R.id.reqdes_item_requestor_name);
        TextView reqRequestorPhoneView = findViewById(R.id.reqdes_item_requestor_phone);
        TextView reqRequestorLocationView = findViewById(R.id.reqdes_item_requestor_location);

        Intent intent = getIntent();
        //final String userName = intent.getStringExtra(DevelopersAdapter.KEY_NAME);

        final String reqdescription = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_DESCRIPTION);
        final String reqname = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_REQUESTOR_NAME);
        final String reqphone = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_REQUESTOR_PHONE);
        final String reqlocation = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_REQUESTOR_LOCATION);

        final String projectId = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_ID);
        URL_DATA_UPDATE_PROGRESS = "http://sheltered-plains-24359.herokuapp.com/api/projects/approve/" + projectId;


        //userNameTextView.setText(userName);
        reqDescriptionView.setText(reqdescription);
        reqRequestorNameView.setText(reqname);
        reqRequestorPhoneView.setText(reqphone);
        reqRequestorLocationView.setText(reqlocation);

//        developerUrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = profileUrl;
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//            }
//        });



    }


    public void changeRequestStatus(View view) {

//        Toast.makeText(RequestDescrActivity.this, "Added to projects list", Toast.LENGTH_SHORT).show();
        updateProjectStatus();

    }

    public void updateProjectStatus(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Approving...");
        pDialog.show();
        StringRequest putRequest = new StringRequest(Request.Method.PUT, URL_DATA_UPDATE_PROGRESS,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        pDialog.hide();
                        Toast.makeText(RequestDescrActivity.this, "Added to projects list", Toast.LENGTH_SHORT).show();

                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        // find out how to log the response itself here
                        Log.d("Error.Response", "Error Response");
                        pDialog.hide();
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("project_status", "uncomplete");


                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(putRequest);
    }
}