package com.example.freelancer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.freelancer.classes.FreelanceServiceManager;
import com.example.freelancer.classes.ServiceSubCategory;
import com.example.freelancer.classes.ServiceSuperCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.freelancer.classes.FreelanceServiceManager.isFreelancer;
import static com.example.freelancer.classes.FreelanceServiceManager.isUserLogged;
import static com.example.freelancer.classes.FreelanceServiceManager.loginPreference;

public class FreelancerSignUp extends AppCompatActivity {

    private EditText mCategory;
    private EditText mSubCategory;
    private Button mSignUpButton;
    private EditText mDescription;
    private EditText mEducation;
    private EditText mSkill;
    private List<String> mCategoryName;
    private List<String> mSubCategoryName;
    private int mUser_id;
    private int mSub_skill_id;
    private String mSelectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_sign_up);

        mSignUpButton = findViewById(R.id.btn_signUp);
        mDescription = findViewById(R.id.shortDescription);
        mEducation = findViewById(R.id.educationText);
        mSkill = findViewById(R.id.skillsText);
        mCategory = findViewById(R.id.category_edit);
        mSubCategory = findViewById(R.id.sub_category);

        final SharedPreferences sharedPreferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        if( sharedPreferences.getBoolean(isUserLogged, false)){
            mUser_id = sharedPreferences.getInt("id",-1);
            if(mUser_id == -1)
                Toast.makeText(this, "ID is -1", Toast.LENGTH_SHORT).show();
        }else{
            //Login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        displayDialogBox1();

        mSubCategoryName = new ArrayList<>();


        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Check if the inputs are not empty
                boolean isValid = validateInput();
                if(!isValid){
                    return;
                }
                //Extract the content
                final String description = mDescription.getText().toString();
                String education = mEducation.getText().toString();
                final String qualifications = mSkill.getText().toString();

                final int appuser_id = mUser_id;

//                2.Make a post request using volley
                volleyRequestOnUserSkill(description, qualifications, mSub_skill_id, appuser_id);
            }
        });

    }

    private void displayDialogBox1() {
        AlertDialog.Builder alert = new AlertDialog.Builder(FreelancerSignUp.this);
        alert.setTitle("Freelancer Registration");
        alert.setMessage("We have noticed that you are not a freelancer. Please register to continue as a freelancer" );

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
                displayDialogBox2();

            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();
    }
    private void displayDialogBox2() {
        final String [] categories = new String[]{"Music", "Videography", "Art", "Programming"};
        AlertDialog.Builder alert = new AlertDialog.Builder(FreelancerSignUp.this);
        alert.setTitle("Please select category of your skill");
       alert.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               mSelectedCategory = categories[which];
               mCategory.setText(categories[which]);


           }
       });


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
                populateSubcategory(mSelectedCategory);

            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(FreelancerSignUp.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        alert.show();
    }
    private void displayDialogBox3() {
        final String [] categories = mSubCategoryName.toArray(new String[0]);
        AlertDialog.Builder alert = new AlertDialog.Builder(FreelancerSignUp.this);
        alert.setTitle("Please select sub category of your skill");
        alert.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSub_skill_id = which +1;//Ideally it should be the subcat id
                mSubCategory.setText(categories[which]);
                mSubCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayDialogBox3();
                    }
                });


            }
        });


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here



            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        displayDialogBox2();
                    }
                });

        alert.show();
    }
    private void displayDialogBox4() {
        AlertDialog.Builder alert = new AlertDialog.Builder(FreelancerSignUp.this);
        alert.setTitle("Success");
        alert.setMessage("Successfully added a skill");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
                Intent toFreelancerMode = new Intent(FreelancerSignUp.this, FreelancerHomeActivity.class);
                startActivity(toFreelancerMode);


            }
        });

        alert.show();
    }

    private void volleyRequestOnUserSkill(final String description, final String qualifications, final int sub_skill_id, final int appuser_id) {
        String url = "https://sheltered-plains-24359.herokuapp.com/api/userskills";
        RequestQueue queue = Volley.newRequestQueue(FreelancerSignUp.this);
        final ProgressDialog progressDialog = new ProgressDialog(FreelancerSignUp.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        progressDialog.dismiss();
                        Toast.makeText(FreelancerSignUp.this, " User updated successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Response", response);
                        //TODO IMPLEMENT PREFERENCE isFreelancer true
                                storePreference();
                                displayDialogBox4();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
//                        Log.d("Response", error.getMessage() );
//                                Toast.makeText(FreelancerSignUp.this, "Error connecting to the server.Please ensure you are connected to the internet", Toast.LENGTH_SHORT).show();
                        Toast.makeText(FreelancerSignUp.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map getParams()
            {
                Map params = new HashMap();
                params.put("sub_skill_id", Integer.toString(sub_skill_id));
                params.put("appuser_id", Integer.toString(appuser_id));
                params.put("poster_image_url", "image/url");
                params.put("description", description);
                params.put("appuser_qualification", qualifications);

// For debugging purposes
//                        params.put("sub_skill_id", "1");
//                        params.put("appuser_id", "3");
//                        params.put("poster_image_url", "Tony");
//                        params.put("description", "Tony");
//                         params.put("appuser_qualification", "qualificafion");


                return params;
            }
        };
        queue.add(postRequest);
    }




    private void populateSubcategory(String category) {
        //Make a request to find
        final String TAG = "VolleyReqSubCat";
        String url = buildUrl(category);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                         processSubCategories(response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                        Toast.makeText(FreelancerSignUp.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(getRequest);
//        ArrayAdapter<String> adapterSubCategory =
//                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mSubCategoryName);
//        adapterSubCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinnerSubCategory.setAdapter(adapterSubCategory);
//        mSpinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                mSub_skill_id = position + 1;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
    public List<String> processSubCategories(String jsonStringResponse){
        final String DATA = "data";
        final String NAME = "name";


        try {
            JSONObject jsonObject  = new JSONObject(jsonStringResponse);
            JSONArray arrayOfSubCategories = jsonObject.getJSONArray(DATA);
            int numberOfSubCategories = arrayOfSubCategories.length();
            mSubCategoryName.clear();
            for (int i=0; i< numberOfSubCategories; i++){
                JSONObject subCategoryJson = arrayOfSubCategories.getJSONObject(i);
                mSubCategoryName.add(subCategoryJson.getString(NAME));
            }
            displayDialogBox3();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  mSubCategoryName;

    }
    private String buildUrl(String subCategory){

        Uri uri =Uri.parse(FreelanceServiceManager.BASE_API_URL)
                .buildUpon()
                .appendPath("categories")
                .appendPath(subCategory)
                .build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.d("TAG", "BAD URL CREATED AT buildUrl");
            e.printStackTrace();
        }
        return  url.toString();
    }
    private boolean validateInput() {
        if(mDescription.getText().toString().isEmpty()
                || mEducation.getText().toString().isEmpty() ||
                mSkill.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please make sure all input fields are filled", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
    private void storePreference(){
        SharedPreferences preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putBoolean(isFreelancer, true);
        editor.commit();
    }




//Not in use Dprecated
    private void requestForCategories() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        final String TAG = "SignupVolley";
        String url = "https://sheltered-plains-24359.herokuapp.com/api/categories";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d(TAG, response.toString());
                        //Process the data here
                        processData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());
                    }
                });
        requestQueue.add(getRequest);
    }
    private void processData(JSONObject response) {
        final String DATA = "data";
        final String NAME = "name";
        final String IMAGE = "image_url";
        try {
            JSONArray arrayOfCategories = response.getJSONArray(DATA);
            int noOfCategories = arrayOfCategories.length();
            for (int i=0; i< noOfCategories; i++){
                JSONObject categoriesJson = arrayOfCategories.getJSONObject(i);
                ServiceSuperCategory subCategories = new ServiceSuperCategory(
                        categoriesJson.getString(NAME),
                        categoriesJson.getString(IMAGE)
                );
                mCategoryName.add(categoriesJson.getString(NAME));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.freelancer_mode:
                Intent toFreelancerMode = new Intent(this, FreelancerHomeActivity.class);
                startActivity(toFreelancerMode);
            case R.id.client_projects:
                Intent toClientProjects = new Intent(this, ClientProjectsActivity.class);
                startActivity(toClientProjects);
        }
        return super.onOptionsItemSelected(item);
    }
}