package com.devf.requestsamples.io;

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
public class Constants {

    public static final String URL_BASE = "https://api.spotify.com/v1";

    public static final String PATH_SEARCH = "/search";

    public static final String PARAM_TYPE = "type";

    public static final String PARAM_QUERY = "q";

    public static final String VALUE_ARTIST = "artist";

    public static final String URL_SEARCH_ARTIST = PATH_SEARCH + "?"
            + PARAM_TYPE + "=" + VALUE_ARTIST;

}
