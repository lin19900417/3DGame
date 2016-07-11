package com.google.anministrator.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/7.
 */
public class FileCache {
    //SD卡的根目录
    private static final File SD_ROOT = Environment.getExternalStorageDirectory();
    //缓存目录
    private String cache_file = "filecache";
    //判断SD卡是否加载成功
    boolean isMounted = false;
    //缓存路径
    private static File FILE_CACHE = null;

    public FileCache() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isMounted = true;
            FILE_CACHE = new File(SD_ROOT, cache_file);
            if (FILE_CACHE != null) {
                FILE_CACHE.mkdirs();
                Log.i("aaaa", "sd卡挂载成功");

            }
        } else {
            Log.i("aaaa", "sd卡挂载出错");
        }
    }

    public synchronized void saveFileToSD(byte[] data, String urlpath) {
        FileOutputStream fos = null;
        if (isMounted) {
            if (!FILE_CACHE.exists()) {
                Log.i("aaaa", "保存错误");
                return;
            } else {
                //获得网络路径中图片的名字
                String fileName = urlpath.substring(urlpath.lastIndexOf("/")) + 1;
                //准备保存文件
                File saveFile = new File(FILE_CACHE, fileName);
                Log.i("aaaa", "保存成功");
                try {
                    fos = new FileOutputStream(saveFile);
                    fos.write(data, 0, data.length);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    public Bitmap getBitmapFromSD(String urlpath) {
        Bitmap bitmap = null;
        if (isMounted) {
            if (urlpath != null) {
                String fileName = urlpath.substring(urlpath.lastIndexOf("/") + 1);
                File getFile = new File(FILE_CACHE, fileName);
                Log.i("aaaa", "读取成功");
                if (getFile.exists()) {
                    bitmap = BitmapFactory.decodeFile(getFile.getAbsolutePath());

                }
                return bitmap;
            }
        }
        return null;
    }

    public void clear() {
        if (isMounted) {
            File[] files = FILE_CACHE.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

}