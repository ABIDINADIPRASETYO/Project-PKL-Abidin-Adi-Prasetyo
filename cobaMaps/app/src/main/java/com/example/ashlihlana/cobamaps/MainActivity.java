package com.example.ashlihlana.cobamaps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ashlihlana.cobamaps.adapter.CustomListAdapter;
import com.example.ashlihlana.cobamaps.adapter.Adapter;

import com.example.ashlihlana.cobamaps.module.Jarak;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kuncoro on 03/29/2016.
 */
public class MainActivity extends AppCompatActivity implements LocationListener,
        SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener  {

    ProgressDialog pDialog;
    SwipeRefreshLayout swipe;
    ListView list,list2;
    CustomListAdapter adapter;
    Adapter adapter1;

    List<Jarak> itemList = new ArrayList<>();
    Double latitude_Reseller, longitude_Reseller;
    Criteria criteria;
    Location location;
    LocationManager locationManager;
    String provider;

    public static final String url_data = "http://192.168.100.223/apiMaps/data.php";
    public static final String url_cari = "http://192.168.100.223/apiMaps/cari_data.php";

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_RESULTS = "results";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_VALUE = "value";

    String tag_json_obj = "json_obj_req";

    /* 10.0.2.2 adalah IP Address localhost EMULATOR ANDROID STUDIO,
    Ganti IP Address tersebut dengan IP Laptop Apabila di RUN di HP. HP dan Laptop harus 1 jaringan */
    private static final String url = "http://192.168.100.223/apiMaps/jarak.php?latitude_Reseller=-8.098902&longitude_Reseller=112.166795";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menyamakan variabel pada layout dan java
        list = (ListView) findViewById(R.id.list);





        //tambahan baru untuk event click pada listview
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                if (position==0){
                    Intent intent0 = new Intent(MainActivity.this, Maps0.class);
                    startActivity(intent0);
                } else if (position==1){
                    Intent intent1 = new Intent(MainActivity.this, Maps1.class);
                    startActivity(intent1);

                } else if (position==2){
                    Intent intent2 = new Intent(MainActivity.this, Maps2.class);
                    startActivity(intent2);

                }else if (position==3){
                    Intent intent3 = new Intent(MainActivity.this, Maps3.class);
                    startActivity(intent3);

                }else if (position==4){
                    Intent intent4 = new Intent(MainActivity.this, Maps4.class);
                    startActivity(intent4);

                }else if (position==5){
                    Intent intent5 = new Intent(MainActivity.this, Maps5.class);
                    startActivity(intent5);

                }else if (position==6){
                    Intent intent6 = new Intent(MainActivity.this, Maps6.class);
                    startActivity(intent6);

                }else if (position==7){
                    Intent intent7 = new Intent(MainActivity.this, Maps7.class);
                    startActivity(intent7);

                }else if (position==8){
                    Intent intent8 = new Intent(MainActivity.this, Maps8.class);
                    startActivity(intent8);

                }else if (position==9){
                    Intent intent9 = new Intent(MainActivity.this, Maps9.class);
                    startActivity(intent9);

                }else if (position==10){
                    Intent intent10 = new Intent(MainActivity.this, Maps10.class);
                    startActivity(intent10);

                }else if (position==11){
                    Intent intent11 = new Intent(MainActivity.this, Maps11.class);
                    startActivity(intent11);

                }


            }

        });
        //end list

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        // mengisi data dari adapter ke listview
        adapter = new CustomListAdapter(this, itemList);
        list.setAdapter(adapter);

        FloatingActionButton fab_search = (FloatingActionButton) findViewById(R.id.fb_search);
        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsearch = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intentsearch);
            }
        });



        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        provider = locationManager.getBestProvider(criteria, false);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           lokasi();

                       }
                   }
        );


    }

    private void callData(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(url_data, new Response.Listener<JSONArray>(){

            public void onResponse(JSONArray response){
                Log.e(TAG, response.toString());
              //parsing json
                for(int i = 0;i < response.length();i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Jarak j = new Jarak();

                        j.setNama(obj.getString("nama_Reseller"));
                        j.setGambar(obj.getString("gambar"));

                        double jarak = Double.parseDouble(obj.getString("jarak"));

                        j.setJarak("" + round(jarak, 2));

                        itemList.add(j);


                    } catch (JSONException e) {
              e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }


        } , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }


    @Override
    public void onRefresh() {
        lokasi();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        cariData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

   // @Override
   // public boolean onCreateOptionsMenu(android.view.Menu menu) {
     //   getMenuInflater().inflate(R.menu.menu, menu);
      //  final MenuItem item = menu.findItem(R.id.action_search);
       // final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
       // searchView.setQueryHint(getString(R.string.type_name));
       // searchView.setIconified(true);
       // searchView.setOnQueryTextListener(this);
       // return true;
  //  }


   private void cariData(final String keyword){
       pDialog = new ProgressDialog(MainActivity.this);
       pDialog.setCancelable(false);
       pDialog.setMessage("Loading...");
       pDialog.show();

       StringRequest strReq = new StringRequest(Request.Method.POST, url_cari, new Response.Listener<String>(){

           public void onResponse(String response) {
               Log.e("Response :", response.toString());

               try {
                   JSONObject jObj = new JSONObject(response);

                   int value = jObj.getInt(TAG_VALUE);

                   if (value == 1) {
                       itemList.clear();
                       adapter.notifyDataSetChanged();

                       String getObject = jObj.getString(TAG_RESULTS);
                       JSONArray jsonArray = new JSONArray(getObject);

                       for (int i = 0; i < jsonArray.length(); i++) {
                           JSONObject obj = jsonArray.getJSONObject(i);
                           Jarak j = new Jarak();
                           j.setId(obj.getString("id"));
                           j.setNama(obj.getString("nama_Reseller"));
                           j.setGambar(obj.getString("gambar"));

                          // double jarak = Double.parseDouble(obj.getString("jarak"));

                          // j.setJarak("" + round(jarak, 2));

                           itemList.add(j);
                       }
                       } else{
                           Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                       }
                   } catch(JSONException e){
                           e.printStackTrace();
                       }
                       adapter.notifyDataSetChanged();
                       pDialog.dismiss();
                   }
               },new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.e(TAG, "Error: " + error.getMessage());
               Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
               pDialog.dismiss();
           }
           }) {

               @Override
               protected Map<String, String> getParams() {
                   // Posting parameters to login url
                   Map<String, String> params = new HashMap<String, String>();
                   params.put("keyword", keyword);

                   return params;
               }

           };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
       }









    ////////////////////////////////////////////
                    ///////
                    ///////

    // fungsi ngecek lokasi GPS device pengguna
    private void lokasi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // permintaan update lokasi device dalam waktu per detik
        locationManager.requestLocationUpdates(provider, 1000, 1, this);

        if (location != null) {
            onLocationChanged(location);
        } else {
            /* latitude longitude default jika tidak ditemukan lokasi dari device pengguna */
            callListVolley(-8.098902, 112.166795);
        }
    }

    // untuk menampilkan lokasi wisata terdekat dari device pengguna
    private void callListVolley(double latitude, double longitude) {
        itemList.clear();
        adapter.notifyDataSetChanged();

        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(url + latitude + "&lng=" + longitude,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(TAG, response.toString());

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Jarak j = new Jarak();
                                j.setNama(obj.getString("nama_Reseller"));
                                j.setGambar(obj.getString("gambar"));

                                double jarak = Double.parseDouble(obj.getString("jarak"));

                                j.setJarak("" + round(jarak, 2));

                                itemList.add(j);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // memberitahu adapter jika ada perubahan data
                        adapter.notifyDataSetChanged();

                        swipe.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // menambah permintaan ke queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    // untuk menyederhanakan angka dibelakan koma jarak
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    // untuk menentukan lokasi gps dari device pengguna
    @Override
    public void onLocationChanged(Location location) {
        latitude_Reseller = location.getLatitude();
        longitude_Reseller = location.getLongitude();

        // untuk melihat latitude longitude posisi device pengguna pada logcat ditemukan atau tidak
        Log.e(TAG, "User location latitude:" + latitude_Reseller + ", longitude:" + longitude_Reseller);

        callListVolley(latitude_Reseller, longitude_Reseller);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
