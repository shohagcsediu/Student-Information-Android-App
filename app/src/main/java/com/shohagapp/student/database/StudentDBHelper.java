package com.shohagapp.student.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.shohagapp.student.database.StudentContract.*;

public class StudentDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "studentinfo.db";
    public static final int DATABASE_VERSION = 1;

    public StudentDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_MYTABLE = "CREATE TABLE " +
                StudentEntry.TABLE_NAME + " (" +
                StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentEntry.COL_NAME + " TEXT NOT NULL, " +
                StudentEntry.COL_ROLL + " TEXT NOT NULL, " +
                StudentEntry.COL_DEPT + " TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(CREATE_MYTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
