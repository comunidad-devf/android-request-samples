package com.devf.requestsamples.io;

import com.devf.requestsamples.io.model.SearchArtistResponse;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Created by Pedro Hernández on 09/2015.
 */
public class ApiClient {
    private static ApiService apiService;

    public static ApiService getInstance(){
        if (apiService == null){

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.URL_BASE)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build();

            apiService = restAdapter.create(ApiService.class);

        }
        return apiService;
    }

    public static void searchArtist(String query, Callback<SearchArtistResponse> serverResponse){
        getInstance().searchArtist(query, serverResponse);
    }
}
