package com.google.administrator.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/6.
 */
public class NewsDaoHelper extends SQLiteOpenHelper{

    public NewsDaoHelper(Context context) {
        super(context, "news.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists news(id primary key," +
                "typeid, sortrank, ismake, channel, title, shorttitle, writer, source, litpic, pubdate, senddate, mid, keywords, description, dutyadmin, weight," +
                "typedir, typename, defaultname, namerule, namerule2," +
                "sitepath, arcurl, typeurl)");
        Log.i("aaaa","NewsDaoHelper----onCreate执行了");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
