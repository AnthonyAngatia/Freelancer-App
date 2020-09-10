package com.example.freelancer.req;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freelancer.FreelancerProfileActivity;
import com.example.freelancer.R;
import com.squareup.picasso.Picasso;

public class RequestDescrActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_descr);



        //ImageView profileImageView = findViewById(R.id.profileImageView);
        //TextView userNameTextView = findViewById(R.id.usernameTextView);
        //ImageButton shareProfile = findViewById(R.id.shareProfile);
        //TextView developerUrl = findViewById(R.id.developerUrl);

        TextView reqDescriptionView = findViewById(R.id.reqdes_description);
        TextView reqRequestorNameView = findViewById(R.id.reqdes_item_requestor_name);
        TextView reqRequestorPhoneView = findViewById(R.id.reqdes_item_requestor_phone);
        TextView reqRequestorLocationView = findViewById(R.id.reqdes_item_requestor_location);

        Intent intent = getIntent();
        //final String userName = intent.getStringExtra(DevelopersAdapter.KEY_NAME);

        final String reqdescription = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_DESCRIPTION);
        final String reqname = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_REQUESTOR_NAME);
        final String reqphone = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_REQUESTOR_PHONE);
        final String reqlocation = intent.getStringExtra(RequestsAdapter.KEY_PROJECT_REQUESTOR_LOCATION);



        //userNameTextView.setText(userName);
        reqDescriptionView.setText(reqdescription);
        reqRequestorNameView.setText(reqname);
        reqRequestorPhoneView.setText(reqphone);
        reqRequestorLocationView.setText(reqlocation);

//        developerUrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = profileUrl;
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//            }
//        });



    }


    public void changeRequestStatus(View view) {

        Toast.makeText(RequestDescrActivity.this, "Added to projects list", Toast.LENGTH_SHORT).show();

    }
}
