package com.example.freelancer;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientProjectsAdapter extends RecyclerView.Adapter<ClientProjectsAdapter.ViewHolder>{
    // declare Projects private member variable
    private List<ClientProject> clientProjectsList;
    // context variable
    private Context mContext;

    // keys for the intents(these will be needed when one clicks on the image view,
    // to be directed to a specific project's dashboard activity )
    public static final String KEY_PROJECT_ID = "project_id";
    // KEY_PROJECT_STATUS will change after click
    public static final String KEY_PROJECT_STATUS = "project_status";
    // not available yet
    public static final String KEY_PROJECT_REVIEW = "project_review";
    public static final String KEY_PROJECT_DESCRIPTION = "project_description";
    public static final String KEY_PROJECT_PRICE = "project_price";
    public static final String KEY_PROJECT_DELIVERY_TIME = "project_delivery_time";
    public static final String KEY_PROJECT_PROGRESS = "project_progress";
    public static final String KEY_APPUSER_INVITER_ID = "appuser_inviter_id";
    public static final String KEY_APPUSER_FREELANCER_ID = "appuser_freelancer_id";
    //others
    public static final String KEY_PROJECT_FREELANCER_NAME = "project_freelancer_name";
    public static final String KEY_PROJECT_FREELANCER_LOCATION = "project_freelancer_location";
    public static final String KEY_PROJECT_FREELANCER_PHONE = "project_freelancer_phone";

    public static final String KEY_PROJECT_STATUS_DISPLAY = "project_status_display";


    public ClientProjectsAdapter(List<ClientProject> clientProjectsList, Context context) {
        this.clientProjectsList = clientProjectsList;
        this.mContext = context;
    }

    // inner class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        // define view objects
        public RelativeLayout client_project_item_rellayout;
        public ImageView client_project_item_imageView;
        public TextView client_project_item_freelancer_location;
        public TextView client_project_item_freelancer_name;
        public TextView client_project_item_freelancer_phone;

        public TextView client_project_item_status_display;

        // inner class Viewholder constructor
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            client_project_item_rellayout = itemView.findViewById(R.id.client_project_item_rellayout);
            client_project_item_imageView = itemView.findViewById(R.id.client_project_item_imageView);
            client_project_item_freelancer_location = itemView.findViewById(R.id.client_project_item_requestor_location);
            client_project_item_freelancer_name = itemView.findViewById(R.id.client_project_item_requestor_name);
            client_project_item_freelancer_phone = itemView.findViewById(R.id.client_project_item_requestor_phone);

            client_project_item_status_display = itemView.findViewById(R.id.client_project_item_status_display);
        }
    }

    @NonNull
    @Override
    public ClientProjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClientProjectsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_project, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClientProjectsAdapter.ViewHolder holder, final int position) {
        // dealing with the images
        // get array from strings.xml file
        TypedArray projectImagesArray = this.mContext.getResources().obtainTypedArray(R.array.project_imgs);
        //inflate image view
        holder.client_project_item_imageView.setImageResource(projectImagesArray.getResourceId(position, 0));

        //create a variable that gets the current instance of the project in the list
        final ClientProject currentProject = clientProjectsList.get(position);
        //populate Text Views with data, Image View has static image
        holder.client_project_item_freelancer_name.setText(currentProject.getProjectItemRequestorName());
        holder.client_project_item_freelancer_location.setText(currentProject.getProjectItemRequestorLocation());
        holder.client_project_item_freelancer_phone.setText(currentProject.getProjectItemRequestorPhone());

        // if project is completed then display so and if not display otherwise
        if(currentProject.getProjectStatus().equals("complete")){
            holder.client_project_item_status_display.setText("Completed");
        }
        else{
            holder.client_project_item_status_display.setText("In Progress");
        }

        //set onclick listener to handle click events
        holder.client_project_item_rellayout.setOnClickListener(new View.OnClickListener(){
            @Override
            //ensure you override the onClick method
            public void onClick(View v){
                //create an instance of thr developer list and initialize it
                ClientProject currentProject = clientProjectsList.get(position);
                //create an intent and specify the target class as ClientProjectView Activity
                Intent clientProjectViewIntent = new Intent(v.getContext(), ClientProjectViewActivity.class);
                //use intent EXTRA to pass data from MainActivity to ProfileActivity
                clientProjectViewIntent.putExtra(KEY_PROJECT_ID, currentProject.getProjectId());
                clientProjectViewIntent.putExtra(KEY_PROJECT_STATUS, currentProject.getProjectStatus());
                clientProjectViewIntent.putExtra(KEY_PROJECT_REVIEW, currentProject.getProjectReview());
                clientProjectViewIntent.putExtra(KEY_PROJECT_DESCRIPTION, currentProject.getProjectDescription());
                clientProjectViewIntent.putExtra(KEY_PROJECT_PRICE, currentProject.getProjectPrice());
                clientProjectViewIntent.putExtra(KEY_PROJECT_DELIVERY_TIME, currentProject.getProjectDeliveryTime());
                clientProjectViewIntent.putExtra(KEY_PROJECT_PROGRESS, currentProject.getProjectProgress());
                clientProjectViewIntent.putExtra(KEY_APPUSER_INVITER_ID, currentProject.getAppuser_inviter_id());
                clientProjectViewIntent.putExtra(KEY_APPUSER_FREELANCER_ID, currentProject.getAppuser_freelancer_id());

                //others
                clientProjectViewIntent.putExtra(KEY_PROJECT_FREELANCER_NAME, currentProject.getProjectItemRequestorName());
                clientProjectViewIntent.putExtra(KEY_PROJECT_FREELANCER_LOCATION, currentProject.getProjectItemRequestorLocation());
                clientProjectViewIntent.putExtra(KEY_PROJECT_FREELANCER_PHONE, currentProject.getProjectItemRequestorPhone());

                //if project is competed, we'll go with 'Completed' as the display otherwise it's 'In Progress'
                if(currentProject.getProjectStatus() == "complete"){
                    clientProjectViewIntent.putExtra(KEY_PROJECT_STATUS_DISPLAY, "Completed");
                    v.getContext().startActivity(clientProjectViewIntent);
                }
                else{
                    clientProjectViewIntent.putExtra(KEY_PROJECT_STATUS_DISPLAY, "In Progress");
                    v.getContext().startActivity(clientProjectViewIntent);
                }

                // not sure about what I meant below
                // Code to make PUT request to update status of project clicked

            }
        });
    }

    @Override
    public int getItemCount() {
        //return size of developer list
        return clientProjectsList.size();
    }

}
