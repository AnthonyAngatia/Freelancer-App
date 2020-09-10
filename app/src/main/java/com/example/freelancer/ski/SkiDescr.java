package com.example.freelancer.ski;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.R;
import com.example.freelancer.req.RequestActivity;
import com.example.freelancer.req.RequestsAdapter;
import com.example.freelancer.req.RequestsList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


    public class SkiDescr extends AppCompatActivity{

        //api

        private static int userId = 1;
        private static String URL_DATA;
        //Nahejej guhindura endpoint, hasigay kuyipima
        private RecyclerView desRecyclerView;

        private DesAdapter desAdapter;

        private List<DesList> desList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ski_descr);

            //receive intent from FreelancerHome
            // Intent openProjectsIntent = getIntent();

            Intent intent = getIntent();
//            final String categoryName = "Music";

            final String categoryName = intent.getStringExtra(SkiAdapter.KEY_SKILL_NAME);
            URL_DATA = "https://sheltered-plains-24359.herokuapp.com/api/categories/" + categoryName;

            //initialize recycler view
            desRecyclerView = findViewById(R.id.ski_des_recycler_view);
            desRecyclerView.setHasFixedSize(true);
            desRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            //initialize the projectsList... THIS LIST WILL HOLD THE CONTENTS OF OUR REMOTE JSON AND PASS IT TO RECYCLERVIEW
            desList = new ArrayList<>();

            loadUrlData();
        }

        private void loadUrlData() {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.show();

            // Making HTTP Request and getting Response
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.w("res", "Response:"+ response);
                    progressDialog.dismiss();
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray arrayJSON = jsonObject.getJSONArray("data");
                        for(int i=0; i < arrayJSON.length();i++){
                            JSONObject jSONObject = arrayJSON.getJSONObject(i);

                            DesList request = new DesList(
                                    jSONObject.getString("name"),
                                    jSONObject.getString("image_url"),
                                    jSONObject.getString("created_at"),
                                    jSONObject.getString("updated_at"),
                                    Integer.parseInt(jSONObject.getString("skillsupercategory_id"))

                            );
                            desList.add(request);
                            Log.d("res", "data"+desList);
                        }
                        desAdapter = new DesAdapter(desList, getApplicationContext());
                        desRecyclerView.setAdapter(desAdapter);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                    Log.d("Tag", response);
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(com.example.freelancer.ski.SkiDescr.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            int MY_SOCKET_TIMEOUT_MS = 5000;
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        }



}