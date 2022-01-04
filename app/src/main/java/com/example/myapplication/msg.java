package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class msg extends AppCompatActivity {
    EditText ms;
    Button bt;
    List<msset> Liste;
    da nn =new da();

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        String lname = getIntent().getExtras().getString("lname");
        int lid=getIntent().getExtras().getInt("listingid"),sid=getIntent().getExtras().getInt("sellerid");

//no app bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
//no appbar
        setContentView(R.layout.msgfromproduct);
        super.onCreate(savedInstanceState);
        Log.d("TAGon render", "onCreate: ");
        bt = findViewById(R.id.button4);
        ms = findViewById(R.id.ms);

        //json req
        /*JsonArrayRequest jam = new JsonArrayRequest(Request.Method.POST,nn.URL+"gettext.php",new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {

            }
        },new )*/

        JsonArrayRequest jar = new JsonArrayRequest(nn.URL+"gettext.php?listingname="+lname+"&senterid="+me+"&reciverid="+sid,
                responce -> {
                    try {
                        parse_data(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.d("TAG", "onCreate: "+error));
        queue = Volley.newRequestQueue(msg.this);
        queue.add(jar);

    }

    private void parse_data(JSONArray jarray) throws JSONException {
        for (int i =0;i<jarray.length();i++) {
            JSONObject jos = jarray.getJSONObject(i);
            msset l = new msset();
            l.setmsg(jos.getString("msg"));
            Log.d("TAG", "parse_data: "+jos.getString("msg")+"run"+i);
            l.setlistingid(jos.getInt("listingid"));
            l.setsenterid(jos.getInt("senterid"));
            l.setreciverid(jos.getInt("reciverid"));
            l.setlname(jos.getString("listingname"));
            l.setmsgid(jos.getInt("msgid"));
            Log.d("TAG run", "parse_data: "+i);

            Liste.add(l);
        }
        //adapter
        resinf  rar =new resinf(Liste,this);
      //  reses.setAdapter(rar);
    }



        //new way useing string
    /*    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, nn.URL+"gettext.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response.trim(), Toast.LENGTH_SHORT).show();
                Log.d("ONTAG", "onResponse: "+response);
                Log.d("TAG:", "onResponse: i was herte");
            }

        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString().trim(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map <String, String> param=new HashMap<String,String>();
                @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
                int me = sh.getInt("uid", 0);
                String lname = getIntent().getExtras().getString("lname");
                int lid=getIntent().getExtras().getInt("listingid"),sid=getIntent().getExtras().getInt("sellerid");

                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("lname",lname);
                MyData.put("reciverid", String.valueOf(sid));
                MyData.put("senterid", String.valueOf(me));
                return MyData;
            }
        };
*/
        //stop json req


    public void sent(View b){
        Log.d("TAG", "sent: insent");
        String msgr,lname=getIntent().getExtras().getString("listingname");
        int lid=getIntent().getExtras().getInt("listingid"),sid=getIntent().getExtras().getInt("sellerid");

        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show

        int me = sh.getInt("uid", 0);
        msgr=ms.getText().toString();
        Log.d("TAG", "sent: "+msgr+me+lname);
        Log.d("TAG", "sent:22 "+lid+sid);
        if(msgr!=null){
            RequestQueue queue = Volley.newRequestQueue(msg.this);
            StringRequest msgo = new StringRequest(Request.Method.POST, new StringBuilder().append(nn.URL).append("msgin.php").toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("success")){
                        Log.d("TAG?", "onResponse: "+response); ms.setText(" ");}
                    else if (response.trim().equals("fail"))
                        Log.d("TAG", "onResponse: failed sent");
                    else {
                        Log.d("TAG", "onResponse: whats"+response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("msg",msgr);
                    data.put("reciverid", String.valueOf(sid));
                    data.put("senterid", String.valueOf(me));
                    data.put("listingname",lname);
                    data.put("lid", String.valueOf(lid));
                    return data;
            }



        };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(msgo);

    }}
    public void toho(View v){
        Intent i = new Intent(msg.this, Success.class);
        startActivity(i);
    }
    public void toh(View v){

        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        String lname = getIntent().getExtras().getString("lname");
        int lid=getIntent().getExtras().getInt("listingid"),sid=getIntent().getExtras().getInt("sellerid");
        Log.d("TAGla gold", "onCreate: me"+me+"lname"+lname+"lid"+lid+"sellerid"+sid);

    }

}
