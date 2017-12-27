package com.crtmg.simplereddit.model.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by argi on 12/27/17.
 */

public interface API {

    @Headers({"User-Agent: SimpleRedditClient"})
    @GET("{reddits}/{filter}.json")
    Call<Object> listArticles(
            @Path("reddits") String reddits,
            @Path("filter") String filter,
            @Query("limit") int limit,
            @Query("after") String after
    );
}
