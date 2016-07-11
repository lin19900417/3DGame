package com.google.anministrator.cache;

import android.content.pm.LauncherApps;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/6.
 */
public class WebCache {
    public static byte[] download(String urlpath) {
        try {
            URL url = new URL(urlpath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();
            Log.i("aaaa", "connection.getResponseCode=" + connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                Log.i("aaaa", "HttpUtils里的if执行了");
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bs = new byte[1024 * 4];
                int len = -1;
                while ((len = is.read(bs)) != -1) {
                    baos.write(bs, 0, len);
                }
                if (is != null) {
                    is.close();
                }
                return baos.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getWebCache(final String urlpath, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bs = download(urlpath);
                Log.i("aaaa","CallBack----urlpath"+urlpath);
                Log.i("aaaa","CallBack---bs"+bs);
                callback.getResult(bs);
            }
        }).start();
    }

    public interface Callback {
         public void getResult(byte[] bs);
    }
}
