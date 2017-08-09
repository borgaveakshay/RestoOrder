package com.restoorders.View;

import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.restoorders.R;
import com.restoorders.View.BaseViews.WifiConnectionBaseActivity;

public class ClientConnectionActivity extends WifiConnectionBaseActivity {


    Button mSendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppContext.getmWifiConnectionManager().setSERVICE_NAME("Client Device");
        mSendButton = (Button) findViewById(R.id.sendData);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connectToHost();
            }
        });
    }

    @Override
    protected void onPause() {
        if (mNsdManager != null) {
            mNsdManager.stopServiceDiscovery(mDiscoveryListener);;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mNsdManager != null) {
            mNsdManager.discoverServices(
                    mAppContext.getmWifiConnectionManager().getSERVICE_TYPE(), NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
        }
    }

    @Override
    protected void onDestroy() {
        if (mNsdManager != null) {
            mNsdManager.stopServiceDiscovery(mDiscoveryListener);
        }
        super.onDestroy();
    }

    @Override
    protected void serviceResolved() {

//        mSendButton.setVisibility(View.VISIBLE);
    }
}
