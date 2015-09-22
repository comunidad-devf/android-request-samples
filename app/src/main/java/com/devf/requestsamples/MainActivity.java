package com.devf.requestsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.devf.requestsamples.domain.Artist;
import com.devf.requestsamples.io.ApiClient;
import com.devf.requestsamples.io.model.SearchArtistResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity implements Callback<SearchArtistResponse> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchArtistResponse response = new SearchArtistResponse();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiClient.searchArtist("willie", new Callback<SearchArtistResponse>() {
            @Override
            public void success(SearchArtistResponse searchArtistResponse, retrofit.client.Response response) {
                List<Artist> artists = searchArtistResponse.getArtist();

                for (Artist artist : artists){
                    Log.i("Retrofit", artist.getName());
                }

                ApiClient.searchArtist();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
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

    @Override
    public void success(SearchArtistResponse searchArtistResponse, retrofit.client.Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }
}
