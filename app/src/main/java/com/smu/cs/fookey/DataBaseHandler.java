package com.smu.cs.fookey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG on 2017-08-18.
 */

public class DataBaseHandler extends SQLiteOpenHelper implements dbInterface{

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DB";
    private static final String TABLE_NAME = "foodDataTable";
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_DATE = "date";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context) {
        return new DataBaseHandler(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //DB를 새로 만든다.
        String CREATE_TABLE1 = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_DATE + " LONG " + ")";
        db.execSQL(CREATE_TABLE1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DB를 지우고 다시 쓴다.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    @Override
    public void insertData(FoodData data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CATEGORY, data.getCategory());
        contentValues.put(KEY_DATE, data.getDate());

        long returnResult = db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public List<FoodData> getDataList() {

        List<FoodData> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        FoodData data;
        if (cursor.moveToFirst()) {
            do {
                data = new FoodData();
                data.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                data.setCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
                data.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                dataList.add(data);
            } while (cursor.moveToNext());
        }
        return dataList;
    }
}
