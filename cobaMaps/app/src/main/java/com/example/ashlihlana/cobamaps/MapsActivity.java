package com.example.ashlihlana.cobamaps;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.ashlihlana.cobamaps.AppController;
import com.example.ashlihlana.cobamaps.R;
import com.example.ashlihlana.cobamaps.helper.PermissionHelper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.location.LocationListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ashlihlana.cobamaps.SplashScreen.REQUEST_ID_MULTIPLE_PERMISSIONS;

/**
 * Created by Kuncoro on 15/06/2017.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    MapFragment mapFragment;
    GoogleMap gMap, gMap1;
    MarkerOptions markerOptions = new MarkerOptions();
    CameraPosition cameraPosition;
    LatLng center, latLng, latLng1;
    String title, alamat, telp, web;
    private GoogleApiClient mGoogleApiClient;



    private GoogleMap mMap;

    private PermissionHelper permissionHelper;

    public static final String ID = "id_Reseller";
    public static final String TITLE = "nama_Reseller";
    public static final String ALAMAT = "alamat_Reseller";
    public static final String TLP = "telp_Reseller";
    public static final String WEB = "website_Reseller";
    public static final String LAT = "latitude_Reseller";
    public static final String LNG = "longitude_Reseller";
    private static final String TAG = "MapsActivity";


    //url ganti dengan api baru
    private String url = "http://192.168.100.223/apiMaps/getLatLng.php";
    //     private String url = "http://192.168.8.102/apiMaps/getLatLng.php";
    String tag_json_obj = "json_obj_req";

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                MapsActivity.this.startActivity(intent);
                //MapsActivity.this.finish();

            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }



    //


    //


    @Override
    public void onMapReady(GoogleMap googleMap) {
//

        gMap = googleMap;

        center = new LatLng(-8.098902, 112.166795);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(10).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


             if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
                    PackageManager.PERMISSION_GRANTED) {

                gMap.setMyLocationEnabled(true);

            }
        }else{


            gMap.setMyLocationEnabled(true);


        }

        //yang dipakai

        Location locationCt;
        LocationManager locationManagerCt = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationCt = locationManagerCt.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
      LatLng coordinate = new LatLng(locationCt.getLatitude(),locationCt.getLongitude());
       cameraPosition = new CameraPosition.Builder()
               .target(coordinate)
               .zoom(15)
               .bearing(90)
               .tilt(90)
               .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        Marker myLocationMarker = gMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon))
                        .position(coordinate));
        googleMap.getUiSettings().setZoomControlsEnabled(true);


        getMarkers();

    }




    private void addMarker(final LatLng latlng, final String title, final String alamat) {
        markerOptions.position(latlng);
        markerOptions.title(title);
        markerOptions.snippet(alamat);
        markerOptions.getInfoWindowAnchorU();
        gMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.resto)));
        gMap.getUiSettings().setZoomControlsEnabled(true);




        //aksi untuk jika marker di click oke
        gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this);
                alert.setTitle(marker.getTitle());
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MapsActivity.this, android.R.layout.select_dialog_item);
                arrayAdapter.add(telp);
                arrayAdapter.add(web);
                arrayAdapter.add(alamat);


                alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String strName = arrayAdapter.getItem(which);
                        if (strName.contentEquals(telp)){
                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telp)));

                        }else if(strName.contentEquals(web)) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+web)));
                        }else if (strName.contentEquals(alamat)){
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+latLng)));
                        }
                        strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(MapsActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                alert.show();
            }
        });
    }

    // Method untuk mengmbil data dari api / json menggunakan method post
    private void getMarkers() {
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.e("Response: ", response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String getObject = jObj.getString("reseller");
                    JSONArray jsonArray = new JSONArray(getObject);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        title = jsonObject.getString(TITLE);
                        telp = jsonObject.getString(TLP);
                        web = jsonObject.getString(WEB);
                        alamat = jsonObject.getString(ALAMAT);
                        latLng = new LatLng(Double.parseDouble(jsonObject.getString(LAT)),
                                Double.parseDouble(jsonObject.getString(LNG)));

                        // Menambah data marker untuk di tampilkan ke google map
                        addMarker(latLng, title, alamat);

                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", error.getMessage());
                Toast.makeText(MapsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        AppController.getInstance().addToRequestQueue(strReq,tag_json_obj);

    }


}