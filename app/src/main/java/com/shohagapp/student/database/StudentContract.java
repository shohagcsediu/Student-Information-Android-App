package com.shohagapp.student.database;

import android.provider.BaseColumns;

public class StudentContract {

    public StudentContract() {}

    public static final class StudentEntry implements BaseColumns{
        public static final String TABLE_NAME = "studentlist";
        public static final String COL_NAME = "name";
        public static final String COL_ROLL = "roll";
        public static final String COL_DEPT = "dept";
    }
}
