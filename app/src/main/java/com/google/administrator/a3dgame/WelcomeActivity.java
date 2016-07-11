package com.google.administrator.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.administrator.service.DownLoadService;
import com.google.administrator.utils.NetUtils;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    int pageIndex=1;
    GifImageView gifImageView;
    Animation animation;
    //判断网络是否打开
    boolean netOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gifImageView = (GifImageView)findViewById(R.id.welcome_gif);
        //添加一个动画
        animation=new AlphaAnimation(0,1.0f);
        animation.setDuration(3000);
        gifImageView.startAnimation(animation);
        //给动画添加一个监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //判断网络
                netOpen =  NetUtils.netConnect(WelcomeActivity.this);
                if(netOpen){
                    //开始Service，下载数据
                    Intent downloadServiceIntent=new Intent(WelcomeActivity.this, DownLoadService.class);
                    downloadServiceIntent.putExtra("typeid",1);
                    downloadServiceIntent.putExtra("pageIndex",pageIndex++);
                    startService(downloadServiceIntent);

                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!netOpen){
                    Toast.makeText(WelcomeActivity.this, "请链接你的网络", Toast.LENGTH_SHORT).show();
                }
                isFirstLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    //判断是否是第一次登陆
    private void isFirstLogin(){
        SharedPreferences sharePreference=getSharedPreferences("isFirstLogin", Context.MODE_PRIVATE);
        boolean isLogin=sharePreference.getBoolean("isLogin",false);
        //如果是第一次登陆，就跳转到引导界面，否则的话，跳转到主界面
        if(!isLogin){
            Intent guideIntent=new Intent(WelcomeActivity.this,GuideActivity.class);
            startActivity(guideIntent);
            finish();
        }else{
            Intent mainIntent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
