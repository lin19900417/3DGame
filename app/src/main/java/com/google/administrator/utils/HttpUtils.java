package com.google.administrator.utils;

import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/6.
 */
public class HttpUtils {
    public static byte[] download(String urlpath){
        try {
            URL url=new URL(urlpath);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();
            Log.i("aaaa","connection.getResponseCode="+connection.getResponseCode());
            if(connection.getResponseCode()==200){
                Log.i("aaaa","HttpUtils里的if执行了");
                InputStream is=connection.getInputStream();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                byte[] bs=new byte[1024*4];
                int len=-1;
                while((len=is.read(bs))!=-1){
                   baos.write(bs,0,len);
                }
                if(is!=null){
                    is.close();
                }
                return baos.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
