package com.example.pc.volley_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eName, eEmail, eMobile;
    Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        all_find_id();
        onclick();
    }

    private void all_find_id() {
        eName = (EditText) findViewById(R.id.e_Name);
        eEmail = (EditText) findViewById(R.id.e_Email);
        eMobile = (EditText) findViewById(R.id.e_Phone);
        bSave = (Button) findViewById(R.id.b_Save);

    }

    private void onclick() {
        bSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        insert_data();

    }
    private void insert_data(){
        final String name = eName.getText().toString().trim();
        final String email = eEmail.getText().toString().trim();
        final String phone = eMobile.getText().toString().trim();

        String REGISTER_URL = "http://192.168.0.104/volley_server/registan.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> content_value = new HashMap<String, String>();
                content_value.put("name", name);
                content_value.put("email", email);
                content_value.put("phone", phone);
                return content_value;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

