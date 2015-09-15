package com.devf.requestsamples;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created by Pedro Hernández on 09/2015.
 */
public class BusquedaArtistaAsyncTask extends AsyncTask<String, Void, List<Artista>> {

    public static final String LOG_TAG = BusquedaArtistaAsyncTask.class.getSimpleName();

    @Override
    protected List<Artista> doInBackground(String... params) {

        if (params[0] == null) {
            Log.e(LOG_TAG, "No especificaste el query de busqueda");
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String queryBusqueda = params[0];
        String respuesta;

        String urlBusqueda = "https://api.spotify.com/v1/search?type=artist&q=" + queryBusqueda;

        try {
            URL url = new URL(urlBusqueda);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            if (inputStream == null) {
                Log.e(LOG_TAG, "No hay respuesta");
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder constructorDeRespuesta = new StringBuilder();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                constructorDeRespuesta.append(linea)
                        .append("\n");
            }

            if (constructorDeRespuesta.length() == 0) {
                Log.e(LOG_TAG, "Respuesta vacia");
                return null;
            }

            respuesta = constructorDeRespuesta.toString();

            return parsearRespuesta(respuesta);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Url erronea");
            e.printStackTrace();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Hubo un error abriendo la conexión");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Hubo un error en el parseo");
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "No se pudo cerrar el buffer");
                    e.printStackTrace();
                }
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Artista> artistas) {
        for (int i = 0; i < artistas.size(); i++) {
            Log.i(LOG_TAG, artistas.get(i).nombre);¬
        }
    }

    public List<Artista> parsearRespuesta(String respuesta) throws JSONException {
        ArrayList<Artista> artistas = new ArrayList<>();
        JSONObject respuestaJson = new JSONObject(respuesta);

        JSONObject artistasJson = respuestaJson.getJSONObject("artists");
        JSONArray items = artistasJson.getJSONArray("items");

        int tamanio = items.length();

        for (int i = 0; i < tamanio; i++) {
            JSONObject artista = items.getJSONObject(i);

            String name = artista.getString("name");

            artistas.add(new Artista(name));
        }

        return artistas;
    }
}
