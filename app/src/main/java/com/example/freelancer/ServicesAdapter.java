package com.example.freelancer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer.classes.ServiceSuperCategory;

import java.util.List;
/*
* This file is an adapter that binds the VIEW and DATA to a VIEWHOLDER
* It displays the General categories we have i.e Music,Programming, Videos, Art,
* */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    private final Context mContext;
    private List<String> servicesList;
    private List<Integer> imageList;
    private int mCurrentPosition;

    public ServicesAdapter(Context mContext, List<String> servicesList, List<Integer> imageList) {
        this.mContext = mContext;
        this.servicesList = servicesList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.item_services_card,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
        holder.textView.setText(servicesList.get(position));
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView textView;
        int mCurrentPosition;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_service);
            textView = itemView.findViewById(R.id.text_servicename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SpecificCategoryActivity.class);
                    //The id of the general category
                    intent.putExtra(SpecificCategoryActivity.CATEGORY_NAME, servicesList.get(mCurrentPosition));
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
