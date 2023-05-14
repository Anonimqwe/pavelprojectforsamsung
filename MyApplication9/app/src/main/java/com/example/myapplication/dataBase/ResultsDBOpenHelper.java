package com.example.myapplication.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Results;

import java.util.ArrayList;
import java.util.List;

public class ResultsDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATA_BASE_NAME = "Results.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String TABLE_NAME = "results";
    public ResultsDBOpenHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void insert(Results results) {
        SQLiteDatabase writableDatabase = getWritableDatabase(); //cntr+alt+v запись в переменную
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", results.getName());
        contentValues.put("time", results.getTime());
        writableDatabase.insert(TABLE_NAME, null, contentValues);
        writableDatabase.close();
    }

    public List<Results> findAll () {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        List<Results> resultsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int columnNumberId, columnNumberName, columnNumberTime;
            columnNumberName = cursor.getColumnIndex("name");
            columnNumberId = cursor.getColumnIndex("id");
            columnNumberTime = cursor.getColumnIndex("time");
            do {
                Results results = new Results(
                        cursor.getInt(columnNumberId),
                        cursor.getString(columnNumberName),
                        cursor.getString(columnNumberTime)
                );
                resultsList.add(results);
            } while (cursor.moveToNext());
        }
        cursor.close();
        readableDatabase.close();
        return resultsList;
    }
}
