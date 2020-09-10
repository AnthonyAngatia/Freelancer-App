package com.example.freelancer.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/*
* This class processes the freelancing data gotten from the API*/
public class FreelanceServiceManager {

    private static FreelanceServiceManager ourinstance = null;
    public static final String BASE_API_URL = "https://sheltered-plains-24359.herokuapp.com/api/";
    //Preference variables
        public static final String loginPreference = "LOGIN";
    public static final String name = "NameKey";
    public static final String isUserLogged = "IsUserLoggedIn";
    public static final String isFreelancer = "isFreelancer";
    public static final String email = "EmailKey";
    private ArrayList<FreeLancer> mFreeLancers = new ArrayList<>();

    public ArrayList<FreeLancer> getFreelancersList(){
        return mFreeLancers;
    }
    public ArrayList<FreeLancer> setFreelancersList(ArrayList<FreeLancer> freelancers){
        this.mFreeLancers = freelancers;
        return this.mFreeLancers;
    }
    public void setFreelancersList(FreeLancer freeLancer){
        this.mFreeLancers.add(freeLancer);
    }
    public FreeLancer getFreelancerFromArrayList(int id){
        for(FreeLancer freeLancer: mFreeLancers){
            if(freeLancer.getId() == id){
                return freeLancer;
            }

        }
        return null;
    }

    public static FreelanceServiceManager getInstance(){
        if(ourinstance == null){
            ourinstance = new FreelanceServiceManager();
            //INITIALIZE OUR DATA HERE
            ourinstance.initializeData();
        }
        return ourinstance;

    }



    private  void initializeData() {


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
//TODO try use it in the sub categories stuff
    public String volleyRequest(String url, Context context){
        final String TAG = "VolleyRequest";
        final String[] responseString = {"No request made to the API"};
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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

//Not in use deprecated
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
