package com.google.administrator.a3dgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.administrator.adapter.GrideViewAdapter;
import com.google.administrator.dao.News;
import com.google.administrator.service.GameService;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private static final String[] GAME_NAME=new String[]{
            "游戏","动作","射击","角色扮演","养成","益智","即时策略","策略","体育","模拟经营","赛车","冒险"
    };
    private static final int[] GAME_TYPE_ID = new int[]{
            179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192
    };

    private List<News> data;
    TextView tv;
    Spinner spinner;
    GridView gridView;
    GrideViewAdapter grideViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        data=new ArrayList<>();
        tv= (TextView) findViewById(R.id.activity_game_tv1);
        spinner= (Spinner) findViewById(R.id.activity_game_sp);
        gridView= (GridView) findViewById(R.id.activity_game_gridview);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,GAME_NAME);
        grideViewAdapter=new GrideViewAdapter(data,this);
        spinner.setAdapter(arrayAdapter);
        gridView.setAdapter(grideViewAdapter);


        Intent gameService=new Intent(this, GameService.class);
//        gameService.putExtra("urlpath",urlpath);
        startService(gameService);

    }
}
