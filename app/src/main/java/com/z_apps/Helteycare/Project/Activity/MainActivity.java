package com.z_apps.Helteycare.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.FirebaseManeger;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback

{
    private GoogleMap mMap;
    SupportMapFragment mapFragment ;

    Z_methods z_methods ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mapFragment   = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapVIEW);
        mapFragment.getMapAsync(this);

z_methods = new Z_methods(this);
z_methods.GOTo_Activity(Act_login.class ,R.anim.goin_tolift , R.anim.goout_tolift);
      }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void Go_log(View view) {

        z_methods.GOTo_Activity(Act_signUp.class);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
