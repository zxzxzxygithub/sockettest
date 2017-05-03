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
     * 保存异常信息到sdcard中
     *
     * @param pContext
     */
    public static void saveToSdcard(Context pContext, String content) {
        String fileName;
        StringBuffer sBuffer = new StringBuffer();
        // 添加异常信息
        sBuffer.append(content);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File file1 = new File("sdcard/asocketlog");
            if (!file1.exists()) {
                file1.mkdir();
            }
            fileName = "asockettest.log";
            File file2 = new File(file1.getPath(),fileName);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file2, true);
                fos.write(sBuffer.toString().getBytes());
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
