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

import com.example.freelancer.classes.FreeLancer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SuggestedProvidersAdapter extends RecyclerView.Adapter<SuggestedProvidersAdapter.ViewHolder> {
    private Context mContext;
    private List<FreeLancer> mFreeLancers;

    public SuggestedProvidersAdapter(Context context, List<FreeLancer> freeLancers) {
        mContext = context;
        mFreeLancers = freeLancers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.item_suggested_provider, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextDescription.setText(mFreeLancers.get(position).toString());
        holder.mUserImage.setImageResource(R.drawable.song);
//        Picasso.with(mContext).load(mFreeLancers.get(position).getImageUrl()).into(holder.mUserImage);
    }

    @Override
    public int getItemCount() {
        return mFreeLancers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mUserImage;
        public final TextView mTextDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserImage = itemView.findViewById(R.id.freelancer_profile_image);
            mTextDescription = itemView.findViewById(R.id.text_description);
            //TODO More descriptions are to come

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SpecificCategoryActivity.class);
                    //The id of the general category
//                    intent.putExtra(SpecificCategoryActivity.CATEGORY_NAME, servicesList.get(mCurrentPosition));
//                    mContext.startActivity(intent);
                }
            });

        }
    }
}
