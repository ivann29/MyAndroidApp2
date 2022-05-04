package be.kuleuven.myandroidapp2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
//check if u have thisjksjkd
public class MapsActivity2 extends AppCompatActivity implements OnMapReadyCallback{

    private static final String fineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String coarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int location_permission_request_code = 1234;
    private boolean mLocationPermissionGranted;
    private GoogleMap mMap;

    public void onMapReady(GoogleMap googleMap)
    {
        Toast.makeText(this, "Map is ready",Toast.LENGTH_SHORT).show();
        mMap = googleMap;



    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLocationPermission();
        setContentView(R.layout.activity_maps2);

    }

    private void getLocationPermission()
    {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),fineLocation) == PackageManager.PERMISSION_GRANTED)
        {
            //Toast.makeText(this, "already provided",Toast.LENGTH_SHORT).show();
            if((ContextCompat.checkSelfPermission(this.getApplicationContext(),coarseLocation)== PackageManager.PERMISSION_GRANTED))
            {
                mLocationPermissionGranted = true;
            }

            else
            {
                ActivityCompat.requestPermissions(this,permissions,location_permission_request_code);
            }
        }


        else{
            ActivityCompat.requestPermissions(this,permissions,location_permission_request_code);
        }
    }

    private void initMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity2.this);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;

        switch(requestCode)
        {
            case location_permission_request_code:{
                if(grantResults.length>0){
                    for(int i=0; i < grantResults.length; i++)
                    {
                        if(grantResults[i]!= PackageManager.PERMISSION_GRANTED)
                        {
                            mLocationPermissionGranted=false;
                        }
                    }
                    mLocationPermissionGranted = true;
                    initMap();
                }
            }
        }


    }
}