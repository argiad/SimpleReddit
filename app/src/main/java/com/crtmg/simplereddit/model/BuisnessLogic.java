package com.crtmg.simplereddit.model;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.crtmg.simplereddit.interfaces.NetworkCallback;
import com.crtmg.simplereddit.model.network.NetworkHelper;
import com.crtmg.simplereddit.model.reddit.Children;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by argi on 12/27/17.
 */

public class BuisnessLogic {


    private final int posts_limit = 15;
    private NetworkHelper networkHelper;
    private String reddits = "all";
    private String filter = "new";

    private final int cacheSize = ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8;
    private LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            // The cache size will be measured in kilobytes rather than
            // number of items.
            return bitmap.getByteCount() / 1024;
        }
    };

    void setNetworkHelper(NetworkHelper helper){
        networkHelper = helper;
    }

    public void setNewRoute(String reddits, String filter) {
        this.reddits = reddits;
        this.filter = filter;
    }

    public void needMore(String before, NetworkCallback callback) {
        networkHelper.getList(reddits, filter, posts_limit, before, callback);
    }

    public LruCache<String, Bitmap> getLruCache() {
        return lruCache;
    }
}
