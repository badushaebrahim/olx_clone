package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Success extends AppCompatActivity {
    TextView new2;
   // String me="john",pwd="pass";
    List<Listing> Liste;
    RequestQueue queue;
    //private String URL = "http://192.168.0.104/andro/getdata.php";
    da n = new da();
    FloatingActionButton mAddAlarmFab, mAddPersonFab;
    ExtendedFloatingActionButton mAddFab;
    TextView addAlarmActionText, addPersonActionText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;
    RecyclerView reses;
  //  RecyclerView.Adapter reseradapter;
    RecyclerView.LayoutManager reslay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //no app bar
        try
    {
        this.getSupportActionBar().hide();
    }
        catch (NullPointerException e){}
    //no appbar
    //setContentView(R.layout.activity_main);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.sucess);
        new2 = findViewById(R.id.text2);
        reses =findViewById(R.id.recyclerView);
        reslay= new LinearLayoutManager(this);
        reses.setLayoutManager(reslay);

        Liste = new ArrayList<>();
        //new3 = findViewById(R.id.text22);

      //  me=getIntent().getExtras().getString("me");
        // pwd=getIntent().getExtras().getString("pwd");
     //   Log.d("TAG", "up: "+me);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show

        int a = sh.getInt("uid", 0);

// We can then use the data
        Log.d("TAG", "onCreate: id of user "+a);
        //shit
        mAddFab = findViewById(R.id.add_fab);
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);
        mAddFab = findViewById(R.id.add_fab);
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);
        addAlarmActionText =
                findViewById(R.id.add_alarm_action_text);
        addPersonActionText =
                findViewById(R.id.add_person_action_text);
        // Now set all the FABs and all the action name
        // texts as GONE
        mAddAlarmFab.setVisibility(View.GONE);
        mAddPersonFab.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        addPersonActionText.setVisibility(View.GONE);
        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        isAllFabsVisible = false;
        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink();
        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            mAddAlarmFab.show();
                            mAddPersonFab.show();
                            addAlarmActionText
                                    .setVisibility(View.VISIBLE);
                            addPersonActionText
                                    .setVisibility(View.VISIBLE);

                            // Now extend the parent FAB, as
                            // user clicks on the shrinked
                            // parent FAB
                            mAddFab.extend();
                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {
                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            mAddAlarmFab.hide();
                            mAddPersonFab.hide();
                            addAlarmActionText
                                    .setVisibility(View.GONE);
                            addPersonActionText
                                    .setVisibility(View.GONE);
                            // Set the FAB to shrink after user
                            // closes all the sub FABs
                            mAddFab.shrink();
                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });
        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddPersonFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText
                                (Success
                                                .this, "Person Added",
                                        Toast.LENGTH_SHORT).show();
                    }
                });
        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddAlarmFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText

                                (Success.this ,"Alarm Added",
                                        Toast.LENGTH_SHORT).show();
                    }
                });
        //shit here
       // new2.setText(me);
        //new3.setText(pwd);
        JsonArrayRequest jar = new JsonArrayRequest(n.URL+"getdata.php",
                responce -> {
                    try {
                        parse_data(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(Success.this, "data get error", Toast.LENGTH_SHORT).show());
            queue = Volley.newRequestQueue(Success.this);
            queue.add(jar);

    }

    private void parse_data(JSONArray jarray) throws JSONException {
        for (int i =0;i<jarray.length();i++) {
            JSONObject jos = jarray.getJSONObject(i);
            Listing l = new Listing();
            l.setLink(jos.getString("imagelink"));
            l.setDetail(jos.getString("long_details"));
            l.setHead(jos.getString("Listing_title"));
            l.setLat((float) jos.getDouble("latitude"));
            l.setLongi((float) jos.getDouble("longi"));
            l.setSellerid(jos.getInt("sellerid"));
            l.setLid(jos.getInt("Lid"));
            Liste.add(l);
        }
        //adapter
        resinf  rar =new resinf(Liste,this);
        reses.setAdapter(rar);
    }


    public  void rr(View v){
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show

        int me = sh.getInt("uid", 0);
        //String me=getIntent().getExtras().getString("me");
       // String pwd=getIntent().getExtras().getString("pwd");
        Intent intent = new Intent(Success.this, newr.class);
        //intent.putExtra("me",me);
      //  intent.putExtra("pwd",pwd);
        startActivity(intent);
        finish();

    }
    public void mse(View v){
        Intent intent = new Intent(Success.this, msg.class);
        Log.d("TAG", "mse: ok");
        startActivity(intent);
        finish();
    }



}
