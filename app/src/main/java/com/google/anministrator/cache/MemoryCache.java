package com.google.anministrator.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MemoryCache {
    LruCache<String,Bitmap> lruCache;
    public MemoryCache(){
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheMemory=maxMemory/8;

        lruCache=new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    public synchronized void addBitmapToMemoryCache(String urlpath,Bitmap bitmap){
        if(urlpath!=null){
            if(bitmap!=null){
                lruCache.put(urlpath,bitmap);
            }
        }
    }
    public Bitmap getBitmapFromMemoryCache(String urlpath){
        if(urlpath!=null){
            if(lruCache.get(urlpath)!=null){
                return lruCache.get(urlpath);
            }
        }
        return null;
    }
    public void clear(){
        if(lruCache.size()>0){
            lruCache.evictAll();
        }
    }
}
