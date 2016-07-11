package com.google.administrator.a3dgame;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.administrator.dao.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class WebViewActivity extends AppCompatActivity{
    WebView webView;
    private List<News> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articleitem);

        Log.i("aaaa","WebViewActivity服务连接成功");

        Intent intent=getIntent();
        final String urlpath=intent.getStringExtra("arcurl");
        String title=intent.getStringExtra("title");

        data= new ArrayList<>();
       webView= (WebView) this.findViewById(R.id.articlefragment_webview);
        webView.loadUrl(urlpath);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(urlpath);
                return true;
            }
        });
        //掌控网页的加载进度
        webView.setWebChromeClient(new WebChromeClient(){
            //可以获得网页进度信息

//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                Log.i("aaaa","newProgress="+newProgress);
//                setTitle("Loading....");
//            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("aaaa","title----"+title);
            }
        });
    }
}
