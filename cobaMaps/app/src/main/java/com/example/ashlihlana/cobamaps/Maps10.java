package com.example.ashlihlana.cobamaps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps10 extends FragmentActivity implements OnMapReadyCallback {

    LatLng center, latLng, latLng1;
    GoogleMap gMap10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps10);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0812345678"));
                startActivity(intent);
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:085740482440"));
                sms.putExtra("sms_body", "Assalamualaikum");
                startActivity(sms);
            }
        });



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng sydney = new LatLng(-7.193304, 111.893431);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.resto))
                .title("Grosir Bojonegoro")
                .snippet("Rt:15/Rw:02,, Jl. Raya Desa Wedi, Wedi, Kapas, Kabupaten Bojonegoro, Jawa Timur 62181 "));
        // googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sydney)
                .zoom(15)
                .bearing(90)
                .tilt(90)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            public void onInfoWindowClick(Marker marker) {
                //Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(Maps10.this);
                builder.setTitle("Grosir Bojonegoro")
                        .setIcon(R.drawable.resto)
                        .setMessage("Rt:15/Rw:02,, Jl. Raya Desa Wedi, Wedi, Kapas, Kabupaten Bojonegoro, Jawa Timur 62181")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


    }
}
