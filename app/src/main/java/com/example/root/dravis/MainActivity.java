package com.example.root.dravis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.widget.Toast;
import android.provider.Settings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements LocationListener{
    private LocationManager locationManager;
     DatabaseReference myRef;
    FirebaseDatabase mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    2000, 1, this);


        }


        catch
                (SecurityException e)
        {
            Toast.makeText(getBaseContext(), ""+e,
                    Toast.LENGTH_SHORT).show();
        }
// ...


// Write a message to the database
        try {
            mdatabase = FirebaseDatabase.getInstance();
           // FirebaseDatabase   mDatabase =  FirebaseDatabase.getInstance().getReference();
           // DatabaseReference mDatabase = database.getReference("testing-739c9/-KTJZ7ScdMo0hxKLZr54");

             myRef = mdatabase.getReference("Usman");
            Toast.makeText(getBaseContext(), "jjjjjjjjjjjj",
                    Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "jddjhddiufduhdi",
                    Toast.LENGTH_SHORT).show();

        }
         }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude() + " New Longitude: " + location.getAltitude() +
                "New Longitude: " + location.getSpeed();


        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        Map<String,Object> taskMap = new HashMap<String,Object>();
        taskMap.put("Alt", location.getAltitude());
        taskMap.put("Long", location.getLongitude());
        taskMap.put("Lat", location.getLatitude());
        taskMap.put("Bearing", location.getBearing());
        taskMap.put("Speed", location.getSpeed());
        taskMap.put("Time", location.getTime());


        try {
            myRef.updateChildren(taskMap);
            Toast.makeText(getBaseContext(), "Done Alhamdulillah", Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {

            Toast.makeText(getBaseContext(), "Unable to Transfer to Firebase", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turn`ed off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }


}
