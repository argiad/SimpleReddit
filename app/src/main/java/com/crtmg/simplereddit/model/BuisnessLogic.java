package com.crtmg.simplereddit.model;

import com.crtmg.simplereddit.interfaces.NetworkCallback;
import com.crtmg.simplereddit.model.network.NetworkHelper;

/**
 * Created by argi on 12/27/17.
 */

public class BuisnessLogic {


    private final int posts_limit = 15;
    private NetworkHelper networkHelper;
    private String reddits = "all";
    private String filter = "new";

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
}
