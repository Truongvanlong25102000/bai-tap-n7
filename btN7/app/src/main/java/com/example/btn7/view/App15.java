package com.example.btn7.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.btn7.R;

import java.util.ArrayList;

public class App15 extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app15);
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.listView1);
        cursor = getContact();
        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            arrayList.add(name);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(App15.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
    }

    private Cursor getContact() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
        String section=ContactsContract.Contacts.IN_VISIBLE_GROUP+" = '"+("1")+"'";
        String[] sectionArgs=null;
        String sortOrder=ContactsContract.Contacts.DISPLAY_NAME+" COLLATE LOCALIZED ASC";

        return managedQuery(uri,projection,section,sectionArgs,sortOrder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_15,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.switchToApp15:
                Intent intent=new Intent(getApplicationContext(),App15B.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}