package maps;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import be.kuleuven.myandroidapp2.AccountActivity;
import be.kuleuven.myandroidapp2.R;


public class MapsActivity extends AppCompatActivity {


    private static final String TAG = "MapsActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(isServicesOkay())
        {
            init();
        }

    }

    public void onConfirm (View caller)
    {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);

    }

    private void init()
    {


        Button btnMap = (Button) findViewById(R.id.buttonMaps);
        btnMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent = new Intent(MapsActivity.this, MapsActivity2.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOkay()
    {
        Log.d(TAG, "isServicesOkay: checking google serivces verion");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsActivity.this);
        if(available == ConnectionResult.SUCCESS)
        {
            //all is okay
            Log.d(TAG, "isServicesOkay: Google play services is working");
            return true;
        }

        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            //an error occured but it is resolvable
            Log.d(TAG, "isServicesOkay: An error occured but we can fix it ");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapsActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        }
        else {
            Toast.makeText(this, "You cannot make map request", Toast.LENGTH_SHORT).show();  }

        return false;

    }

}