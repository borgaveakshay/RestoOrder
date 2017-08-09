package com.restoorders.Utils;

import android.app.Application;

import com.restoorders.wifimanager.WifiConnectionManager;

/**
 * Created by akshayborgave on 26/07/17.
 */

public class AppContext extends Application {

    WifiConnectionManager mWifiConnectionManager;
    @Override
    public void onCreate() {
        super.onCreate();

        mWifiConnectionManager = new WifiConnectionManager(this);
    }

    public WifiConnectionManager getmWifiConnectionManager() {
        return mWifiConnectionManager;
    }

}
