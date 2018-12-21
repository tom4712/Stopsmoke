package kr.hs.sdh.stopsmoke;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * Created by tom47 on 2018-01-18.
 */

public class DBhelper {

    private static final String DATABASE_NAME = "first_DB";
    private static final String DATABASE_TABLE = "first_table";
    private static final int DATABASE_VERSION = 1;
    private final Context mCtx;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private SQLiteStatement insertStmt;

    private static final String DATABASE_CREATE="CREATE TABLE " +DATABASE_TABLE+ "" +
            " (    ID    INTEGER PRIMARY    KEY AUTOINCREMENT,    SDATE TEXT,    ONOFF  TEXT, ENDDATE TEXT)";


    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
        /**
         *
         * @param db         The database.
         * @param oldVersion The old database version.
         * @param newVersion The new database version.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public void insertGarbage(){
        mDb.execSQL("INSERT INTO first_table VALUES(0,0,0,0);");
    } //첫실행 쓰레기값

    public void open() throws SQLException {

        mDbHelper = new DatabaseHelper(mCtx);

        mDb = mDbHelper.getWritableDatabase();
    }//디비오픈

    public DBhelper(Context ctx) {
        this.mCtx = ctx;
    }//컨텍스트

    public void close() {
        mDbHelper.close();
    }//디비종료

    public void updateon(int sum){
        insertStmt = mDb.compileStatement("UPDATE first_table SET ONOFF  = ?");
        insertStmt.clearBindings();
        insertStmt.bindString(1,String.valueOf(sum));
        insertStmt.execute();
    }

    public void updatesdate(String sum){
        insertStmt = mDb.compileStatement("UPDATE first_table SET SDATE  = ?");
        insertStmt.clearBindings();
        insertStmt.bindString(1,sum);
        insertStmt.execute();
    }

    public void updateedate(String sum){
        insertStmt = mDb.compileStatement("UPDATE first_table SET ENDDATE = ?");
        insertStmt.clearBindings();
        insertStmt.bindString(1,sum);
        insertStmt.execute();
        Log.d("db","값넣음"+sum);
    }

    public Cursor AllRows() {
        return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
    }//커서허용
    public boolean deleteAll() {
        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }//값초기화

}