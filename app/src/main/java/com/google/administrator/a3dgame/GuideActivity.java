package com.google.administrator.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.administrator.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    ViewPager guideViewPager;
    GuideViewPagerAdapter guideViewPagerAdapter;
    List<View> views;
    LayoutInflater layoutInflater;
    ImageView [] dots;
    int currentIndex;//当前页面的索引
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDot();
    }

    private void initDot(){
        LinearLayout ll= (LinearLayout) findViewById(R.id.guide_dot_ll);
        dots=new ImageView[views.size()];
        //得到线性布局下面的所有的点对象
        for(int i=0;i<views.size();i++){
            dots[i]= (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);
        }
        //初始化当前所在page的索引值
        currentIndex=0;
        //设置当前的page是白色
        dots[currentIndex].setEnabled(false);

    }
    //初始化View
    private void initView(){
        guideViewPager=(ViewPager)findViewById(R.id.guide_viewpager);
        views=new ArrayList<>();
        layoutInflater=LayoutInflater.from(this);
        View view1=layoutInflater.inflate(R.layout.activity_guide_page01,null);
        View view2=layoutInflater.inflate(R.layout.activity_guide_page02,null);
        View view3=layoutInflater.inflate(R.layout.activity_guide_page03,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        guideViewPagerAdapter = new GuideViewPagerAdapter(views);
        guideViewPager.setAdapter(guideViewPagerAdapter);
        guideViewPager.addOnPageChangeListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置底部显示点的颜色
        if(position<0||position+1>views.size()){
            return;
        }
        //设置当前位置为选中状态
        dots[position].setEnabled(false);
        //设置之前的位置为非选中状态
        dots[currentIndex].setEnabled(true);
        currentIndex=position;
        //添加最后一个引导界面的Button监听
        if(position==views.size()-1){
            Button btn=(Button)views.get(position).findViewById(R.id.guide_page3_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //保存一个登陆过的记录
                    setGuide();
                    //跳转
                    Intent mainIntent=new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            });
        }
    }
    //保存登陆过的信息
    private void setGuide(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("isLogin",true);
        editor.commit();
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
