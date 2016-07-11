package com.google.administrator.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class NewsDao {
    private NewsDaoHelper newsDaoHelper;

    public NewsDao(Context context) {
        newsDaoHelper = new NewsDaoHelper(context);
    }

    public boolean insert(News news) {
        SQLiteDatabase db = newsDaoHelper.getReadableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", news.getId());
            contentValues.put("typeid", news.getTypeid());
            contentValues.put("sortrank", news.getSortrank());
            contentValues.put("ismake", news.getIsmake());
            contentValues.put("channel", news.getChannel());
            contentValues.put("title", news.getTitle());
            contentValues.put("shorttitle", news.getShorttitle());
            contentValues.put("writer", news.getWriter());
            contentValues.put("source", news.getSource());
            contentValues.put("litpic", news.getLitpic());
            contentValues.put("pubdate", news.getPubdate());
            contentValues.put("senddate", news.getSenddate());
            contentValues.put("mid", news.getMid());
            contentValues.put("keywords", news.getKeywords());
            contentValues.put("description", news.getDescription());

            contentValues.put("dutyadmin", news.getDutyadmin());
            contentValues.put("weight", news.getWeight());
            contentValues.put("typedir", news.getTypedir());
            contentValues.put("typename", news.getTypename());
            contentValues.put("defaultname", news.getDefaultname());
            contentValues.put("namerule", news.getNamerule());
            contentValues.put("namerule2", news.getNamerule2());
            contentValues.put("sitepath", news.getSitepath());
            contentValues.put("arcurl", news.getArcurl());
            contentValues.put("typeurl", news.getTypeurl());

            Log.i("aaaa", "NewsDao--insert方法执行了");
            long id = db.insert("news", null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (db.isOpen() && db != null) {
            db.close();
        }
        return false;
    }

    public List<News> findAll() {
        SQLiteDatabase db = null;
        List<News> data = new ArrayList<>();
        Cursor cursor = null;
        try {
            db = newsDaoHelper.getReadableDatabase();
            cursor = db.query("news", new String[]{"id", "typeid", "sortrank", "ismake", "channel", "title", "shorttitle", "writer", "source",
                    "litpic", "pubdate", "senddate", "mid", "keywords", "description", "dutyadmin", "weight", "typedir",
                    "typename", "defaultname", "namerule", "namerule2", "sitepath", "arcurl", "typeurl"}, null, null, null, null, null);
            while (cursor.moveToNext()) {
                News news = new News();
                news.setId(cursor.getString(cursor.getColumnIndex("id")));
                news.setTypeid(cursor.getString(cursor.getColumnIndex("typeid")));
                news.setSortrank(cursor.getString(cursor.getColumnIndex("sortrank")));
                news.setIsmake(cursor.getString(cursor.getColumnIndex("ismake")));
                news.setChannel(cursor.getString(cursor.getColumnIndex("channel")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setShorttitle(cursor.getString(cursor.getColumnIndex("shorttitle")));
                news.setWriter(cursor.getString(cursor.getColumnIndex("writer")));
                news.setSource(cursor.getString(cursor.getColumnIndex("source")));
                news.setLitpic(cursor.getString(cursor.getColumnIndex("litpic")));
                news.setPubdate(cursor.getString(cursor.getColumnIndex("pubdate")));
                news.setSenddate(cursor.getString(cursor.getColumnIndex("senddate")));
                news.setMid(cursor.getString(cursor.getColumnIndex("mid")));
                news.setKeywords(cursor.getString(cursor.getColumnIndex("keywords")));
                news.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                news.setDutyadmin(cursor.getString(cursor.getColumnIndex("dutyadmin")));
                news.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
                news.setTypedir(cursor.getString(cursor.getColumnIndex("typedir")));
                news.setTypename(cursor.getString(cursor.getColumnIndex("typename")));
                news.setDefaultname(cursor.getString(cursor.getColumnIndex("defaultname")));
                news.setNamerule(cursor.getString(cursor.getColumnIndex("namerule")));
                news.setNamerule2(cursor.getString(cursor.getColumnIndex("namerule2")));
                news.setSitepath(cursor.getString(cursor.getColumnIndex("sitepath")));
                news.setArcurl(cursor.getString(cursor.getColumnIndex("arcurl")));
                news.setTypeurl(cursor.getString(cursor.getColumnIndex("typeurl")));

                data.add(news);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(cursor!=null&&!cursor.isClosed()){
                cursor.close();
            }
            if(db!=null&&db.isOpen()){
                db.close();
            }
        }
        return null;
    }
    public List<News> findAll(int typeid) {
        SQLiteDatabase db = null;
        List<News> data = new ArrayList<>();
        Cursor cursor = null;
        try {
            db = newsDaoHelper.getReadableDatabase();
            cursor = db.query("news", new String[]{"id", "typeid", "sortrank", "ismake", "channel", "title", "shorttitle", "writer", "source",
                    "litpic", "pubdate", "senddate", "mid", "keywords", "description", "dutyadmin", "weight", "typedir",
                    "typename", "defaultname", "namerule", "namerule2", "sitepath", "arcurl", "typeurl"}, "typeid = ?", new String[]{typeid+""}, null, null, null);
            while (cursor.moveToNext()) {
                News news = new News();
                news.setId(cursor.getString(cursor.getColumnIndex("id")));
                news.setTypeid(cursor.getString(cursor.getColumnIndex("typeid")));
                news.setSortrank(cursor.getString(cursor.getColumnIndex("sortrank")));
                news.setIsmake(cursor.getString(cursor.getColumnIndex("ismake")));
                news.setChannel(cursor.getString(cursor.getColumnIndex("channel")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setShorttitle(cursor.getString(cursor.getColumnIndex("shorttitle")));
                news.setWriter(cursor.getString(cursor.getColumnIndex("writer")));
                news.setSource(cursor.getString(cursor.getColumnIndex("source")));
                news.setLitpic(cursor.getString(cursor.getColumnIndex("litpic")));
                news.setPubdate(cursor.getString(cursor.getColumnIndex("pubdate")));
                news.setSenddate(cursor.getString(cursor.getColumnIndex("senddate")));
                news.setMid(cursor.getString(cursor.getColumnIndex("mid")));
                news.setKeywords(cursor.getString(cursor.getColumnIndex("keywords")));
                news.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                news.setDutyadmin(cursor.getString(cursor.getColumnIndex("dutyadmin")));
                news.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
                news.setTypedir(cursor.getString(cursor.getColumnIndex("typedir")));
                news.setTypename(cursor.getString(cursor.getColumnIndex("typename")));
                news.setDefaultname(cursor.getString(cursor.getColumnIndex("defaultname")));
                news.setNamerule(cursor.getString(cursor.getColumnIndex("namerule")));
                news.setNamerule2(cursor.getString(cursor.getColumnIndex("namerule2")));
                news.setSitepath(cursor.getString(cursor.getColumnIndex("sitepath")));
                news.setArcurl(cursor.getString(cursor.getColumnIndex("arcurl")));
                news.setTypeurl(cursor.getString(cursor.getColumnIndex("typeurl")));

                data.add(news);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(cursor!=null&&!cursor.isClosed()){
                cursor.close();
            }
            if(db!=null&&db.isOpen()){
                db.close();
            }
        }
        return null;
    }
}
