package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import java.util.List;

/*
* This is an Activity contains
* */
public class SpecificCategoryActivity extends AppCompatActivity {
//    The category ID is the position of the category in its datastructure i.e the arraylist index
    public static final String CATEGORY_NAME = "com.example.freelancer.CATEGORY_NAME";
    private static final int POSITION_NOT_SET = -1;//Helps in debugging
    private RecyclerView mRecyclerSubCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_category);
        mRecyclerSubCategories = findViewById(R.id.recycler_category);

        Intent intent = getIntent();
        String name = intent.getStringExtra(CATEGORY_NAME);

        String url = buildUrl(name);
        volleyRequest(url, this);

    }
    private String buildUrl(String subCategory){

        Uri uri =Uri.parse(FreelanceServiceManager.BASE_API_URL)
                .buildUpon()
                .appendPath("categories")
                .appendPath(subCategory)
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
    public void volleyRequest(String url, Context context){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        final String TAG = "SpecCatAct";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<ServiceSubCategory> serviceSubCategories = new ArrayList<>();
                        progressDialog.dismiss();
                        Log.d(TAG, response.toString());
                         serviceSubCategories = processSubCategories(response.toString());
                        //LayoutManager
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SpecificCategoryActivity.this);
                        mRecyclerSubCategories.setLayoutManager(linearLayoutManager);
                        ServiceSubCategoryAdapter subCategoryAdapter = new ServiceSubCategoryAdapter(SpecificCategoryActivity.this,serviceSubCategories);
                         mRecyclerSubCategories.setAdapter(subCategoryAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, error.getMessage());
                        Toast.makeText(SpecificCategoryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(getRequest);
    }
    public ArrayList<ServiceSubCategory> processSubCategories(String jsonStringResponse){
        final String DATA = "data";
        final String NAME = "name";
        final String IMAGE = "image_url";
        ArrayList<ServiceSubCategory> serviceSubCategoriesArrayList = new ArrayList<>();

        try {
            JSONObject jsonObject  = new JSONObject(jsonStringResponse);
            JSONArray arrayOfSubCategories = jsonObject.getJSONArray(DATA);
            int numberOfSubCategories = arrayOfSubCategories.length();
            for (int i=0; i< numberOfSubCategories; i++){
                JSONObject subCategoryJson = arrayOfSubCategories.getJSONObject(i);
                ServiceSubCategory subCategories = new ServiceSubCategory(
                        subCategoryJson.getString(NAME),
                        subCategoryJson.getString(IMAGE)
                );
                serviceSubCategoriesArrayList.add(subCategories);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return serviceSubCategoriesArrayList;



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

}
