package com.google.administrator.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.administrator.a3dgame.R;
import com.google.administrator.adapter.ArticleAdapter;
import com.google.administrator.dao.News;
import com.google.administrator.dao.NewsDao;
import com.google.administrator.fragment.ArticleFragment;
import com.google.administrator.fragment.ArticleFragment2;
import com.google.administrator.utils.HttpUtils;
import com.google.administrator.utils.JsonUtils;
import com.google.anministrator.cache.FileCache;
import com.google.anministrator.cache.ManagerCache;
import com.google.anministrator.cache.WebCache;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class DownLoadService extends Service {


    int typeid;
    public int pageIndex = 1;
    private List<News> list;
    private Handler handler;
    //    private NotificationManager notificationManager;
//    private NotificationCompat.Builder builder;
    private FileCache fileCache;
    private NewsDao newsDao;
    private News news;
    ManagerCache managerCache;

    private String urlpath;
    private String urlpath1;
    private String getUrlpath;
    ArticleFragment articleFragment = new ArticleFragment(1);
    ArticleFragment2 articleFragment2 = new ArticleFragment2(2);


//    public DownLoadService(int typeid) {
//        this.typeid = typeid;
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaaa", "成功连接到service");
        newsDao = new NewsDao(getApplicationContext());
        handler = new Handler();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用线程下载数据
        urlpath = "http://www.3dmgame.com/sitemap/api.php?row=20&typeid=";
        urlpath1 = "&paging=1&page=";
        typeid = intent.getIntExtra("typeid", 1);
        pageIndex = intent.getIntExtra("pageIndex", 1);
        final String str=intent.getStringExtra("aaa");
        getUrlpath = urlpath + typeid + urlpath1 + pageIndex;
        managerCache = new ManagerCache();
        fileCache = new FileCache();
        if (getUrlpath != null) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte[] b = HttpUtils.download(getUrlpath);

//                    handler.sendEmptyMessage(1);
                    if (b != null) {
                        try {
                            String json = new String(b, "utf-8");
                            list = JsonUtils.getList(json);

                            for (News news : list) {

                                String[] s = news.getLitpic().toString().split("/");
                                String imagename = s[s.length - 1];
                                String pic = news.getLitpic();
                                byte[] bs = WebCache.download(pic);
                                fileCache.saveFileToSD(bs, pic);
                                NewsDao newsDao = new NewsDao(getApplicationContext());
                                newsDao.insert(news);

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        if("yiersan".equals(str)){
//                                            if(typeid==2){
//
////                                                ArticleFragment.articleAdapter.notifyDataSetChanged();
////                                                ArticleFragment.pullToRefreshListView.onRefreshComplete();
////                                            }else{
//                                                ArticleFragment2.
//                                            }

                                        }

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
        }
        return START_REDELIVER_INTENT;
    }
}
