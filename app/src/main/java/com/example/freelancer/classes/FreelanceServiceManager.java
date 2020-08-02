package com.example.freelancer.classes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.MainActivity;
import com.example.freelancer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/*
* This class processes the freelancing data gotten from the API*/
public class FreelanceServiceManager {

    private static FreelanceServiceManager ourinstance = null;
    public static final String BASE_API_URL = "http://localhost:8000/api";

    public static FreelanceServiceManager getInstance(){
        if(ourinstance == null){
            ourinstance = new FreelanceServiceManager();
            //INITIALIZE OUR DATA HERE
            ourinstance.initializeData();
        }
        return ourinstance;

    }

    private  void initializeData() {
        getServicesData();

    }


    /*
* @returns a list of service objects from the API e.g music, art, design etc.
*
* TODO: Change the return type
*  */
    public void getServicesData(){

    }
    /*
    * @returns a list<string> of services*/
    public List<String> getServicesNames(){
        //TODO:These objects will be processed from the data gotten from getServicesData
        List<String> servicesNames = new ArrayList<>();
        servicesNames.add("Music");
        servicesNames.add("Programming");
        servicesNames.add("Animations");
        servicesNames.add("Drawing");
        servicesNames.add("ETC");
        return servicesNames;
    }
    public List<Integer> getServicesImages(){
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.song);
        imageList.add(R.drawable.development);
        imageList.add(R.drawable.multimedia);
        imageList.add(R.drawable.paint);
        imageList.add(R.drawable.development);
        return imageList;
    }

    public ServiceSubCategory getSubCategory(Context context, int id) {
        getServicesNames().get(id);
        //Should we pass the id or the string as the URLi.e app.com/0, app.com/1 or app/music, app.com/art
        //This data should be replaced by data from the Api
        List<String> subCategoryName = new ArrayList<>();
        subCategoryName.add("VoiceOver");
        subCategoryName.add("Mixing");
        subCategoryName.add("Producers");
        subCategoryName.add("Instrumentalist");
        subCategoryName.add("Singers");
        TypedArray subCategoryImages = context.getResources().obtainTypedArray(R.array.music);
        List<Integer> imageIdList = new ArrayList<>();
        for( int i=0; i < subCategoryImages.length();i++){
            imageIdList.add(subCategoryImages.getResourceId(i, -1));
        }
        subCategoryImages.recycle();
        return new ServiceSubCategory(subCategoryName, imageIdList );

    }

    public List<String> getFreelancer(int subCategoryId) {
        //Get a list of Freelancers doing this job
        //TODO : Getting freelancers
        //Ideally, it should get the object from the  API
        List<String> freelancerNames = new ArrayList<>();
        freelancerNames.add("Anthony Angatia");
        freelancerNames.add("Anthony Angatia");
        freelancerNames.add("Anthony Angatia");
        freelancerNames.add("Anthony Angatia");
        freelancerNames.add("Anthony Angatia");
        freelancerNames.add("Anthony Angatia");
        return freelancerNames;
    }

    public String volleyRequest(String url, Context context){
        final String TAG = "VolleyRequest";
        final String[] responseString = {"No request made to the API"};
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        url ="https://api.github.com/search/users?q=language:java+location:nairobi";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        responseString[0] = response.toString();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseString[0] = "Error occurred retrieving data";
                        Log.d(TAG, error.getMessage());
                    }
                });
        requestQueue.add(getRequest);
        return responseString[0];
    }
//    public String volleyRequest(String url, Context context){
//        final String URL_DATA =  "https://api.github.com/search/users?q=language:java+location:nairobi";
////        final String URL_DATA =  "http://localhost:8000/api/categories";
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("Tag", response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("TAG", error.getMessage());
//
//            }
//        });
//        queue.add(req);
//        return "vhbj";
//
//    }




    public ArrayList<ServiceSuperCategory> processCategories(String requestString)  {
        final String DATA = "data";
        final String NAME = "name";
        final String IMAGE = "image_url";
        ArrayList<ServiceSuperCategory> serviceSuperCategoryArrayList = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(requestString);
            JSONArray arrayCategories = jsonObject.getJSONArray(DATA);
            int numberOfCategories = arrayCategories.length();
            for (int i=0; i< numberOfCategories; i++){
                JSONObject categoriesJson = arrayCategories.getJSONObject(i);
                ServiceSuperCategory superCategory = new ServiceSuperCategory(
                        categoriesJson.getString(NAME),
                        categoriesJson.getString(IMAGE)
                );
                serviceSuperCategoryArrayList.add(superCategory);
            }
        }catch(JSONException e){
            Log.d("TAG", "ERROR PROCESSING SUPERCATEGORIES JSON");
            Log.d("TAG", e.getMessage());
            e.printStackTrace();

        }
    return serviceSuperCategoryArrayList;
    }
}
