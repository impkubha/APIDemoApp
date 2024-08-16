package com.example.apidemoapp;
;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class SendData extends AppCompatActivity {
    EditText edtId, edtName, edtAddress;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_data);
        edtId=findViewById(R.id.edtId);
        edtName=findViewById(R.id.edtName);
        edtAddress=findViewById(R.id.edtAddress);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volleyRequest();
            }
        });
    }

    public void volleyRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/myproject/getdata.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("exception", error.toString());
            }
        }){
            protected HashMap<String,String> getParams(){
             HashMap<String, String> prams=new HashMap<>();
             prams.put("id",edtId.getText().toString());
                prams.put("name",edtName.getText().toString());
                prams.put("address",edtAddress.getText().toString());
                return prams;
            };
        };

queue.add(stringRequest);


    }


}


