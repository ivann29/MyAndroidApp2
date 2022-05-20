package be.kuleuven.myandroidapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void onSearch_Clicked (View caller)
    {
        Intent intent = new Intent(this, findFriends.class);
        startActivity(intent);

    }


    public void onAccount_btn_Clicked (View caller)
    {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);

    }

    public void onCommonInterests_Clicked(View caller)
    {
        Intent intent= new Intent(this, Dash.class);
        startActivity(intent);
    }


}