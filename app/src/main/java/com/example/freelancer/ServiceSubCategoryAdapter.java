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

import java.util.ArrayList;
import java.util.List;

public class ServiceSubCategoryAdapter extends RecyclerView.Adapter<ServiceSubCategoryAdapter.ViewHolder> {
    private Context mContext;
    private List<String> subCategoryNames = new ArrayList<>();

    public ServiceSubCategoryAdapter(Context context, List<String> subCategoryNames) {
        mContext = context;
        this.subCategoryNames = subCategoryNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.specific_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextSubCategory.setText(subCategoryNames.get(position));
        holder.mImageSubCategory.setImageResource(R.drawable.development);
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return subCategoryNames.size();
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
                    Intent intent = new Intent(mContext, SuggestedProviders.class);
                    intent.putExtra(SuggestedProviders.SUB_CATEGORY_ID, mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
