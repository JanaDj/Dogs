package com.example.meangirl.dogs.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DogsDatabase.db";
    private static int DB_VERSION = 1;

    public static String DATABASE_CREATION =
        "CREATE TABLE "+ Contract.BreedsSchema.TABLE_NAME + " (" +
          Contract.BreedsSchema._ID + " INTEGER PRIMARY KEY autoincrement NOT NULL, "
        + Contract.BreedsSchema.FIELD_GROUP + " TEXT, "
        + Contract.BreedsSchema.FIELD_SECTION + " TEXT, "
        + Contract.BreedsSchema.FIELD_NAME + " TEXT, "
        + Contract.BreedsSchema.FIELD_SIZE + " TEXT, "
        + Contract.BreedsSchema.FIELD_COAT + " TEXT, "
        + Contract.BreedsSchema.FIELD_ENERGY + " TEXT" +
          ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Contract.BreedsSchema.TABLE_NAME);
        onCreate(db);
    }
 }
