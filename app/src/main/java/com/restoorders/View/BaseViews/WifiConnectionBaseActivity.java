package com.restoorders.View.BaseViews;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.restoorders.Utils.AppContext;
import com.restoorders.Utils.SocketServerTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by akshayborgave on 26/07/17.
 */

public class WifiConnectionBaseActivity extends BaseActivity {

    public static final String TAG = "WifiBaseActivity";
    protected static final String REQUEST_CONNECT_CLIENT = "request-connect-client";
    protected AppContext mAppContext;
    protected NsdManager mNsdManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppContext = (AppContext) getApplicationContext();
        mNsdManager = mAppContext.getmWifiConnectionManager().getmNsdManager();
    }

   protected NsdManager.DiscoveryListener mDiscoveryListener = new NsdManager.DiscoveryListener() {

        // Called as soon as service discovery begins.
        @Override
        public void onDiscoveryStarted(String regType) {

            showProgress();
            Log.d(TAG, "Service discovery started");
        }

        @Override
        public void onServiceFound(NsdServiceInfo service) {
            hideProgress();
            // A service was found! Do something with it.
            Log.d(TAG, "Service discovery success : " + service);
            Log.d(TAG, "Host = "+ service.getServiceName());
            Log.d(TAG, "port = " + String.valueOf(service.getPort()));

            if (!service.getServiceType().equals(mAppContext.getmWifiConnectionManager().getSERVICE_TYPE().toString())) {
                // Service type is the string containing the protocol and
                // transport layer for this service.
                Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
            } else if (service.getServiceName().equals(mAppContext.getmWifiConnectionManager().getSERVICE_NAME())) {
                // The name of the service tells the user what they'd be
                // connecting to. It could be "Bob's Chat App".
                Log.d(TAG, "Same machine: " + mAppContext.getmWifiConnectionManager().getSERVICE_NAME());
            } else {
                Log.d(TAG, "Diff Machine : " + service.getServiceName());
                // connect to the service and obtain serviceInfo
                mAppContext.getmWifiConnectionManager().getmNsdManager().resolveService(service, mResolveListener);
            }
        }

        @Override
        public void onServiceLost(NsdServiceInfo service) {
            hideProgress();
            // When the network service is no longer available.
            // Internal bookkeeping code goes here.
            Log.e(TAG, "service lost" + service);
        }

        @Override
        public void onDiscoveryStopped(String serviceType) {
            hideProgress();
            Log.i(TAG, "Discovery stopped: " + serviceType);
        }

        @Override
        public void onStartDiscoveryFailed(String serviceType, int errorCode) {
            hideProgress();
            Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            mAppContext.getmWifiConnectionManager().getmNsdManager().stopServiceDiscovery(this);
        }

        @Override
        public void onStopDiscoveryFailed(String serviceType, int errorCode) {
            hideProgress();
            Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            mNsdManager.stopServiceDiscovery(this);
        }
    };

   protected NsdManager.ResolveListener mResolveListener = new NsdManager.ResolveListener() {

        @Override
        public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
            hideProgress();
            // Called when the resolve fails. Use the error code to debug.
            Log.e(TAG, "Resolve failed " + errorCode);
            Log.e(TAG, "serivce = " + serviceInfo);
        }

        @Override
        public void onServiceResolved(NsdServiceInfo serviceInfo) {
            Log.d(TAG, "Resolve Succeeded. " + serviceInfo);

            hideProgress();
            if (serviceInfo.getServiceName().equals(mAppContext.getmWifiConnectionManager().getSERVICE_NAME())) {
                Log.d(TAG, "Same IP.");
                return;
            }

            // Obtain port and IP
            mAppContext.getmWifiConnectionManager().setmHostPort(serviceInfo.getPort());
            mAppContext.getmWifiConnectionManager().setmHostAddress(serviceInfo.getHost());
            Toast.makeText(WifiConnectionBaseActivity.this, "Device connected", Toast.LENGTH_SHORT).show();

            serviceResolved();

        }
    };

    protected NsdManager.RegistrationListener mRegistrationListener = new NsdManager.RegistrationListener() {

        @Override
        public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
            hideProgress();
            String mServiceName = NsdServiceInfo.getServiceName();
            mAppContext.getmWifiConnectionManager().setSERVICE_NAME(mServiceName);
            Log.d(TAG, "Registered name : " + mServiceName);
            serviceRegistered();

        }

        @Override
        public void onRegistrationFailed(NsdServiceInfo serviceInfo,
                                         int errorCode) {
            hideProgress();
            // Registration failed! Put debugging code here to determine
            // why.
        }

        @Override
        public void onServiceUnregistered(NsdServiceInfo serviceInfo) {
            hideProgress();
            // Service has been unregistered. This only happens when you
            // call
            // NsdManager.unregisterService() and pass in this listener.
            Log.d(TAG,
                    "Service Unregistered : " + serviceInfo.getServiceName());
        }

        @Override
        public void onUnregistrationFailed(NsdServiceInfo serviceInfo,
                                           int errorCode) {
            hideProgress();
            // Unregistration failed. Put debugging code here to determine
            // why.
        }
    };

    public void connectToHost() {

        if (mAppContext.getmWifiConnectionManager().getmHostAddress() == null) {
            Log.e(TAG, "Host Address is null");
            return;
        }

        String ipAddress = getLocalIpAddress();
        JSONObject jsonData = new JSONObject();

        try {
            jsonData.put("request", REQUEST_CONNECT_CLIENT);
            jsonData.put("ipAddress", ipAddress);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "can't put request");
            return;
        }

        new SocketServerTask(this).executeSocket(jsonData);
    }

    private String getLocalIpAddress() {
        WifiManager wm = (WifiManager) mAppContext.getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

    protected void serviceRegistered(){

    }

    protected  void serviceResolved(){

    }

}
