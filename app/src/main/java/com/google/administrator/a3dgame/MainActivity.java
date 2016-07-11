package com.google.administrator.a3dgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.administrator.adapter.ArticleAdapter;
import com.google.administrator.adapter.MainFragmentPagerAdapter;
import com.google.administrator.fragment.ArticleFragment;
import com.google.administrator.fragment.ArticleFragment2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    HorizontalScrollView horizontalScrollView_top;
    RadioGroup radioGroup_top;
    RadioButton rb01_top,rb02_top,rb03_top,rb04_top,rb05_top,rb06_top,rb07_top,rb08_top,rb09_top,rb10_top;
    ViewPager viewPager;
    RadioGroup radioGroup_bottom;
    RadioButton rb01_bottom,rb02_bottom,rb03_bottom;
    MainFragmentPagerAdapter mainFragmentPagerAdapter;
    List<Fragment> fragments;
    ArticleAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initDate();
//        articleAdapter=new ArticleAdapter(this,)
    }
    //初始化ViewPager中的数据
    private void initDate() {
        fragments=new ArrayList<>();
        //添加Fragment
        ArticleFragment f1=new ArticleFragment(1);
        ArticleFragment2 f2 = new ArticleFragment2(2);
        ArticleFragment2 f3 = new ArticleFragment2(151);
        ArticleFragment2 f4 = new ArticleFragment2(152);
        ArticleFragment2 f5 = new ArticleFragment2(153);
        ArticleFragment2 f6 = new ArticleFragment2(154);
        ArticleFragment2 f7 = new ArticleFragment2(196);
        ArticleFragment2 f8 = new ArticleFragment2(197);
        ArticleFragment2 f9 = new ArticleFragment2(199);
        ArticleFragment2 f10 = new ArticleFragment2(25);
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        fragments.add(f5);
        fragments.add(f6);
        fragments.add(f7);
        fragments.add(f8);
        fragments.add(f9);
        fragments.add(f10);
        mainFragmentPagerAdapter=new MainFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }
    //初始化所有监听
    private void initListener() {
        radioGroup_top.setOnCheckedChangeListener(this);
        radioGroup_bottom.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }
    //初始化所有控件
    private void initView() {
        horizontalScrollView_top= (HorizontalScrollView) findViewById(R.id.main_top_hsv);
        radioGroup_top= (RadioGroup) findViewById(R.id.main_top_rg);
        rb01_top= (RadioButton) findViewById(R.id.main_top_rb1);
        rb02_top = (RadioButton) findViewById(R.id.main_top_rb2);
        rb03_top = (RadioButton) findViewById(R.id.main_top_rb3);
        rb04_top = (RadioButton) findViewById(R.id.main_top_rb4);
        rb05_top = (RadioButton) findViewById(R.id.main_top_rb5);
        rb06_top = (RadioButton) findViewById(R.id.main_top_rb6);
        rb07_top = (RadioButton) findViewById(R.id.main_top_rb7);
        rb08_top = (RadioButton) findViewById(R.id.main_top_rb8);
        rb09_top = (RadioButton) findViewById(R.id.main_top_rb9);
        rb10_top = (RadioButton) findViewById(R.id.main_top_rb10);
        rb01_top.setChecked(true);
        viewPager= (ViewPager) findViewById(R.id.main_center_vp);
        radioGroup_bottom= (RadioGroup) findViewById(R.id.main_bottom_rg);
        rb01_bottom = (RadioButton) findViewById(R.id.main_bottom_rb01);
        rb02_bottom = (RadioButton) findViewById(R.id.main_bottom_rb02);
        rb03_bottom = (RadioButton) findViewById(R.id.main_bottom_rb03);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int chechedId) {
        switch (chechedId){
            case R.id.main_top_rb1:
                viewPager.setCurrentItem(0);

                break;
            case R.id.main_top_rb2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_top_rb3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_top_rb4:
                viewPager.setCurrentItem(3);

                break;
            case R.id.main_top_rb5:
                viewPager.setCurrentItem(4);
                break;
            case R.id.main_top_rb6:
                viewPager.setCurrentItem(5);
                break;
            case R.id.main_top_rb7:
                viewPager.setCurrentItem(6);
                break;
            case R.id.main_top_rb8:
                viewPager.setCurrentItem(7);
                break;
            case R.id.main_top_rb9:
                viewPager.setCurrentItem(8);
                break;
            case R.id.main_top_rb10:
                viewPager.setCurrentItem(9);
                break;
            case R.id.main_bottom_rb01:
                viewPager.setCurrentItem(0);
                rb01_bottom.setBackgroundColor(Color.GREEN);
                rb02_bottom.setBackgroundColor(Color.BLACK);
                rb03_bottom.setBackgroundColor(Color.BLACK);
                break;
            case R.id.main_bottom_rb02:
                viewPager.setCurrentItem(1);
                rb02_bottom.setBackgroundColor(Color.GREEN);
                rb01_bottom.setBackgroundColor(Color.BLACK);
                rb03_bottom.setBackgroundColor(Color.BLACK);
                Intent bbsActivity=new Intent(MainActivity.this,BBSActivity.class);
                startActivity(bbsActivity);
                break;
            case R.id.main_bottom_rb03:
                viewPager.setCurrentItem(2);
                rb03_bottom.setBackgroundColor(Color.GREEN);
                rb01_bottom.setBackgroundColor(Color.BLACK);
                rb02_bottom.setBackgroundColor(Color.BLACK);
                Intent gameActivity=new Intent(MainActivity.this,GameActivity.class);
                startActivity(gameActivity);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //顶部的滚动条出现移动效果
        horizontalScrollView_top.setVisibility(View.VISIBLE);
        radioGroup_top.setVisibility(View.VISIBLE);
        //获得当前viewPager对应的RadioButton
        RadioButton radioButton= (RadioButton) radioGroup_top.getChildAt(position);
        radioButton.setChecked(true);
        //让顶部的RadioButton随着ViewPager一起滚动
        int left=radioButton.getLeft();
        horizontalScrollView_top.smoothScrollTo(left,0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
