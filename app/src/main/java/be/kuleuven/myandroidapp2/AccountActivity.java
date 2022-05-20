package be.kuleuven.myandroidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class AccountActivity extends AppCompatActivity
{

    private RequestQueue requestQueue;
    private static final String SUBMIT_URL = "https://studev.groept.be/api/a21pt206/accountSettings/";
    private Button GoogleMapsButton;
    private TextView userLocation;
    private TextView userInterests;
//gnegnegne
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
         GoogleMapsButton = (Button) findViewById(R.id.GoogleMapsButton);
         userLocation = (TextView) findViewById(R.id.locationUser);
         userInterests= (TextView)  findViewById(R.id.InterestsUser);


    }

    public void onGoogleMapsButton_Clicked   (View caller)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);


    }

    public void onBtnFreeTime_Clicked(View caller)
    {
        Intent intent = new Intent(this, FreeTimeActivity.class);
       startActivity(intent);

    }


    public void onBtnInterests_Clicked(View caller){




        Intent intent = new Intent(this, InterestsActivity.class);
        startActivity(intent);




    }




    public String getUserLocation()
    {
        return userLocation.toString();
    }

    public String   getUserInterests(){ return userInterests.toString();}

    public void setInterest(String s )
    {

        userInterests.setText(s);

    }

}