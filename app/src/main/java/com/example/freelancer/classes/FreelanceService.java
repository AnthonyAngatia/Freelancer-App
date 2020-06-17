package com.example.freelancer.classes;

import java.util.ArrayList;
import java.util.List;

public class FreelanceService {

    private List<String> services = new ArrayList<>();

    public FreelanceService(List<String> services) {
        this.services = services;
    }



    public void addServices(){
        services.add("Music");
        services.add("Videos & Animations");
        services.add("Design");
        services.add("Programming");
    }
}
