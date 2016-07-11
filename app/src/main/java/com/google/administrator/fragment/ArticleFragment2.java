package com.google.administrator.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.administrator.a3dgame.R;
import com.google.administrator.a3dgame.WebViewActivity;
import com.google.administrator.adapter.ArticleAdapter;
import com.google.administrator.dao.News;
import com.google.administrator.dao.NewsDao;
import com.google.administrator.service.DownLoadService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ArticleFragment2 extends Fragment {
    private int typeid;
    int pageIndex = 1;
    PullToRefreshListView pullToRefreshListView;
    ListView lv;
    private ArticleAdapter articleAdapter;
    List<News> data;
    NewsDao newsDao;

    public ArticleFragment2() {
    }

    @SuppressLint("ValidFragment")
    public ArticleFragment2(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articlefragment2, null);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.main_articlefragment2_pulltorefresh);
        lv = pullToRefreshListView.getRefreshableView();
        data = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsDao = new NewsDao(getContext());
                data = newsDao.findAll(typeid);


            }
        }).start();

        data = new NewsDao(getContext()).findAll(typeid);
        Intent downLoadService = new Intent(getContext(), DownLoadService.class);
        downLoadService.putExtra("typeid", typeid);
        downLoadService.putExtra("aaa","yiersan");
        downLoadService.putExtra("pageIndex", pageIndex++);
        getContext().startService(downLoadService);
        articleAdapter = new ArticleAdapter(getContext(), data);
//        articleAdapter = new ArticleAdapter(getContext(), data);
        articleAdapter.notifyDataSetChanged();
        if("typeid"==typeid+""){
            Log.i("aaaaa","typeid------"+typeid);
            lv.setAdapter(articleAdapter);
        }



        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                data = new NewsDao(getContext()).findAll(typeid);
                Intent downLoadService = new Intent(getContext(), DownLoadService.class);
                downLoadService.putExtra("typeid", typeid);
                downLoadService.putExtra("aaa","yiersan");
                downLoadService.putExtra("pageIndex", pageIndex++);
                getContext().startService(downLoadService);

                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                Log.i("aaaa", "WebViewActivity服务启动");
//                Log.i("aaaa","arcurl------"+arcurl);
            }
        });


        return view;
    }
}
