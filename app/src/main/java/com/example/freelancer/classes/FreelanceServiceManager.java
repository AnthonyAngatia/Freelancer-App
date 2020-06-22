package com.example.freelancer.classes;

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
        servicesNames.add("Videos & Animations");
        servicesNames.add("Design");
        servicesNames.add("Programming");
        servicesNames.add("Photoshop");
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

}
