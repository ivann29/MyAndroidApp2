package be.kuleuven.myandroidapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    
    public void onLoginButton_Clicked  (View caller)
    {
        Intent intent = new Intent(this, LogIn.class);
        //intent.putExtra("Order",order);
        startActivity(intent);

    }

    public void onSignUpButton_Clicked  (View caller)
    {
        Intent intent = new Intent(this, TestIvan.class);
       // intent.putExtra("Order",order);
        startActivity(intent);
    }

}