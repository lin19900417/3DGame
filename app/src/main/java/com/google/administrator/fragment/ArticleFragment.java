package com.google.administrator.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.administrator.a3dgame.R;
import com.google.administrator.a3dgame.WebViewActivity;
import com.google.administrator.adapter.ArticleAdapter;
import com.google.administrator.adapter.MainArticleFragmentViewPagerAdapter;
import com.google.administrator.custom.MainArticleFragmentViewPager;
import com.google.administrator.dao.News;
import com.google.administrator.dao.NewsDao;
import com.google.administrator.dao.NewsDaoHelper;
import com.google.administrator.service.DownLoadService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ArticleFragment extends Fragment {
    //定义文章类型
    private int typeid;

    private List<News> data;
    MainArticleFragmentViewPagerAdapter mainArticleFragmentViewPagerAdapter;

    private NewsDaoHelper helper;
    private NewsDao newsDao;
    private SQLiteDatabase db;
    private ListView lv;
    public static PullToRefreshListView pullToRefreshListView;

    public static ArticleAdapter articleAdapter;

    private WebView webView;

    public ArticleFragment() {

    }

    //Annotation 注解
    @SuppressLint("ValidFragment")
    public ArticleFragment(int typeid) {
        this.typeid = typeid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        //获得Fragment中整体布局
        View view = inflater.inflate(R.layout.activity_main_articlefragment, null);
        //获得Fragment中的ViewPager
        MainArticleFragmentViewPager mainArticleFragmentViewPager =
                (MainArticleFragmentViewPager) view.findViewById(R.id.main_articlefragment_viewpager);
        int[] imageRsId = {R.drawable.default1, R.drawable.default2, R.drawable.default3};
        //初始化ViewPager的數據
        List<ImageView> imageViews = new ArrayList<>();


        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            //設置圖片的縮放類型  鋪滿全屏
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViews.add(imageView);
        }
        mainArticleFragmentViewPagerAdapter = new
                MainArticleFragmentViewPagerAdapter(imageViews);
        mainArticleFragmentViewPager.setAdapter(mainArticleFragmentViewPagerAdapter);

        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.main_articlefragment_pulltorefresh);

        lv = pullToRefreshListView.getRefreshableView();
        helper = new NewsDaoHelper(getContext());
        data = new ArrayList<>();
        articleAdapter = new ArticleAdapter(getContext(), data);
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = helper.getReadableDatabase();
                newsDao = new NewsDao(getContext());
                data = newsDao.findAll(typeid);
            }
        }).start();

        data = new NewsDao(getContext()).findAll(typeid);
        Intent downLoadService = new Intent(getContext(), DownLoadService.class);
        downLoadService.putExtra("typeid",1);
        downLoadService.putExtra("pageIndex",1);
        getContext().startService(downLoadService);
        final ArticleAdapter articleAdapter = new ArticleAdapter(getContext(), data);
        lv.setAdapter(articleAdapter);


        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

//                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Intent downLoadService = new Intent(getContext(), DownLoadService.class);
                downLoadService.putExtra("typeid",1);
                downLoadService.putExtra("pageIndex",1);
                getContext().startService(downLoadService);
                articleAdapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                String arcurl = data.get(i-1).getArcurl();
                String title = data.get(i).getTitle();
                intent.putExtra("arcurl", arcurl);
                intent.putExtra("title", title);
                getContext().startActivity(intent);
                Log.i("aaaa","WebViewActivity服务启动");
                Log.i("aaaa","arcurl------"+arcurl);
            }
        });


        return view;
    }


}
