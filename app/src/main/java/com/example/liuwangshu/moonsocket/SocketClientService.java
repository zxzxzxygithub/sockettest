package com.example.liuwangshu.moonsocket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketClientService extends Service {
    public  static final int PORT = 8688;
    public static final String TAG = "socket Client ";
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    Socket socket = null;

    public SocketClientService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connectServer();
    }

    private void connectServer() {
        new Thread() {
            @Override
            public void run() {
                connectSocketServer();
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = "send msg from client :" + System.currentTimeMillis();
        try {
            mPrintWriter.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onStartCommand:  send msg to server " + msg);
        return super.onStartCommand(intent, flags, startId);
    }

    private void connectSocketServer() {

        while (socket == null) {
            try {
                //选择和服务器相同的端口8688
                socket = new Socket("127.0.0.1", PORT);
//                socket = new Socket("localhost", PORT);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Log.d(TAG, "connectSocketServer:_port_" + PORT);
            } catch (IOException e) {
                SystemClock.sleep(1000);
            }
        }
        BufferedReader br = null;
        try {
            // 接收服务器端的消息
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                final String msg = br.readLine();
                if (msg != null) {
                    Log.d(TAG, "read from server: " + msg);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mPrintWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
