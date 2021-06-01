package com.example.btn7.view.Utility;

import android.provider.BaseColumns;

public class StudentReaderContract {
    private StudentReaderContract(){

    }

    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_CLASS_TITLE = "class";
    }
}
