package com.google.administrator.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.administrator.a3dgame.R;
import com.google.administrator.dao.News;
import com.google.anministrator.cache.ManagerCache;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class GrideViewAdapter extends BaseAdapter{



    private List<News> data;
    GridView gridView;
    Context context;
    ManagerCache managerCache;
    public GrideViewAdapter(List<News> data, Context context) {
        this.data = data;
        this.context = context;
        managerCache=new ManagerCache();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.activity_game_item,null);
            holder=new ViewHolder();
            holder.iv= (ImageView) convertView.findViewById(R.id.activity_game_item_iv);
            holder.tv= (TextView) convertView.findViewById(R.id.activity_game_item_tv);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(data.get(i).getTitle());
        String imgpath ="http://www.3dmgame.com"+data.get(i).getLitpic();

        if(!data.get(i).getLitpic().isEmpty()){
            managerCache.getCache(imgpath,holder.iv);

        }else{
            holder.iv.setImageResource(R.mipmap.ic_launcher);
        }




        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
