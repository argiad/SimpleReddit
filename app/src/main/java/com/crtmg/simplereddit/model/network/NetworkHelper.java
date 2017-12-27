package com.crtmg.simplereddit.model.network;

import android.content.Context;
import android.util.Log;

import com.crtmg.simplereddit.BuildConfig;
import com.crtmg.simplereddit.R;
import com.crtmg.simplereddit.interfaces.NetworkCallback;
import com.crtmg.simplereddit.model.reddit.Children;
import com.crtmg.simplereddit.model.reddit.RedditError;
import com.crtmg.simplereddit.model.reddit.RedditResponse;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by argi on 12/27/17.
 */

public class NetworkHelper {


    private String TAG = NetworkHelper.class.getCanonicalName();
    private Context context;
    private API api;

    public static NetworkHelper getInstance(Context context) {
        NetworkHelper helper = new NetworkHelper();
        helper.context = context;
        helper.init();
        return helper;
    }

    // Init and prepare OkHttpClient and Retrofit
    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public void getList(String reddits, String filter, int limit, String before, final NetworkCallback callback) {
        Call<Object> call = api.listArticles(reddits, filter, limit, before);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Gson gson = new Gson();
                try {
                    if (response.code() >= 200 && response.code() < 300) {

                        RedditResponse redditResponse = gson.fromJson(gson.toJson(response.body()), RedditResponse.class);
                        List<Children> postList = redditResponse.getData().getChildren();
                        if (callback != null)
                            callback.newPosts(postList);

                    } else {
                        RedditError redditError = gson.fromJson(response.errorBody().string(), RedditError.class);
                        log(redditError.getMessage());
                        if (callback != null)
                            callback.returnError(String.format(Locale.getDefault(), context.getString(R.string.network_helper_error_response_code), response.code()));
                    }
                } catch (Exception e) {
                    if (callback != null)
                        callback.returnError(context.getString(R.string.network_helper_errer_parser));
                    log(call.request().toString());
                    log(response.body().toString());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (callback != null)
                    callback.returnError(context.getString(R.string.network_helper_error_failure));
                t.printStackTrace();
            }
        });
    }


    private void log(String string) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, string);
    }

}