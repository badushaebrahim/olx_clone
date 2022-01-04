package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    String TAG ="sh";
    Button register;
    //TextView register;
    EditText email,password,number,name,address,pw2;
    Integer Num;
    String EMAIL,PASSWORD,Number,Name,Address,pw22;
    //private String URL = "http://192.168.0.103/andro/regapi.php";
    da n = new da();
    protected void onCreate(Bundle savedInstanceState) {
        //no app bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
//no appbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);

        register = findViewById(R.id.login);
        //register = findViewById(R.id.login_register);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        number = findViewById(R.id.Number);
        name = findViewById(R.id.Name);
        address = findViewById(R.id.Address);
        pw2 = findViewById(R.id.password2);

    }
    public  void register(View v){
        if (!EMAIL.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            email.requestFocus();
            email.setError("please enter valid email");
        } else if (PASSWORD.length()<8) {
            password.requestFocus();
            password.setError("Please add password with atleast 8 characters");
        }

        else {
            //Volly_call_Login();
            Log.d("main", "Login:  ");


        }
    }
   /* public void register2(){
        Name = name.getText().toString();
        Address = address.getText().toString();
        Number = number.getText().toString();
        Num = Integer.parseInt(Number);
        EMAIL = email.getText().toString();
        PASSWORD = password.getText().toString();
        pw22 = pw2.getText().toString();
        Log.d("regs done", "register2: "+Name+Address+Num+EMAIL+PASSWORD+pw22);
        /*Intent i = new Intent(this,  MainActivity.class);
        startActivity(i);

    }*/
///register
    public void save(View v) {
        Name = name.getText().toString();
        Address = address.getText().toString();
        Number = number.getText().toString();
        Num = Integer.parseInt(Number);
        EMAIL = email.getText().toString();
        PASSWORD = password.getText().toString();
        pw22 = pw2.getText().toString();

        if(!PASSWORD.equals(pw22)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else if(!Name.equals("") && !EMAIL.equals("") && !PASSWORD.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"regapi.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("success")) {
                        Log.d(TAG, "onResponse: uss");

                    } else if (response.trim().equals("failure")) {
                        Log.d(TAG, "onResponse: fil");


                    }Log.d(TAG, "onResponse: "+response);
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
                    data.put("name", Name);
                    data.put("email", EMAIL);
                    data.put("password", PASSWORD);
                    data.put("address",Address);
                    data.put("number",Number);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);




           Intent intent = new Intent(register.this, MainActivity.class);
            intent.putExtra("me", "rumba");
            startActivity(intent);
            finish();

        }
    }





}
