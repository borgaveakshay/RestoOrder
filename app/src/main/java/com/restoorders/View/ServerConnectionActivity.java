package com.restoorders.View;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.restoorders.Utils.SocketServerTask;
import com.restoorders.View.BaseViews.WifiConnectionBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshayborgave on 27/07/17.
 */

public class ServerConnectionActivity extends WifiConnectionBaseActivity {
    private int socketServerPort = 6000;
    private List<String> clientIPs;
    private ServerSocket serverSocket;
    private SocketServerThread socketServerThread;
    String clientIPAddress;

    @Override
    protected void onResume() {
        super.onResume();
        if (mNsdManager != null) {
            setProgressMessage("Server Registration Started...");
            showProgress();
            mAppContext.getmWifiConnectionManager().setmHostPort(socketServerPort);
            mAppContext.getmWifiConnectionManager().registerNsdService(mRegistrationListener);
        }
    }


    @Override
    protected void onPause() {

        if (mNsdManager != null) {
            mNsdManager.unregisterService(mRegistrationListener);
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mNsdManager != null) {
            mNsdManager.unregisterService(mRegistrationListener);
        }
        super.onDestroy();
    }

    private class SocketServerThread extends Thread {

        @Override
        public void run() {

            Socket socket = null;
            DataInputStream dataInputStream = null;
            DataOutputStream dataOutputStream = null;

            try {
                Log.i(TAG, "Creating server socket");
                serverSocket = new ServerSocket(socketServerPort);

                while (true) {
                    socket = serverSocket.accept();
                    dataInputStream = new DataInputStream(
                            socket.getInputStream());
                    dataOutputStream = new DataOutputStream(
                            socket.getOutputStream());

                    String messageFromClient, messageToClient, request;

                    //If no message sent from client, this code will block the program
                    messageFromClient = dataInputStream.readUTF();

                    try {
                        final JSONObject jsondata;
                        jsondata = new JSONObject(messageFromClient);
                        request = jsondata.getString("request");

                        if (request.equals(REQUEST_CONNECT_CLIENT)) {
                             clientIPAddress = jsondata.getString("ipAddress");

                            // Add client IP to a list
                            clientIPs.add(clientIPAddress);
                            messageToClient = "Connection Accepted";
                            dataOutputStream.writeUTF(messageToClient);
                            SocketMainHandler.sendEmptyMessage(1);
                        } else {
                            // There might be other queries, but as of now nothing.
                            dataOutputStream.flush();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Unable to get request");
                        dataOutputStream.flush();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    public Handler SocketMainHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case 1:
                    Toast.makeText(ServerConnectionActivity.this, clientIPAddress,Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };
    @Override
    protected void serviceRegistered() {

        clientIPs = new ArrayList<String>();
        socketServerThread = new SocketServerThread();
        socketServerThread.start();
    }
}
