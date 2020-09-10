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


    public class DesAdapter extends RecyclerView.Adapter<com.example.freelancer.ski.DesAdapter.ViewHolder> {

        private List<DesList> desList;

        private Context mContext;

        public static final String KEY_SUB_SKILL_ID = "id";
        public static final String KEY_SUB_SKILL_NAME = "name";
        public static final String KEY_SUB_SKILL_IMAGE = "image_url";
        public static final String KEY_SUB_SKILL_CREATED_AT = "created_at";
        public static final String KEY_SUB_SKILL_UPDATED_AT = "updated_at";
        public static final String KEY_SUB_SKILL_DESCRIPTION = "description";
        public static final String KEY_SUB_SKILL_APPUSER_ID = "appuser_id";
        public static final String KEY_SUB_SKILL_SUPERID = "skillsupercategory_id";


        public DesAdapter(List<DesList> desList, Context context) {
            this.desList = desList;
            this.mContext = context;
        }


        public class ViewHolder extends RecyclerView.ViewHolder{

            public RelativeLayout des_item_rellayout;
            public ImageView des_item_imageView;
            public TextView des_item_name;
            public TextView des_item_created_at;
            public TextView des_item_updated_at;


            public ViewHolder(@NonNull View itemView){
                super(itemView);
                des_item_rellayout = itemView.findViewById(R.id.ski_des_item_rellayout);
                des_item_imageView = itemView.findViewById(R.id.ski_des_item_imageView);
                des_item_name = itemView.findViewById(R.id.ski_des_item_name);
                des_item_created_at = itemView.findViewById(R.id.ski_des_item_created_at);
                des_item_updated_at = itemView.findViewById(R.id.ski_des_item_updated_at);
            }
        }

        @NonNull
        @Override
        public com.example.freelancer.ski.DesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new com.example.freelancer.ski.DesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ski_des_list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.freelancer.ski.DesAdapter.ViewHolder holder, final int position) {

            final DesList current = desList.get(position);

            holder.des_item_name.setText(current.getSkillSubName());
            holder.des_item_created_at.setText(current.getSkillSubCreatedAt());
            holder.des_item_updated_at.setText(current.getSkillSubUpdatedAt());


            holder.des_item_rellayout.setOnClickListener(new View.OnClickListener(){
                @Override
                //ensure you override the onClick method
                public void onClick(View v){

                    DesList current = desList.get(position);

                    Intent viewIntent = new Intent(v.getContext(), AllAdded.class);
                    //use intent EXTRA to pass data from SkiActivity to SkiDescrActivity
                    viewIntent.putExtra(KEY_SUB_SKILL_NAME, current.getSkillSubName());
                    viewIntent.putExtra(KEY_SUB_SKILL_IMAGE, current.getSkillSubImage());
                    viewIntent.putExtra(KEY_SUB_SKILL_CREATED_AT, current.getSkillSubCreatedAt());
                    viewIntent.putExtra(KEY_SUB_SKILL_UPDATED_AT, current.getSkillSubUpdatedAt());
                    viewIntent.putExtra(KEY_SUB_SKILL_SUPERID, current.getSkillSuperCategoryId());

                    v.getContext().startActivity(viewIntent);

                    //Code to make PUT request to update status of project clicked

                }
            });
        }

        @Override
        public int getItemCount() {
            //return size of des list
            return desList.size();
        }



}