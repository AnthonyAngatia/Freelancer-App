package com.example.freelancer.ui.home;

import android.os.Bundle;
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

import com.example.freelancer.R;
import com.example.freelancer.ServicesAdapter;
import com.example.freelancer.classes.FreelanceServiceManager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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

        List<Integer> imageList = FreelanceServiceManager.getInstance().getServicesImages();
        List<String>  serviceName =  FreelanceServiceManager.getInstance().getServicesNames();

        recyclerView.setAdapter(new ServicesAdapter(getContext(),serviceName,imageList));
    }
}
