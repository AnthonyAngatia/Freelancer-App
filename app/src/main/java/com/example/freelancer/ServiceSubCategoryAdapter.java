package com.example.freelancer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer.classes.ServiceSubCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceSubCategoryAdapter extends RecyclerView.Adapter<ServiceSubCategoryAdapter.ViewHolder> {
    private Context mContext;
    private String  TAG = "SubCatAdapter";
//    private ServiceSubCategory mServiceSubCategory;
    private List<ServiceSubCategory> mServiceSubCategoryList;

    public ServiceSubCategoryAdapter(Context context, List<ServiceSubCategory> serviceSubCategoryList) {
        mContext = context;
        mServiceSubCategoryList = serviceSubCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.item_specific_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextSubCategory.setText(mServiceSubCategoryList.get(position).getName());
        Picasso.with(mContext).load(mServiceSubCategoryList.get(position).getImageUrl()).into(holder.mImageSubCategory);
//        holder.mImageSubCategory.setImageResource(R.drawable.song);
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mServiceSubCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mImageSubCategory;
        public final TextView mTextSubCategory;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackgroundColor(Color.parseColor("#00bcd4"));
            mImageSubCategory = itemView.findViewById(R.id.category_image);
            //Rename to subcategory title
            mTextSubCategory = itemView.findViewById(R.id.specific_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SuggestedProvidersActivity.class);
                    intent.putExtra(SuggestedProvidersActivity.SUB_CATEGORY_ID, mCurrentPosition);
                    Log.d(TAG, Integer.toString(mCurrentPosition));
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
