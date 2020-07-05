package com.example.freelancer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SuggestedProvidersAdapter extends RecyclerView.Adapter<SuggestedProvidersAdapter.ViewHolder> {
    private Context mContext;
    private List<String> freelancerName;

    public SuggestedProvidersAdapter(Context context, List<String> freelancerName) {
        mContext = context;
        this.freelancerName = freelancerName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.suggested_provider, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextDescription.setText(freelancerName.get(position));
        holder.mUserImage.setImageResource(R.drawable.song);
    }

    @Override
    public int getItemCount() {
        return freelancerName.size();
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
                    //TODO To be implemented
                }
            });

        }
    }
}
