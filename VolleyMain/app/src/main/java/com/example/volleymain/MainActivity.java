package com.example.volleymain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Model> listItem;
    String reqUrl="https://jsonplaceholder.typicode.com/photos";
    RcView rcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItem=new ArrayList<>();

        rcView=new RcView(listItem,this);

        recyclerView=findViewById(R.id.rcView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rcView);

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.start();


        final JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, reqUrl,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i <= response.length(); i++) {

                        JSONObject obj = response.getJSONObject(i);

                        Model model=new Model(obj.getInt("albumId"),obj.getInt("id"),
                                obj.getString("title"),obj.getString("url"),obj.getString("thumbnailUrl"));

                        listItem.add(model);

                        rcView.notifyDataSetChanged();

                        Log.d("RES","Reponse is"+"\t"+obj.getString("title"));
                        Log.d("DIS","Reponse is"+"\t"+obj.getString("thumbnailUrl"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);
    }
}
