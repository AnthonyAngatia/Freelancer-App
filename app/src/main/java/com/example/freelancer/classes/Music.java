package com.example.freelancer.classes;

import com.example.freelancer.classes.music.Mixing;
import com.example.freelancer.classes.music.Producers;
import com.example.freelancer.classes.music.Singers;
import com.example.freelancer.classes.music.VoiceOver;

import java.util.ArrayList;
import java.util.List;

public class Music {
    VoiceOver voiceOver;
    Mixing mixing;
    Producers producers;
    Singers singers;
    List<Object> musicCategories = new ArrayList<>();//Should Hold a list of the above objects
    //Use a builder pattern to initialize all classes the class

}
