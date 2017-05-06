package com.example.liuwangshu.moonsocket;

import android.content.Context;
import android.os.Trace;
import android.util.Log;

/**
 * @author zhengyx
 * @description 任务
 * @date 2017/5/3
 */
public class Task implements Runnable {
    private final Context context;
    private long outTime;
    private static final String TAG = "MyTask";

    private String content;

    public Task(Context context, String content) {
        this.context = context;
        this.content = content;
    }

    private boolean shutDown = false;

    public void run() {
        try {
            Utils.saveSocketInfoToSdcard(context, content, true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = " task has been error " + e.getMessage();
            Log.d(TAG, msg);
            Utils.saveErrorInfoToSdcard(context, msg, false);
        }
    }

    /**
     * @description 外部结束任务
     * @author zhengyx
     * @date 2017/5/3
     */
    public void shutDown() {
        shutDown = true;
    }
}
