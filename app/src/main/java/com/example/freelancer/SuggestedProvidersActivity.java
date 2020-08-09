package com.example.freelancer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.classes.FreeLancer;
import com.example.freelancer.classes.FreelanceServiceManager;
import com.example.freelancer.classes.ServiceSubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
* This activity handles the display of various artist performing a certain
* subcategory of freelance service
* We should be able to filter and search from this Activity
* */
public class SuggestedProvidersActivity extends AppCompatActivity {

    public final static String SUB_CATEGORY_NAME = "com.example.freelancer.SUB_CATEGORY_NAME";
    private static final int POSITION_NOT_SET = -1;
    private RecyclerView mFreelancersRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_providers);
        mFreelancersRecycler = findViewById(R.id.recycler_providers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mFreelancersRecycler.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String name = intent.getStringExtra(SUB_CATEGORY_NAME);
        String url = buildUrl(name);
        volleyRequest(url);

    }
    private String buildUrl(String subCategory){

        Uri uri =Uri.parse(FreelanceServiceManager.BASE_API_URL)
                .buildUpon()
                .appendPath("categories")
                .appendPath("music")
                .appendPath(subCategory.toString())
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
    public void volleyRequest(String url){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        final String TAG = "VolleyRequest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<FreeLancer> freeLancers;
                        progressDialog.dismiss();
                        Log.d(TAG, response.toString());
                        freeLancers = processFreelancer(response.toString());
                        SuggestedProvidersAdapter freelancerAdapter = new SuggestedProvidersAdapter(SuggestedProvidersActivity.this, freeLancers);
                        mFreelancersRecycler.setAdapter(freelancerAdapter);
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
    public ArrayList<FreeLancer> processFreelancer(String jsonStringResponse){
        final String DATA = "data";
        final String NAME = "appuser_name";
        final String QUALIFICATION = "appuser_qualifications";
        final String PORTFOLIO ="appuser_portfolio";
        final String RATING ="appuser_rating";
        final String IMAGE = "appuser_profile_img";
        ArrayList<FreeLancer> freeLancerArrayList = new ArrayList<>();

        try {
            JSONObject jsonObject  = new JSONObject(jsonStringResponse);
            JSONArray arrayOfFreelancer = jsonObject.getJSONArray(DATA);
            int noOfFreelancers = arrayOfFreelancer.length();
            for (int i=0; i< noOfFreelancers; i++){
                JSONObject freelancerJsonObject = arrayOfFreelancer.getJSONObject(i);
                FreeLancer freeLancer = new FreeLancer(
                        freelancerJsonObject.getString(NAME),
                        freelancerJsonObject.getString(IMAGE),
                        freelancerJsonObject.getString(RATING),
                        freelancerJsonObject.getString(QUALIFICATION),
                        freelancerJsonObject.getString(PORTFOLIO)
                );
                freeLancerArrayList.add(freeLancer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return freeLancerArrayList;



    }

}
