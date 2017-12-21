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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */



public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("bikes");
    List<Bike> bikes;

    private static final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        bikes = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        Toast.makeText(this, "Choisir un vélo", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bikes.clear();
                // This method) is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Bike bike = postSnapshot.getValue(Bike.class);
                    bikes.add(bike);
                }
                Log.d(TAG, "FeuBaise Value is: " + bikes);
                for (Bike bike : bikes) {
                    // fruit is an element of the `fruits` array.
                    Log.d(TAG, "FeuBaise Value is: " + bike.name);
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(bike.latitude, bike.longitude))
                            .title(bike.name)
                            .snippet("Plus d'informations"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(bike.latitude, bike.longitude)));

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                if(arg0 != null){
                    Log.d(TAG, "marker Click");
                    Intent intent = new Intent(getApplicationContext(), BikeActivity.class);
                    // intent.putParcelableArrayListExtra(this.BIKE, tickets);
                    startActivity(intent);
                }
            }
        });
    }

}

