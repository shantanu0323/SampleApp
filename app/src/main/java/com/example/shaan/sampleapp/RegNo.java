package com.example.shaan.sampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHAAN on 20-09-16.
 */
public class RegNo {

    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_REG = "reg_no";

    private static final String DATABASE_NAME = "STUDENT";
    private static final String DATABASE_TABLE = "student";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public void createEntry(String name, String reg) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_REG,reg);
        ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_REG};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iRegNo = c.getColumnIndex(KEY_REG);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iRegNo) + "\n";
        }

        return result;
    }

    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_NAME + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " VARCHAR2(20) NOT NULL, " +
                    KEY_REG + " VARCHAR2(20) UNIQUE);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE + ";" );
            onCreate(db);
        }
    }

    public RegNo(Context c) {
        this.ourContext = c;
    }

    public RegNo open()throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

}
