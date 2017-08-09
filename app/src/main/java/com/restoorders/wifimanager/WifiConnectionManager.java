package com.restoorders.wifimanager;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import java.net.InetAddress;

import static android.R.attr.port;

/**
 * Created by akshayborgave on 26/07/17.
 */

public class WifiConnectionManager {

    Context mContext;
    private static final String TAG ="WifiConnectionManager";
    public String SERVICE_NAME = "Server Device";
    public String SERVICE_TYPE = "_http._tcp.";
    private NsdManager mNsdManager;
    private InetAddress mHostAddress;
    private int mHostPort;


    public WifiConnectionManager(Context context) {

        mContext = context;
        mNsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);

    }


    public void registerNsdService(NsdManager.RegistrationListener registrationListener){

        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setServiceName(SERVICE_NAME);
        serviceInfo.setServiceType(SERVICE_TYPE);
        serviceInfo.setPort(mHostPort);

        mNsdManager.registerService(serviceInfo,
                NsdManager.PROTOCOL_DNS_SD,
                registrationListener);
    }

    public void unRegisterService(NsdManager.RegistrationListener registrationListener){

        if (mNsdManager != null) {
            mNsdManager.unregisterService(registrationListener);
        }

    }



    public void discoverDevice(NsdManager.DiscoveryListener discoveryListener){

        if(mNsdManager != null) {
            mNsdManager.discoverServices(SERVICE_TYPE,
                    NsdManager.PROTOCOL_DNS_SD, discoveryListener);
        }
    }


    public void stopDiscoveringdevice(NsdManager.DiscoveryListener discoveryListener){

        if(mNsdManager != null){
            mNsdManager.stopServiceDiscovery(discoveryListener);
        }
    }

    public String getSERVICE_NAME() {
        return SERVICE_NAME;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) {
        this.SERVICE_NAME = SERVICE_NAME;
    }

    public String getSERVICE_TYPE() {
        return SERVICE_TYPE;
    }

    public void setSERVICE_TYPE(String SERVICE_TYPE) {
        this.SERVICE_TYPE = SERVICE_TYPE;
    }

    public NsdManager getmNsdManager() {
        return mNsdManager;
    }

    public InetAddress getmHostAddress() {
        return mHostAddress;
    }

    public int getmHostPort() {
        return mHostPort;
    }

    public void setmHostAddress(InetAddress mHostAddress) {
        this.mHostAddress = mHostAddress;
    }

    public void setmHostPort(int mHostPort) {
        this.mHostPort = mHostPort;
    }
}
