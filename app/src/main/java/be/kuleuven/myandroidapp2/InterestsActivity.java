package be.kuleuven.myandroidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;

public class InterestsActivity extends AppCompatActivity
{


    private Button sportsbtn;
    private Button actingbtn;
    private Button moviesbtn;
    private Button artbtn;
    private Button politicsbtn;
    private Button extremesportsbtn;
    private Button musicbtn;
    private Button signbtn;
    private Button foodbtn;
    private Button businessbtn;
    private Button ConfirmButton;
    private String[] array;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        sportsbtn = (Button) findViewById(R.id.sports);
        actingbtn = (Button) findViewById(R.id.acting);
        moviesbtn = (Button) findViewById(R.id.movies);
        artbtn = (Button) findViewById(R.id.art);
        politicsbtn = (Button) findViewById(R.id.politics);
        extremesportsbtn = (Button) findViewById(R.id.extremeSports);
        musicbtn = (Button) findViewById(R.id.music);
        signbtn = (Button) findViewById(R.id.sing);
        foodbtn = (Button) findViewById(R.id.foodDrinks);
        businessbtn = (Button) findViewById(R.id.business);
        array = new String[3];
        Arrays.fill(array, "");

    }



    public void onCofirm_clicked(View caller)
    {
        RequestQueue requestQueue;
        String SUBMIT_URL =" https://studev.groept.be/api/a21pt206/accountInterestIole/";

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = SUBMIT_URL  +
                array[0]  +"/" +
                array[1] + "/" +
                array[2] ;

        Log.d("Database","creating response");

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("Database","response received");
                        Intent intent = new Intent(caller.getContext(), AccountActivity.class);
                        startActivity(intent);
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

    public void onSports_clicked   (View caller)
    {
        int a=0;
        sportsbtn.setBackgroundResource(R.color.colorSelected);
        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=sportsbtn.getText().toString();
                a=1;
            }
            else{}
        }
    }

    public void actingBtn_Clicked   (View caller)
    {
        int a=0;
        actingbtn.setBackgroundResource(R.color.colorSelected);
        for(int i = 0; i<array.length; i++ )
        {
            if(array[i].equals("")&& a==0)
            {
                array[i]= actingbtn.getText().toString();
                a=1;
            }
            else{}
        }
    }

    public void moviesBtn_Clicked   (View caller)
    {
        int a=0;
        moviesbtn.setBackgroundResource(R.color.colorSelected);
        for(int i = 0; i<array.length; i++ )
        {
            if(array[i].equals("") && a==0)
            {
                array[i]= moviesbtn.getText().toString();
                a=1;
            }
            else{}
        }
    }

    public void onArtBtn_Clicked  (View caller)
    {
        int a=0;
        artbtn.setBackgroundResource(R.color.colorSelected);
        for(int i = 0; i<array.length; i++ )
        {
            if(array[i].equals("") && a==0)
            {

                array[i]=  artbtn.getText().toString();
                a=1;
            }
            else{}
        }
    }








}

