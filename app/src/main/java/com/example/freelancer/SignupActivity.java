package com.example.freelancer;

import androidx.appcompat.app.AppCompatActivity;

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
//                if(password.getText() != passwordConfirm.getText()){
//                    Toast.makeText(SignupActivity.this, "Please make sure the inputs of password field and the confirm password field matches", Toast.LENGTH_SHORT).show();
//                }
//                else{
                    //Write code to make a post request to the api

                    String url = "https://sheltered-plains-24359.herokuapp.com/api/appusers";
                    RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);

                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
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
                                    Log.d("Error.Response", error.getMessage());
                                }
                            }
                    ) {
                        @Override
                        protected Map getParams()
                        {
                            Map params = new HashMap();
                            params.put("appuser_name", "Alif");
                            params.put("appuser_pass", 12345678);
                            params.put("appuser_fname", "reerere");


                            return params;
                        }
                    };
                    queue.add(postRequest);

                    //Return the person object
                    //Redirect to the login page
//                }

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
