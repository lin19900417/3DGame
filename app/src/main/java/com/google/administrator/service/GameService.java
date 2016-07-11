package com.google.administrator.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.google.administrator.dao.News;
import com.google.administrator.dao.NewsDao;
import com.google.administrator.dao.NewsDaoHelper;
import com.google.administrator.utils.HttpUtils;
import com.google.administrator.utils.JsonUtils;
import com.google.anministrator.cache.FileCache;
import com.google.anministrator.cache.ManagerCache;
import com.google.anministrator.cache.WebCache;

import java.util.ArrayList;
import java.util.List;

public class GameService extends Service {
    NewsDao newsDao;
    NewsDaoHelper newsDaoHelper;
    FileCache fileCache;
    ManagerCache managerCache;
    Handler handler;
    List<News> data;
    int pageIndex=1;
    String urlpath;
    
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaaa","GameService连接成功");
        newsDao=new NewsDao(getApplicationContext());
        newsDaoHelper=new NewsDaoHelper(getApplicationContext());
        fileCache=new FileCache();
        managerCache=new ManagerCache();
        handler=new Handler();
        data=new ArrayList<>();
        urlpath="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=179&paging=1&page="+pageIndex;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] b= HttpUtils.download(urlpath);

                if(b!=null){
                    try {
                        String json=new String(b,"utf-8");
                        data= JsonUtils.getList(json);

                        for (News news:data){

                            String[] s=news.getLitpic().toString().split("/");
                            String imagename=s[s.length-1];
                            String pic=news.getLitpic();
                            byte[] bs= WebCache.download(pic);
                            fileCache.saveFileToSD(bs,pic);
                            newsDao=new NewsDao(getApplicationContext());
                            newsDao.insert(news);
                            newsDao.findAll(179);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
//                                        ArticleFragment.inst)
                                }
                            });

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        pageIndex++;

        return START_REDELIVER_INTENT;
    }
}
