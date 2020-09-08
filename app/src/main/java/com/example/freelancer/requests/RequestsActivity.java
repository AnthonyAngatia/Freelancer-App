package com.example.freelancer.requests;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity {
    //api
    private static final String URL_DATA =
            "https://api.github.com/search/users?q=language:java+location:nairobi";


    private RecyclerView recyclerView;
    private RequestsAdapter myAdapter;
    private List<RequestsList> developerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        developerList = new ArrayList<>();
        loadUrlData();
    }

    private void loadUrlData(){
        final  ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Log.w("res","Response:"+response);
                progressDialog.dismiss();
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("items");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject jo = array.getJSONObject(i);
                        RequestsList developers = new RequestsList(jo.getString("login"),jo.getString("html_url"), jo.getString("avatar_url"));
                        developerList.add(developers);
                        Log.d("res","developers"+ developers);
                    }
                    myAdapter = new RequestsAdapter(developerList, getApplicationContext());
                    recyclerView.setAdapter(myAdapter);

                } catch (JSONException e){
                    e.printStackTrace();
                }
                Log.d("Tag",response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(RequestsActivity.this,"Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

