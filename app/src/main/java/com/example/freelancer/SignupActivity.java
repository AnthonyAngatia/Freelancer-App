package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText mEmailAddress;
    private EditText mPhoneNo;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private TextView mLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mEmailAddress = findViewById(R.id.username);
        mPhoneNo = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);
        mPasswordConfirm = findViewById(R.id.confirmPassword);
        mLoginText = findViewById(R.id.textView_login);
        Button btn = findViewById(R.id.btn_signUp);
        login();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                1. Check if the inputs are empty

//                2.Make a post request using volley
                String url = "https://sheltered-plains-24359.herokuapp.com/api/appusers";
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
                progressDialog.setMessage("Loading....");
                progressDialog.show();
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Error connecting to the server.Please ensure you are connected to the internet", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map getParams()
                    {
                        Map params = new HashMap();
                        params.put("appuser_name", "Anthony");
                        params.put("appuser_pass", "12345678");
                        params.put("appuser_fname", "Tony");

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
    }

    private void login() {
        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
////                if(password.getText() != passwordConfirm.getText()){
////                    Toast.makeText(SignupActivity.this, "Please make sure the inputs of password field and the confirm password field matches", Toast.LENGTH_SHORT).show();
////                }
////                else{
//                    //Write code to make a post request to the api
//
//                    String url = "https://sheltered-plains-24359.herokuapp.com/api/appusers";
//                    RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
//                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
//                progressDialog.setMessage("Loading....");
//                progressDialog.show();
//
//                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                            new Response.Listener<String>()
//                            {
//                                @Override
//                                public void onResponse(String response) {
//                                    // response
//                                    progressDialog.dismiss();
//                                    Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();
//                                    Log.d("Response", response);
//                                }
//                            },
//                            new Response.ErrorListener()
//                            {
//
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    // error
//                                    Log.d("Error.Response", error.getMessage());
//                                    progressDialog.dismiss();
//                                    Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                                }
//                            }
//                    ) {
//                        @Override
//                        protected Map getParams()
//                        {
//                            Map params = new HashMap();
//                            params.put("appuser_name", "Alif");
//                            params.put("appuser_pass", 12345678);
//                            params.put("appuser_fname", "reerere");
////                            Toast.makeText(SignupActivity.this, "At the params", Toast.LENGTH_SHORT).show();
//                            return params;
//                        }
//                    };
//                    queue.add(postRequest);
//
//                    //Return the person object
//                    //Redirect to the login page
////                }

