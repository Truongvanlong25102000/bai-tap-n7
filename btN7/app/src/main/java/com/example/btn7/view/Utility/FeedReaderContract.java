package com.example.btn7.view.Utility;

import android.provider.BaseColumns;

public class FeedReaderContract {

    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_PHONE_TITLE = "phone";
        public static final String COLUMN_STREET_TITLE = "street";
        public static final String COLUMN_EMAIL_TITLE = "email";
        public static final String COLUMN_ADDRESS_TITLE = "address";
    }
}
