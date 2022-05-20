package be.kuleuven.myandroidapp2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

//check if u have thisjksjkd
public class MapsActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity2";

    private static final String fineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String coarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int location_permission_request_code = 1234;
    private boolean mLocationPermissionGranted;
    private GoogleMap mMap;
    private FusedLocationProviderClient myFusedLocationProviderClient;
    public String locationString;

    public void onMapReady(GoogleMap googleMap)
    {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {

                return;
            }
            mMap.setMyLocationEnabled(true);
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        getLocationPermission();
        getDeviceLocation();

    }

    public String getLocationString()
    {return this.locationString;}

    private void getDeviceLocation()
    {
        System.out.println("getDeviceLocation: getting device location");
        myFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPermissionGranted )
            {
                LocationManager locationManager = (LocationManager)  this.getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
                Task location = myFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener()
                {

                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if(task.isSuccessful()&& location != null)
                        {
                            Log.d(TAG, "onComplete: found location");
                            Location currentLocation = (Location) location.getResult();
                            LatLng object = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            moveCamera(object,15f);

                            /* LatLng latlong = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            Geocoder geocoder = new Geocoder(MapsActivity2.this, Locale.getDefault());
                            try{
                                List<Address> listAddresses = geocoder.getFromLocation( latlong.latitude, latlong.longitude,1);
                                if(listAddresses.size()>0){ locationString  = listAddresses.get(0).getLocality();};

                            }
                            catch(IOException ex)
                            {ex.printStackTrace();}*/

                        }
                        else
                        {
                            Log.d(TAG, "onComplete: unable to find location");
                            Toast.makeText(MapsActivity2.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        }
        catch(SecurityException e)
        {
            System.out.println(e.getMessage());
        }


    }

    private void moveCamera(LatLng latLong, float zoom)
    {
        System.out.print("moving camera");
        Log.d(TAG, "moveCamera: moving the camera to" + latLong.latitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong,zoom));


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
                initMap();

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
        //((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(MapsActivity2.this);

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