package com.example.freelancer.ski;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.freelancer.R;
import com.example.freelancer.req.RequestDescrActivity;
import com.example.freelancer.req.RequestsList;
import com.squareup.picasso.Picasso;
import java.util.List;


    public class SkiAdapter extends RecyclerView.Adapter<com.example.freelancer.ski.SkiAdapter.ViewHolder> {

        private List<SkiList> skiList;

        private Context mContext;

        public static final String KEY_SKILL_ID = "skillsupercategory_id";
        public static final String KEY_SKILL_NAME = "name";
        public static final String KEY_SKILL_IMAGE = "image_url";
        public static final String KEY_SKILL_CREATED_AT = "created_at";
        public static final String KEY_SKILL_UPDATED_AT = "updated_at";


        public SkiAdapter(List<SkiList> skiList, Context context) {
            this.skiList = skiList;
            this.mContext = context;
        }


        public class ViewHolder extends RecyclerView.ViewHolder{

            public RelativeLayout ski_item_rellayout;
            public ImageView ski_item_imageView;
            public TextView ski_item_name;
            public TextView ski_item_created_at;
            public TextView ski_item_updated_at;


            public ViewHolder(@NonNull View itemView){
                super(itemView);
                ski_item_rellayout = itemView.findViewById(R.id.ski_item_rellayout);
                ski_item_imageView = itemView.findViewById(R.id.ski_item_imageView);
                ski_item_name = itemView.findViewById(R.id.ski_item_name);
                ski_item_created_at = itemView.findViewById(R.id.ski_item_created_at);
                ski_item_updated_at = itemView.findViewById(R.id.ski_item_updated_at);
            }
        }

        @NonNull
        @Override
        public com.example.freelancer.ski.SkiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new com.example.freelancer.ski.SkiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ski_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.freelancer.ski.SkiAdapter.ViewHolder holder, final int position) {

            final SkiList currentSki = skiList.get(position);

            holder.ski_item_name.setText(currentSki.getSkillSuperName());
            holder.ski_item_created_at.setText(currentSki.getSkillSuperCreatedAt());
            holder.ski_item_updated_at.setText(currentSki.getSkillSuperUpdatedAt());


            holder.ski_item_rellayout.setOnClickListener(new View.OnClickListener(){
                @Override
                //ensure you override the onClick method
                public void onClick(View v){

                    SkiList currentSki = skiList.get(position);

                    Intent viewIntent = new Intent(v.getContext(), SkiDescr.class);
                    //use intent EXTRA to pass data from SkiActivity to SkiDescrActivity
                    viewIntent.putExtra(KEY_SKILL_ID, currentSki.getSkillSuperId());
                    viewIntent.putExtra(KEY_SKILL_NAME, currentSki.getSkillSuperName());
                    viewIntent.putExtra(KEY_SKILL_IMAGE, currentSki.getSkillSuperImage());
                    viewIntent.putExtra(KEY_SKILL_CREATED_AT, currentSki.getSkillSuperCreatedAt());
                    viewIntent.putExtra(KEY_SKILL_UPDATED_AT, currentSki.getSkillSuperUpdatedAt());

                    v.getContext().startActivity(viewIntent);

                    //Code to make PUT request to update status of project clicked

                }
            });
        }

        @Override
        public int getItemCount() {
            //return size of ski list
            return skiList.size();
        }


}