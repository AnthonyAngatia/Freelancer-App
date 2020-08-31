package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.classes.FreelanceServiceManager;
import com.example.freelancer.classes.ServiceSubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
    }

    private void login() {
        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:Check if the textviews are empty
                mEmail = mUserEmailEditText.getText().toString();
                mPass = mUserPasswordEditText.getText().toString();
                String url = buildUrl();
//                volleyRequest(url, LoginActivity.this);
                SharedPreferences preferences = getSharedPreferences(MainActivity.myPreference, MODE_PRIVATE);
                SharedPreferences.Editor editor =  preferences.edit();
                editor.putBoolean("IsUserLoggedIn", true);
                editor.commit();
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

}
