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

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {
    // declare Projects private member variable
    private List<Project> projectsList;
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
    public static final String KEY_PROJECT_REQUESTOR_NAME = "project_requestor_name";
    public static final String KEY_PROJECT_REQUESTOR_LOCATION = "project_requestor_location";
    public static final String KEY_PROJECT_REQUESTOR_PHONE = "project_requestor_phone";

    public ProjectsAdapter(List<Project> projectsList, Context context) {
        this.projectsList = projectsList;
        this.mContext = context;
    }

    // inner class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        // define view objects
        public RelativeLayout project_item_rellayout;
        public ImageView project_item_imageView;
        public TextView project_item_requestor_location;
        public TextView project_item_requestor_name;
        public TextView project_item_requestor_phone;
        public TextView project_item_status_display;

        // inner class Viewholder constructor
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            project_item_rellayout = itemView.findViewById(R.id.project_item_rellayout);
            project_item_imageView = itemView.findViewById(R.id.project_item_imageView);
            project_item_requestor_location = itemView.findViewById(R.id.project_item_requestor_location);
            project_item_requestor_name = itemView.findViewById(R.id.project_item_requestor_name);
            project_item_requestor_phone = itemView.findViewById(R.id.project_item_requestor_phone);

            project_item_status_display = itemView.findViewById(R.id.project_item_status_display);
        }
    }

    @NonNull
    @Override
    public ProjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.ViewHolder holder, final int position) {
        // dealing with the images
        // get array from strings.xml file
        TypedArray projectImagesArray = this.mContext.getResources().obtainTypedArray(R.array.project_imgs);
        //inflate image view
        holder.project_item_imageView.setImageResource(projectImagesArray.getResourceId(position, 0));

        //create a variable that gets the current instance of the project in the list
        final Project currentProject = projectsList.get(position);
        //populate Text Views with data, Image View has static image
        holder.project_item_requestor_name.setText(currentProject.getProjectItemRequestorName());
        holder.project_item_requestor_location.setText(currentProject.getProjectItemRequestorLocation());
        holder.project_item_requestor_phone.setText(currentProject.getProjectItemRequestorPhone());

        // if project is completed then display so and if not display otherwise
        if(currentProject.getProjectStatus().equals("complete")){
            holder.project_item_status_display.setText("Completed");
        }
        else{
            holder.project_item_status_display.setText("In Progress");
        }

        //set onclick listener to handle click events
        holder.project_item_rellayout.setOnClickListener(new View.OnClickListener(){
            @Override
            //ensure you override the onClick method
            public void onClick(View v){
                //create an instance of thr developer list and initialize it
                Project currentProject = projectsList.get(position);
                //create an intent and specify the target class as ProjectView Activity
                Intent projectViewIntent = new Intent(v.getContext(), ProjectViewActivity.class);
                //use intent EXTRA to pass data from RequestActivity to RequestDescrActivity
                projectViewIntent.putExtra(KEY_PROJECT_ID, String.valueOf(currentProject.getProjectId()));
                projectViewIntent.putExtra(KEY_PROJECT_STATUS, currentProject.getProjectStatus());
                projectViewIntent.putExtra(KEY_PROJECT_REVIEW, currentProject.getProjectItemRequestorName());
                projectViewIntent.putExtra(KEY_PROJECT_DESCRIPTION, currentProject.getProjectItemRequestorLocation());
                projectViewIntent.putExtra(KEY_PROJECT_PRICE, currentProject.getProjectPrice());
                projectViewIntent.putExtra(KEY_PROJECT_DELIVERY_TIME, currentProject.getProjectItemRequestorName());
                projectViewIntent.putExtra(KEY_PROJECT_PROGRESS, currentProject.getProjectProgress());
                projectViewIntent.putExtra(KEY_APPUSER_INVITER_ID, currentProject.getProjectItemRequestorLocation());
                projectViewIntent.putExtra(KEY_APPUSER_FREELANCER_ID, currentProject.getProjectItemRequestorPhone());
                projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_NAME, currentProject.getProjectItemRequestorName());
                projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_LOCATION, currentProject.getProjectItemRequestorLocation());
                projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_PHONE, currentProject.getProjectItemRequestorPhone());

                //others
                projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_NAME, currentProject.getProjectItemRequestorName());
                projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_LOCATION, currentProject.getProjectItemRequestorLocation());
                projectViewIntent.putExtra(KEY_PROJECT_REQUESTOR_PHONE, currentProject.getProjectItemRequestorPhone());

                //if project is not competed, go to ProjectViewActivity else go to ProjectViewActivity2
                if(currentProject.getProjectStatus().equals("uncomplete")){
                    v.getContext().startActivity(projectViewIntent);
                }
                else{
                    Intent projectView2Intent = new Intent(v.getContext(), ProjectView2Activity.class);
                    v.getContext().startActivity(projectView2Intent);
                }

                // not sure about what I meant below
                // Code to make PUT request to update status of project clicked

            }
        });
    }

    @Override
    public int getItemCount() {
        //return size of developer list
        return projectsList.size();
    }

}
