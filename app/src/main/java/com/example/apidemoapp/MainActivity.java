package com.example.apidemoapp;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        volleyRequest();
    }

    public void volleyRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/myproject/getdata.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,

        new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                decodeJson(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("exception", error.toString());
            }
        });
        queue.add(stringRequest);
    }
        public void decodeJson(String response){
            try {
                ArrayList<DataModel> data=new ArrayList<>();
                JSONObject result=new JSONObject(response);
                JSONArray array=result.getJSONArray("data");
                for(int i=0;i<array.length();i++){
                    JSONObject student=array.getJSONObject(i);
                    int id=student.getInt("id");
                    String name= (String) student.get("name");
                    String address= (String) student.get("address");
                    DataModel dataModel=new DataModel(id,name,address);
                    data.add(dataModel);
                }
                layoutManager=new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerAdapter=new RecyclerViewAdapter(MainActivity.this,data);
                recyclerView.setAdapter(recyclerAdapter);
            }catch (Exception e){
                Log.d("exception", e.toString());
            }
        }

    }


