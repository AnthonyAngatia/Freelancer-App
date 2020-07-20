package com.example.freelancer.classes;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.freelancer.R;

import java.util.ArrayList;
import java.util.List;
/*
* This class processes the freelancing data gotten from the API*/
public class FreelanceServiceManager {

    private static FreelanceServiceManager ourinstance = null;

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
}
