package com.google.administrator.a3dgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BBSActivity extends AppCompatActivity {

    WebView webView;
    String urlpath="http://bbs.3dmgame.com/forum.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);

        Log.i("aaaa","BBSActivity----onCreate方法执行了....");

        webView= (WebView) findViewById(R.id.bbs_activity_webview);
        webView.loadUrl(urlpath);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(urlpath);
                return  true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("aaaa","BBSActivity----title--"+title);
            }
        });

    }
}
