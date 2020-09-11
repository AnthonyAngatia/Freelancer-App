package com.example.freelancer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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
        holder.mTextDescription.setText(mFreeLancers.get(position).getName());
//        holder.mPosterImage.setImageResource(R.drawable.song);
        holder.mCurrentPosition = position;
        holder.freelancerId = mFreeLancers.get(position).getId();
        Picasso.with(mContext).load(mFreeLancers.get(position).getImageUrl()).into(holder.mPosterImage);
        holder.mRatingBar.setNumStars(mFreeLancers.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return mFreeLancers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mPosterImage;
        public final TextView mTextDescription;
        public int mCurrentPosition;
        public int freelancerId;
        private final RatingBar mRatingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setBackgroundColor(Color.parseColor("#00ff00"));
            mPosterImage = itemView.findViewById(R.id.poster_image);
            mTextDescription = itemView.findViewById(R.id.freelancer_name);
            mRatingBar = itemView.findViewById(R.id.rating_bar);
            //TODO More descriptions are to come
            itemView .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent anotherIntent = new Intent(mContext, FreelancerProfileActivity.class);
                    anotherIntent.putExtra(FreelancerProfileActivity.FREELANCER_ID, freelancerId);//ID of the user
                    mContext.startActivity(anotherIntent);
                }
            });

        }
    }
}
