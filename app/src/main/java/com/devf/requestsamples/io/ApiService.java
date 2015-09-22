package com.devf.requestsamples.io;

import com.devf.requestsamples.domain.Artist;
import com.devf.requestsamples.io.model.SearchArtistResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

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
public interface ApiService {

    @GET(Constants.URL_SEARCH_ARTIST)
    void searchArtist(@Query(Constants.PARAM_QUERY) String query,
                      Callback<SearchArtistResponse> serverResponse);
}
