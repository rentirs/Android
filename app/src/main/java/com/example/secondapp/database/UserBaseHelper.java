package com.example.secondapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + UserDbSchema.UserTable.NAME + " (" +
                    UserDbSchema.Cols.UUID + ", " +
                    UserDbSchema.Cols.NAME + ", " +
                    UserDbSchema.Cols.LASTNAME + ", " +
                    UserDbSchema.Cols.PHONE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserDbSchema.UserTable.NAME);
        onCreate(sqLiteDatabase);
    }
}
