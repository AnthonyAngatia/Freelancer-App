package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.classes.FreelanceServiceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.freelancer.classes.FreelanceServiceManager.isFreelancer;
import static com.example.freelancer.classes.FreelanceServiceManager.isUserLogged;
import static com.example.freelancer.classes.FreelanceServiceManager.loginPreference;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserEmailEditText;
    private EditText mUserPasswordEditText;
    private String mEmail;
    private String mPass;
    private TextView mRegister;
    private Button mLoginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserEmailEditText = findViewById(R.id.username);
        mUserPasswordEditText = findViewById(R.id.password);
        mRegister = findViewById(R.id.textView_signup);
        mLoginbtn = findViewById(R.id.btn_login);
        login();
        signUp();

        Intent arrivedAtLogin = getIntent();
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

    private void login() {
        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:Check if the textviews are empty
                mEmail = mUserEmailEditText.getText().toString();
                mPass = mUserPasswordEditText.getText().toString();
                String url = buildUrl();
                //TODO Method to sign in
                //volleyRequest(url, LoginActivity.this);
                SharedPreferences preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
                SharedPreferences.Editor editor =  preferences.edit();
                editor.putBoolean(isUserLogged, true); //updating
                editor.commit();

                //call function to get user details and put ID in SharedPreference
                getLoginDetails(mUserEmailEditText.getText().toString());

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signUp() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void volleyRequest(String url, Context context){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        final String TAG = "VolleyRequest";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d(TAG, response.toString());
                        //Process the data here
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                });
        requestQueue.add(getRequest);
    }

    private String buildUrl(){

        Uri uri =Uri.parse(FreelanceServiceManager.BASE_API_URL)
                .buildUpon()
                .appendPath("appusers")
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.d("TAG", "BAD URL CREATED AT buildUrl");
            e.printStackTrace();
        }
        return  url.toString();
    }
//    public void processAppUsers(String jsonStringResponse){
//        final String NAME = "appuser_name";
//        final String ID = "appuser_id";
//        final String PASS="appuser_password";
//
//        try {
//            JSONObject jsonObject  = new JSONObject(jsonStringResponse);
//            //TODO: Ask Harry how he retrieved his
//            JSONArray arrayOfUsers = jsonObject.getJSONArray('');
//            int noOfUsers = arrayOfUsers.length();
//            for (int i=0; i< noOfUsers; i++){
//                JSONObject userJson = arrayOfUsers.getJSONObject(i);
//                if(userJson.getString(NAME).equalsIgnoreCase(mEmail)){
//                    //Only use the email to easen your work
//                    if(userJson.getString(PASS).equalsIgnoreCase(mPass)){
//                        SharedPreferences.Editor editor = mSharedpreferences.edit();
//                        editor.putString("User_Name", userJson.getString(NAME));
//                        editor.putInt("Id", userJson.getInt(ID));
//                        editor.putBoolean("IsUserLoggedIn", true);
//                        editor.apply();
//                    }
//                    break;
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void getLoginDetails(String appUserEmail){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();


        String URL_DATA;
        //URL_DATA = "http://172.20.10.2:80/FreelancerAPIV1/Freelancer_API_V1/public/api/appusers/login/" + appUserEmail;
        URL_DATA = "http://sheltered-plains-24359.herokuapp.com/api/appusers/login/" + appUserEmail;

        // Making HTTP Request and getting Response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginActivity.this, "Response"+response.toString(), Toast.LENGTH_LONG).show();
                Log.d("res", "Response:"+ response);
                progressDialog.dismiss();

                //getting the data
                try {
                    int mId = 1000;
                    //JSONObject jsonObject = new JSONObject(response);
                    //JSONObject userJsonObject = jsonObject.getJSONObject("data");
                    //mId = userJsonObject.getInt("appuser_id");

                    JSONObject jsonObject2 = new JSONObject(response);
                    JSONArray appUserArrayJSON = jsonObject2.getJSONArray("data");
                    //logging response
                    //String responseValue = jsonObject.getString("response");
                    Log.d("Response", response);


                    for(int i=0; i < appUserArrayJSON.length();i++){
                        JSONObject appUserJSONObject = appUserArrayJSON.getJSONObject(i);
                        String mIdTemp = appUserJSONObject.getString("appuser_id");
                        mId = Integer.parseInt(mIdTemp);
                    }
                    //put data in sharedPreference
                    SharedPreferences preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putInt("id", mId );//Id gotten from the api
                    editor.commit();
                    Log.d("mId", String.valueOf(mId));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("RespError", e.getMessage());
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error"+error.toString(), Toast.LENGTH_LONG).show();
                Log.d("ErrorLogin", error.toString());
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

        //adding request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
