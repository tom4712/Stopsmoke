package kr.hs.sdh.stopsmoke;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;


/**

 * Created by ntsysMac01 on 2015-12-21.

 */


//이 클래스는 helper로 db를 커넥션 연동 시키기 위해서 꼭 필요한 클래스 이다


public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }


    @Override

    public void onCreate(SQLiteDatabase db) {


    }


    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
