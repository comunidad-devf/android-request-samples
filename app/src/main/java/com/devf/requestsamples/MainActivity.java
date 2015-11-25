package com.devf.requestsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ejecutaPeticionConVolley("u2");
        ejecutaPeticionJsonConVolley("u2");
    }

    public void ejecutarPeticionConAsyncTask(){
        BusquedaArtistaAsyncTask peticion = new BusquedaArtistaAsyncTask();
        peticion.execute("Daft");
    }

    public void ejecutaPeticionConVolley(String query){
        String url =  "https://api.spotify.com/v1/search?type=artist&q=" + query;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Respuesta", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.getMessage());
            }
        });


        VolleySingleton.getInstance(this)
                .addToRequestQueue(request);
    }



    public void ejecutaPeticionJsonConVolley(String query){
        String url =  "https://api.spotify.com/v1/search?type=artist&q=" + query;

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Respuesta", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(this)
                .addToRequestQueue(jsonRequest);
    }


}
