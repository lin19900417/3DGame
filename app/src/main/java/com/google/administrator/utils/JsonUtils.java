package com.google.administrator.utils;

import android.util.Log;

import com.google.administrator.dao.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class JsonUtils {
    public static List<News> getList(String json) {
        Log.i("aaaa", "Json类");
        List<News> list = null;
        try {
            JSONObject root = new JSONObject(json);
            list = new ArrayList<>();
            JSONObject data = root.getJSONObject("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject zero = data.getJSONObject(i + "");
                String id = zero.getString("id");
                String typeid = zero.getString("typeid");
                String sortrank = zero.getString("sortrank");
                String ismake = zero.getString("ismake");
                String channel = zero.getString("channel");
                String title = zero.getString("title");
                String shorttitle = zero.getString("shorttitle");
                String writer = zero.getString("writer");
                String source = zero.getString("source");
                String litpic =  zero.getString("litpic");
                String pubdate = zero.getString("pubdate");
                String senddate = zero.getString("senddate");
                String mid = zero.getString("mid");
                String keywords = zero.getString("keywords");

                String description = zero.getString("description");
                String dutyadmin = zero.getString("dutyadmin");
                String weight = zero.getString("weight");
                String typedir = zero.getString("typedir");
                String typename = zero.getString("typename");
                String defaultname = zero.getString("defaultname");
                String namerule = zero.getString("namerule");
                String namerule2 = zero.getString("namerule2");
                String sitepath = zero.getString("sitepath");
                String arcurl = zero.getString("arcurl");
                String typeurl = zero.getString("typeurl");

                News news = new News(id, typeid, sortrank, ismake, channel, title, shorttitle, writer, source, litpic, pubdate, senddate, mid, keywords, description, dutyadmin, weight,
                        typedir, typename, defaultname, namerule, namerule2,sitepath, arcurl, typeurl);
                list.add(news);
                Log.i("aaaa","解析完成");
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
