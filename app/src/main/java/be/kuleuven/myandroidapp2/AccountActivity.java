package be.kuleuven.myandroidapp2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import maps.MapsActivity;

public class AccountActivity extends AppCompatActivity
{

    private RequestQueue requestQueue;
    private static final String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/accountSettings/";
    private Button GoogleMapsButton;
    private TextView userLocation;
    private TextView userInterests;
    private TextView userFreeTime;
    private ImageView IVPreviewImage;
    private ImageButton imageSelector;
    private SharedPreferences newPreference;
    private Spinner spinner;
    private int SELECT_PICTURE = 200; // constant to compare the activity result code
    private String selected;






//gnegnegne
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        GoogleMapsButton = (Button) findViewById(R.id.GoogleMapsButton);
        userLocation = (TextView) findViewById(R.id.locationUser);
        userInterests= (TextView)  findViewById(R.id.InterestsUser);
        userFreeTime = (TextView) findViewById(R.id.FreeTimeUser);
        IVPreviewImage = (ImageView) findViewById(R.id.profileImage);
        imageSelector= (ImageButton) findViewById(R.id.imageSelector);
        spinner = (Spinner) findViewById(R.id.spinner);
        imageSelector.setVisibility(View.INVISIBLE);

         init(); //for interests set up
         init2(); // for location set up
         init3(); //for profile image set up
         init4(); // for free time set up

        imageSelector.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selected = parent.getItemAtPosition(position).toString();
                userFreeTime.setText(selected);
                updateTime();


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                userFreeTime.setText("Select when your are free to hang out!");
            }
        });

    }

    protected void onStart()
    {
        super.onStart();
        init();
        init2();
        init4();
    }


        public void updateTime()
    {

        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);
        RequestQueue requestQueue;
        String SUBMIT_URL ="https://studev.groept.be/api/a21pt206/updateFreeTime/";

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = SUBMIT_URL  + selected + "/" + email;



        Log.d("Database","creating response");

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("Database","response received");

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        requestQueue.add(submitRequest);
        Log.d("Database","response sent");
    }


    public void init4()
    {
        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);



        String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/retrieveFreeTime/";
        String REQUEST_URL=  SUBMIT_URL + email;
        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, REQUEST_URL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            for (int i = 0; i < response.length(); i++)
                            {
                                Log.e("db", "inside responce");
                                String timeString = "";


                                JSONObject curObject = response.getJSONObject(i);
                                timeString += curObject.getString("freetime");

                                if(timeString.equals("null"))
                                {
                                    userFreeTime.setText("Select when your are free to hang out!");
                                }
                                else
                                {
                                    userFreeTime.setText(timeString);

                                }

                            }

                        }
                        catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue.add(submitRequest);


    }





    public void init3()
    {
        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);



        String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/selectProfileImageUserLoggedIn/";
        String REQUEST_URL=  SUBMIT_URL + email;
        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, REQUEST_URL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            for (int i = 0; i < response.length(); i++)
                            {
                                Log.e("db", "inside responce");
                                String imageString = "";


                                JSONObject curObject = response.getJSONObject(i);
                                imageString += curObject.getString("profileImage");

                                if(!imageString.equals("null"))
                                {
                                    imageSelector.setVisibility(View.INVISIBLE);
                                    byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                    IVPreviewImage.setImageBitmap(bitmap);

                                }
                                else
                                {
                                    imageSelector.setVisibility(View.VISIBLE);
                                }

                            }

                        }
                        catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue.add(submitRequest);

    }



    public void init()
    {
        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/retrieveInterestUserLoggedIn/";
        String requestURL = SUBMIT_URL  + email;


        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String responseInterests = "";

                            for (int i = 0; i < response.length(); i++)
                            {
                                Log.e("db", "inside responce");

                                JSONObject curObject = response.getJSONObject(i);
                                responseInterests += curObject.getString("interests");


                            }

                            if (responseInterests.equals("null"))
                            {
                                userInterests.setText("Select your interests!");

                            }
                            else
                            {
                                userInterests.setText(responseInterests);
                            }


                        } catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue.add(submitRequest);}



    public void imageChooser()
    {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);


    }



    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE)
            {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri)
                {
                    imageSelector.setVisibility(View.INVISIBLE);
                    IVPreviewImage.setImageURI(selectedImageUri);

                    /*String stringUri = selectedImageUri.getPath();
                    byte[] imageBytes = Base64.decode(stringUri, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    IVPreviewImage.setImageBitmap(bitmap);*/

                }

            }
        }
    }


    public void onChangeBtn(View caller){imageChooser();}



    public void onSave(View caller)
    {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        // show the progress dialog box
        /*pd.setMessage("Publishing Post");
        pd.show();*/
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving, please wait...");
        progressDialog.show();
        /*final String timestamp = String.valueOf(System.currentTimeMillis());
        String filepathname = "Posts/" + "post" + timestamp;
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
*/
        Bitmap bitmap = ((BitmapDrawable) IVPreviewImage.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();

        // Get the Base64 string
        String imageString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/insertProfilePicture/";





        /////////

        StringRequest  submitRequest = new StringRequest (Request.Method.POST,  SUBMIT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                //Turn the progress widget off
                progressDialog.dismiss();
                System.out.println("success");
                //Toast.makeText(MainActivity.this, "Post request executed", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("fail");
                // Toast.makeText(MainActivity.this, "Post request failed", Toast.LENGTH_LONG).show();
            }
        })

        { //NOTE THIS PART: here we are passing the parameter to the webservice, NOT in the URL!
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
                String email = newPreference.getString("email", null);
                Map<String, String> params = new HashMap<String, String>();
                params.put("image", imageString);
                params.put("email", email);


                return params;
            }
        };

        requestQueue.add(submitRequest);
    }


        public void init2() {

        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);String email = newPreference.getString("email", null);

        RequestQueue requestQueue2;
        requestQueue2 = Volley.newRequestQueue(this);
        String SUBMIT_URL2 = "https://studev.groept.be/api/a21pt206/retrieveUserLoggedInLocation/";
        String requestURL2 = SUBMIT_URL2 + email;


        JsonArrayRequest submitRequest2 = new JsonArrayRequest(Request.Method.GET, requestURL2, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response2) {
                        try {
                            String responseLocation = "";

                            for (int i = 0; i < response2.length(); i++)
                            {
                                Log.e("db", "inside responce");

                                JSONObject curObject = response2.getJSONObject(i);
                                responseLocation += curObject.getString("location");


                            }

                            if (!responseLocation.equals("null")) {
                                userLocation.setText(responseLocation);

                            } else {
                                userInterests.setText("Open the map!");
                            }


                        } catch (JSONException e) {
                            Log.e("Database", e.getMessage(), e);
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("db", "error");
                    }
                }
        );


        requestQueue2.add(submitRequest2);
    }












    public void onGoogleMapsButton_Clicked   (View caller)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);


    }

    public void onBtnFreeTime_Clicked(View caller)
    {
        Toast.makeText(this,"Use the spinner to select your free time",Toast.LENGTH_LONG).show();

    }


    public void onBtnInterests_Clicked(View caller){




        Intent intent = new Intent(this, InterestsActivity.class);
        startActivity(intent);




    }




}