package com.example.ul.libike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toast.makeText(this, "Choose a language.", Toast.LENGTH_LONG).show();
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                if(arg0 != null && arg0.getTitle().equals("English")){
                    Log.d(TAG, "marker Click");
                    // Initialyze new activity with params
                    Intent intent = new Intent(getApplicationContext(), BikeActivity.class);
                    // intent.putParcelableArrayListExtra(this.BIKE, tickets);
                    startActivity(intent);
                }
            }
        });
        LatLng greatBritain = new LatLng(51.30, -0.07);
        LatLng germany = new LatLng(52.3107, 13.2430);
        LatLng italy = new LatLng(41.53, 12.29);
        LatLng spain = new LatLng(40.25, -3.41);
        mMap.addMarker(new MarkerOptions()
                .position(greatBritain)
                .title("English")
                .snippet("Click on me:)"));
        mMap.addMarker(new MarkerOptions()
                .position(germany)
                .title("German")
                .snippet("Click on me:)"));
        mMap.addMarker(new MarkerOptions()
                .position(italy)
                .title("Italian")
                .snippet("Click on me:)"));
        mMap.addMarker(new MarkerOptions()
                .position(spain)
                .title("Spanish")
                .snippet("Click on me:)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(greatBritain));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(germany));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(italy));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spain));
    }

}

