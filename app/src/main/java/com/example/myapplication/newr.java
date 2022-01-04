package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class newr extends FragmentActivity implements OnMapReadyCallback {
    RequestQueue queue;
     GoogleMap mMap;
    float lat= 35,longi=35;
    TextView head,details;
    ImageView img;
    String TAG ="Newr Activity";
    String header;
    int sid, lid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newr);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        head= findViewById(R.id.heading);
        details = findViewById(R.id.Details);
        img = findViewById(R.id.image);

    }


    public void onMapReady(GoogleMap googleMap) {da n = new da();
        String url = n.URL+"readCourses.php";
        mMap = googleMap;
        header=getIntent().getExtras().getString("head");
        head.setText(header);
        String details2= getIntent().getExtras().getString("Details");
        details.setText(details2);
        //load an imge using picasso
        String lin = getIntent().getExtras().getString("imglink");
        Picasso.with(this).load(lin).into(img);
        float longi =getIntent().getExtras().getFloat("longi");
        float lat = getIntent().getExtras().getFloat("lat");

        //float l2 = 35;
        // Add a marker in Sydney and move the camera
        //LatLng Kerala = new LatLng(l,l2);
       // int l = getIntent().getExtras().getInt("head");
      //  System.out.print(l);
        // Log.d("TAG", "onMapReady: lat"+l1); Log.d("TAG", "onMapReady: long"+l2);
        Log.d(TAG, "onMapReady: lat "+lat);
        Log.d(TAG, "onMapReady: longi "+longi);
         sid = getIntent().getExtras().getInt("sid");
       lid =getIntent().getExtras().getInt("lid");
        Log.d(TAG, "onMapReady: sid "+sid);
        Log.d(TAG, "onMapReady: Listing id "+lid);




        /* StringRequest gets = new StringRequest( Request.Method.POST,url,new com.android.volley.Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                JSONObject jsonObject ;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if (!(jsonObject.getString("head") == null)) {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
*/


       // Log.d("maps", "onMapReady: "+l);
        int la = (int) lat;
        int lo  = (int)longi;
        LatLng Kerala = new LatLng(la,lo);
        mMap.addMarker(new MarkerOptions().position(Kerala)
                .title("Marker in place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Kerala));
    }
    public void m(View v){
       // String me=getIntent().getExtras().getString("me");
     //   String pwd=getIntent().getExtras().getString("pwd");
        Intent intent = new Intent(newr.this, Success.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
    public void mse(View v){
        Intent intent = new Intent(newr.this, msg.class);
        Log.d("TAG", "mse: ok");
        intent.putExtra("lname",header);
        intent.putExtra("sellerid",sid);
        intent.putExtra("listingid",lid);

        startActivity(intent);
        finish();
    }

}
/* Scraped need for arrayrequest

JsonArrayRequest jar = new JsonArrayRequest(url,
                responce -> {
                    try {
                        parse_data(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(newr.this, "data get error", Toast.LENGTH_SHORT).show());
        queue = Volley.newRequestQueue(newr.this);
        queue.add(jar);

    }

    private void parse_data(JSONArray jarray) throws JSONException {

            JSONObject jos = jarray.getJSONObject(0);
            Listing l = new Listing();
            String link=jos.getString("imagelink");
            String Deta= jos.getString("long_details");
            String Tit=jos.getString("Listing_title");
            float  longi =(float) jos.getDouble("longi");
            float lat=(float) jos.getDouble("lat");
           int slid=jos.getInt("sellerid");
            int Li =jos.getInt("Lid");

        }*/