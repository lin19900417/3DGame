package com.google.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.administrator.a3dgame.R;
import com.google.administrator.dao.News;
import com.google.anministrator.cache.ManagerCache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by Administrator on 2016/7/7.
 */
public  class ArticleAdapter extends BaseAdapter{

    private List<News> data;
    private Context context;
    private ManagerCache managerCache;

    public ArticleAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.main_article_listview, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.main_article_listview_iv);
            holder.tv_title = (TextView) convertView.findViewById(R.id.main_article_listview_tv1);
            holder.tv_content = (TextView) convertView.findViewById(R.id.main_article_listview_tv2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.tv_title.setText(get("title").toString());
//        holder.tv_content.setText(map.get("description").toString());
        holder.tv_title.setText(data.get(position).getTitle());

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=sf.format(new Date(Long.parseLong(data.get(position).getPubdate().toString())));
        holder.tv_content.setText(date);



        String imgpath ="http://www.3dmgame.com"+data.get(position).getLitpic();

                Log.i("aaaa","MyAdapter---imgpath--"+imgpath);
        if(!data.get(position).getLitpic().isEmpty()){
            managerCache.getCache(imgpath,holder.iv);

        }else{
            holder.iv.setImageResource(R.mipmap.ic_launcher);
        }

////        Bitmap bitmap= BitmapFactory.decodeFile(imgpath);
//        holder.iv.setImageBitmap();



        return convertView;
    }
    class ViewHolder {
        ImageView iv;
        TextView tv_title;
        TextView tv_content;
    }
}
