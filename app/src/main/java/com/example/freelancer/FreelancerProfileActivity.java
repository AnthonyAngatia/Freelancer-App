package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.example.freelancer.classes.FreelanceServiceManager.isUserLogged;
import static com.example.freelancer.classes.FreelanceServiceManager.loginPreference;

public class FreelancerProfileActivity extends AppCompatActivity {
    public static final String FREELANCER_ID = "com.example.freelancer.FREELANCER_ID";
    Button requestBtn;
    private int mUser_id;
    private FreeLancer mFreeLancer;


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

        final SharedPreferences sharedPreferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        if( sharedPreferences.getBoolean(isUserLogged, false)){
            mUser_id = sharedPreferences.getInt("id",-1);
            if(mUser_id == -1)
                Toast.makeText(this, "ID is -1", Toast.LENGTH_SHORT).show();
        }else{
            //Login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


        Intent intent = getIntent();
        int id = intent.getIntExtra(FREELANCER_ID, -1);
        //Bug
        mFreeLancer = FreelanceServiceManager.getInstance().getFreelancerFromArrayList(id);

        if(mFreeLancer == null){
            Toast.makeText(this, "Freelancer object is empty", Toast.LENGTH_SHORT).show();
//            Log.d("FREELANCER", freeLancer.toString());
        }
        Log.d("FREELANCER", mFreeLancer.toString());


        textViewName.setText(mFreeLancer.getName());
        textViewPhone.setText(Integer.toString(mFreeLancer.getPhone_no()));
        textViewDescription.setText(mFreeLancer.getQualification());
        textViewLocation.setText(mFreeLancer.getLocation());
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FreelancerProfileActivity.this, "Request made. Please wait for the response. Thank you", Toast.LENGTH_SHORT).show();
//                2.Make a post request using volley
                String url = "https://sheltered-plains-24359.herokuapp.com/api/projects";
                RequestQueue queue = Volley.newRequestQueue(FreelancerProfileActivity.this);
                final ProgressDialog progressDialog = new ProgressDialog(FreelancerProfileActivity.this);
                progressDialog.setMessage("Loading....");
                progressDialog.show();
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                progressDialog.dismiss();
                                Toast.makeText(FreelancerProfileActivity.this, " Request made successfully", Toast.LENGTH_SHORT).show();
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Log.d("Response", error.getMessage() );
//                                Toast.makeText(FreelancerProfileActivity.this, "Error connecting to the server.Please ensure you are connected to the internet", Toast.LENGTH_SHORT).show();
                                Toast.makeText(FreelancerProfileActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map getParams()
                    {
                        Map params = new HashMap();
                        params.put("project_status", "pendingFreelancerApproval");
                        params.put("project_description", "Please do for me this and that work");
                        params.put("appuser_inviter_id", Integer.toString(mUser_id));
                        params.put("appuser_freelancer_id", Integer.toString(mFreeLancer.getId()));

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
    }
}