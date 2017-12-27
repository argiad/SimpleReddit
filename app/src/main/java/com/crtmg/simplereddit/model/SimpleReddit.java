package com.crtmg.simplereddit.model;

import android.app.Application;

import com.crtmg.simplereddit.interfaces.NetworkCallback;
import com.crtmg.simplereddit.model.network.NetworkHelper;

/**
 * Created by argi on 12/27/17.
 */

public class SimpleReddit extends Application {

    public BuisnessLogic buisnessLogic = new BuisnessLogic();

    @Override
    public void onCreate() {
        super.onCreate();
        buisnessLogic.setNetworkHelper(NetworkHelper.getInstance(getApplicationContext()));
    }



}
