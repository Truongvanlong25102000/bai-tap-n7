package com.example.btn7.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;

import com.example.btn7.R;
import com.example.btn7.model.Contact;
import com.example.btn7.view.Utility.FeedReaderContract;
import com.example.btn7.view.Utility.FeedReaderDbHelper;
import com.example.btn7.view.adapter.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

public class App14 extends AppCompatActivity implements ItemSelected{

    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Contact> contacts;
    private SQLiteDatabase db;
    private FeedReaderDbHelper dbHelper;
    private Cursor cursor;
    private String name,phone,street,city,email;
    private long id;
    private ItemSelected itemSelected=this;

    String[] projection={
            BaseColumns._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_PHONE_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_ADDRESS_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_EMAIL_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_STREET_TITLE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app14);
        initView();
        getData();
        initEvent();
    }

    private void initEvent() {
    }

    private void getData() {
        dbHelper = new FeedReaderDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();
        cursor=db.rawQuery("SELECT * FROM "+ FeedReaderContract.FeedEntry.TABLE_NAME,null);
        while (cursor.moveToNext()){
            id=cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_ID));
            name=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            phone=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_PHONE_TITLE));
            city=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_ADDRESS_TITLE));
            street=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_STREET_TITLE));
            email=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_EMAIL_TITLE));
            contacts.add(new Contact(id,name,phone,street,email,city));
        }
        cursor.close();
    }

    private void initView() {
        contacts=new ArrayList<>();
        getData();
        recyclerView = findViewById(R.id.recycleViewContact);
        contactAdapter = new ContactAdapter(getApplicationContext(), contacts,itemSelected);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(contactAdapter!=null){
            contacts.clear();
            dbHelper = new FeedReaderDbHelper(getApplicationContext());
            db = dbHelper.getReadableDatabase();
            cursor=db.rawQuery("SELECT * FROM "+ FeedReaderContract.FeedEntry.TABLE_NAME,null);
            while (cursor.moveToNext()){
                id=cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_ID));
                name=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
                phone=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_PHONE_TITLE));
                city=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_ADDRESS_TITLE));
                street=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_STREET_TITLE));
                email=cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_EMAIL_TITLE));
                contacts.add(new Contact(id,name,phone,street,email,city));
            }
            cursor.close();
            contactAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_14, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addContact:
                Intent intent = new Intent(this, ActivityDisplayContact.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemSelectedInRecyclerView(int position) {
        Intent intent = new Intent(this, ActivityDisplayContact.class);
        intent.putExtra("contact",contacts.get(position));
        startActivity(intent);
    }
}