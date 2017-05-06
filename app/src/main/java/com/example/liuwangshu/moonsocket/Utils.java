package com.example.liuwangshu.moonsocket;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhengyongxiang on 2017/5/3.
 */

public class Utils {

    /**
     * 保存error信息到sdcard中
     *
     * @param pContext
     */
    public static void saveErrorInfoToSdcard(Context pContext, String content, boolean append) {
        String fileName = "socketError.log";
        saveToSdcard(pContext, fileName, content, append);
    }

    /**
     * 保存socket信息到sdcard中
     *
     * @param pContext
     */
    public static void saveSocketInfoToSdcard(Context pContext, String content, boolean append) {
        String fileName = "asockettest.log";
        saveToSdcard(pContext, fileName, content, append);
    }

    /**
     * 保存信息到sdcard中
     *
     * @param pContext
     */
    public static void saveToSdcard(Context pContext, String fileName, String content, boolean append) {
        StringBuffer sBuffer = new StringBuffer();
        // 添加异常信息
        sBuffer.append(content);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File file1 = new File("sdcard/asocketlog");
            if (!file1.exists()) {
                file1.mkdir();
            }
            File file2 = new File(file1.getPath(), fileName);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file2, append);
                fos.write(sBuffer.toString().getBytes());
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
