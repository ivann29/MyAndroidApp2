package be.kuleuven.myandroidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountActivity extends AppCompatActivity
{
//gnegnegne
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    public void onGoogleMapsButton_Clicked   (View caller)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        //intent.putExtra("Order",order);
        startActivity(intent);

    }

}