package be.kuleuven.myandroidapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fragments.Dash;

public class selectInterest_Activity extends AppCompatActivity
{
    private SharedPreferences newPreference;

    private Button interest1;
    private Button interest2;
    private Button interest3;
    private String[] tokens;
    private SharedPreferences Shared_pref;
    private RequestQueue requestQueue;
    private static final String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/retrieveInterestUserLoggedIn/";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interest);
        interest1 = (Button) findViewById(R.id.interest1);
        interest2 = (Button) findViewById(R.id.interest2);
        interest3 = (Button) findViewById(R.id.interest3);

        tokens= new String[3];

        init();



    }

    public void init()
    {
        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);
        Shared_pref = getSharedPreferences("details", MODE_PRIVATE);


        requestQueue = Volley.newRequestQueue(this);
        String requestURL = SUBMIT_URL  + email;
        System.out.println(requestURL);


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
                                tokens = responseInterests.split(",",5);
                                for (String a : tokens)
                                    System.out.println(a);

                                interest1.setText(tokens[0]);
                                interest2.setText(tokens[1]);
                                interest3.setText(tokens[2]);


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


        requestQueue.add(submitRequest);



    }


    public void onInterest1_Clicked (View caller)
    {
        Drawable buttonDrawable = interest1.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.gold));
        interest1.setBackground(buttonDrawable);

        String interest = tokens[0];
        SharedPreferences.Editor editor = Shared_pref.edit();
        editor.putString("selectedInterest", interest);
        editor.apply();

    }


    public void onInterest2_Clicked (View caller)
    {
        Drawable buttonDrawable = interest2.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.gold));
        interest2.setBackground(buttonDrawable);

        String interest = tokens[1];
        SharedPreferences.Editor editor = Shared_pref.edit();
        editor.putString("selectedInterest", interest);
        editor.apply();


    }

    public void onInterest3_Clicked (View caller)
    {

        Drawable buttonDrawable = interest3.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.gold));
        interest3.setBackground(buttonDrawable);

        String interest = tokens[2];
        SharedPreferences.Editor editor = Shared_pref.edit();
        editor.putString("selectedInterest", interest);
        editor.apply();

    }




    public void onContinueBtn_Clicked(View caller)
    {
        Intent intent= new Intent(this, Dash.class);
        startActivity(intent);
    }



    public void onSkipBtn_Clicked(View caller)
    {
        Intent intent= new Intent(this, Dash.class);

        startActivity(intent);
    }
}