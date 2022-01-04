package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView register;
    EditText email,password;
    String EMAIL,PASSWORD;
    da n = new da();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//no app bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
//no appbar
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        //register = findViewById(R.id.login_register);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);

    }

    public void login(View view) {
        EMAIL = email.getText().toString().trim();
        PASSWORD = password.getText().toString().trim();

        if(!EMAIL.equals("") && !PASSWORD.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL+"login.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("resok",response);
                    //response="success";
                    String nes = response.toString();
                    Log.d("resok",nes);
                    if (!response.trim().equals("sus")) {
                        Log.d("TAG", "onResponse: worked");

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                        // Creating an Editor object to edit(write to the file)
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putInt("uid", Integer.parseInt(response.trim()));
                        myEdit.commit();
                        Intent intent = new Intent(MainActivity.this, Success.class);
                        //intent.putExtra("me",EMAIL);
                       // intent.putExtra("pwd",PASSWORD);
                        startActivity(intent);
                        finish();
                    } else if(response.trim().equals("failure")) {
                        Log.d("TAG", "onResponse: not in bro ");
                        Toast.makeText(MainActivity.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", EMAIL);
                    data.put("password", PASSWORD);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void go(View v){

        Intent i = new Intent(this,  register.class);
        startActivity(i);
        Log.d("main", "Login: button to text");
        }
        public void mer(View v){
            Intent i = new Intent(this,  newr.class);
            i.putExtra("me","tr");
            i.putExtra("pwd","trt");
            startActivity(i);
            finish();
        }

}





