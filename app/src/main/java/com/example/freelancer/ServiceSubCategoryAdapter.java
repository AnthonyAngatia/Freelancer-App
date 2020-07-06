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

import com.example.freelancer.classes.ServiceSubCategory;

public class ServiceSubCategoryAdapter extends RecyclerView.Adapter<ServiceSubCategoryAdapter.ViewHolder> {
    private Context mContext;
    private ServiceSubCategory mServiceSubCategory;

    public ServiceSubCategoryAdapter(Context context, ServiceSubCategory mServiceSubCategory) {
        mContext = context;
        this.mServiceSubCategory = mServiceSubCategory;
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
        holder.mTextSubCategory.setText(mServiceSubCategory.getSubCategoryTitles().get(position));
        holder.mImageSubCategory.setImageResource(mServiceSubCategory.getSubCategoryImages().get(position));
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mServiceSubCategory.getSubCategoryTitles().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mImageSubCategory;
        public final TextView mTextSubCategory;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageSubCategory = itemView.findViewById(R.id.category_image);
            //Rename to subcategory title
            mTextSubCategory = itemView.findViewById(R.id.specific_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SuggestedProvidersActivity.class);
                    intent.putExtra(SuggestedProvidersActivity.SUB_CATEGORY_ID, mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
