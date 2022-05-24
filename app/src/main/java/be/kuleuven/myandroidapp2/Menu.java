package be.kuleuven.myandroidapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fragments.Dash;

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

        Intent intent= new Intent(this, selectInterest_Activity.class);

        startActivity(intent);

        /*startActivityFromFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fraggy = new AddBlogsFragment();
        fragmentTransaction.add(R.id.fragment_container, fraggy);
        fragmentTransaction.commit();*/
    }


}