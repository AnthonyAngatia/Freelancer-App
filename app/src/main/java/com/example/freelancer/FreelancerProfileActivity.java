package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.classes.FreeLancer;
import com.example.freelancer.classes.FreelanceServiceManager;

import java.util.HashMap;
import java.util.Map;

public class FreelancerProfileActivity extends AppCompatActivity {
    public static final String FREELANCER_ID = "com.example.freelancer.FREELANCER_ID";
    Button requestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        requestBtn = findViewById(R.id.button_request);
        ImageView imageViewProfile = findViewById(R.id.imageView_Profile);//Not in use
        TextView textViewName = findViewById(R.id.name);
        TextView textViewPhone = findViewById(R.id.phone_freelancer_profile);
        TextView textViewLocation = findViewById(R.id.location);
        TextView textViewDescription = findViewById(R.id.description_freelancer_profile);

        Intent intent = getIntent();
        int id = intent.getIntExtra(FREELANCER_ID, -1);
        //Bug
        FreeLancer freeLancer = FreelanceServiceManager.getInstance().getFreelancerFromArrayList(id);

        Log.d("FREELANCER", freeLancer.toString());


        textViewName.setText(freeLancer.getName());
        textViewPhone.setText(Integer.toString(freeLancer.getPhone_no()));
        textViewDescription.setText(freeLancer.getQualification());
        textViewLocation.setText(freeLancer.getLocation());
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FreelancerProfileActivity.this, "Request made. Please wait for the response. Thank you", Toast.LENGTH_SHORT).show();
//                2.Make a post request using volley
//                String url = "https://sheltered-plains-24359.herokuapp.com/api/projects";
//                RequestQueue queue = Volley.newRequestQueue(FreelancerProfileActivity.this);
//                final ProgressDialog progressDialog = new ProgressDialog(FreelancerProfileActivity.this);
//                progressDialog.setMessage("Loading....");
//                progressDialog.show();
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // response
//                                progressDialog.dismiss();
//                                Toast.makeText(FreelancerProfileActivity.this, " Request made successfully", Toast.LENGTH_SHORT).show();
//                                Log.d("Response", response);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                progressDialog.dismiss();
//                                Log.d("Response", error.getMessage() );
//                                Toast.makeText(FreelancerProfileActivity.this, "Error connecting to the server.Please ensure you are connected to the internet", Toast.LENGTH_SHORT).show();
////                                Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }){
//                    @Override
//                    protected Map getParams()
//                    {
//                        Map params = new HashMap();
////                        final String firstName = mFirstName.getText().toString();
////                        final String lastName = mLastName.getText().toString();
////                        final String email = mEmailAdd.getText().toString();
////                        final String password = mPassword.getText().toString();
////                        final String phone = mPhoneNo.getText().toString();
////                        params.put("appuser_fname", firstName);
////                        params.put("appuser_lname", lastName);
////                        params.put("appuser_email", email);
////                        params.put("appuser_phone", phone);
////                        params.put("appuser_pass", password);
//// For debugging purposes
////                        params.put("project_status", "Anthony");
////                        params.put("project_description", "12345678");
////                        params.put("appuser_inviter_id", "1");
////                        params.put("appuser_freelancer_id", "2");
//
//                        return params;
//                    }
//                };
//                queue.add(postRequest);
            }
        });
    }
}