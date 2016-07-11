package com.google.anministrator.cache;


import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import ImageCompression.Compression;

/**
 * Created by Administrator on 2016/7/7.
 */
public class ManagerCache {
    WebCache webCache=new WebCache();
    FileCache fileCache=new FileCache();
    MemoryCache memoryCache=new MemoryCache();
    Handler handler=new Handler();

    public void getCache(final String urlpath,final ImageView imageView){
        Bitmap bitmap=null;
        if (memoryCache.getBitmapFromMemoryCache(urlpath)!=null){
            bitmap=memoryCache.getBitmapFromMemoryCache(urlpath);
            imageView.setImageBitmap(bitmap);
        }else if(fileCache.getBitmapFromSD(urlpath)!=null){
            bitmap=fileCache.getBitmapFromSD(urlpath);
            memoryCache.addBitmapToMemoryCache(urlpath,bitmap);
            imageView.setImageBitmap(bitmap);
        }else{
            webCache.getWebCache(urlpath, new WebCache.Callback() {
                @Override
                public void getResult(byte[] data) {
                    final Bitmap bitmap1 = Compression.getCompressionBitmap(data, 50, 50);
                    byte[] d = getbyteFromBitmap(bitmap1);
                    fileCache.saveFileToSD(d, urlpath);
                    Log.i("aaaa","Manager----byte[] d---"+d);
                    Log.i("aaaa","ManagerCache保存成功");
                    memoryCache.addBitmapToMemoryCache(urlpath, bitmap1);
                    handler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         imageView.setImageBitmap(bitmap1);
                                     }
                                 }
                    );
                }
            });
        }
    }

    public byte[] getbyteFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
}

