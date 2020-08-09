package com.example.freelancer.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.R;
import com.example.freelancer.ServicesAdapter;
import com.example.freelancer.classes.FreelanceServiceManager;
import com.example.freelancer.classes.ServiceSuperCategory;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final String TAG = "TAG";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initializeDisplayContent(root);
        return root;
    }
    private void initializeDisplayContent(View root) {
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_services);
        //LayoutManager
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

//        String requestString = FreelanceServiceManager.getInstance().volleyRequest(buildUrl(), getContext());
//        Log.d("TAG", requestString);//TODO Remove
        //Process the string
//        List<ServiceSuperCategory> categoryList = FreelanceServiceManager.getInstance().processCategories(requestString);
        //TODO This 2 might be replaced by the above
        List<Integer> imageList = FreelanceServiceManager.getInstance().getServicesImages();
        List<String>  serviceName =  FreelanceServiceManager.getInstance().getServicesNames();

        List<ServiceSuperCategory> categoryList;
        recyclerView.setAdapter(new ServicesAdapter(getContext(),serviceName,imageList));
    }

    private String buildUrl(){

        Uri uri =Uri.parse(FreelanceServiceManager.BASE_API_URL)
                .buildUpon()
                .appendPath("categories")
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

}
