package com.example.liuwangshu.moonsocket;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketClientActivity extends AppCompatActivity {
    private static final int REQUESTCODE_SCLIENT = 0x111;
    private Button bt_send;
    private EditText et_receive;
    private TextView tv_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        initView();
        Intent service = new Intent(this, SocketServerService.class);
        startService(service);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerAtMills = System.currentTimeMillis();
        long intervalMills = 1000;
        PendingIntent operation = PendingIntent.getService(SocketClientActivity.this, REQUESTCODE_SCLIENT, new Intent(SocketClientActivity.this, SocketClientService.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMills, intervalMills, operation);


    }

    private void initView() {
        et_receive = (EditText) findViewById(R.id.et_receive);
        bt_send = (Button) findViewById(R.id.bt_send);
        tv_message = (TextView) this.findViewById(R.id.tv_message);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(SocketClientActivity.this, SocketClientService.class));
            }
        });

    }


}
