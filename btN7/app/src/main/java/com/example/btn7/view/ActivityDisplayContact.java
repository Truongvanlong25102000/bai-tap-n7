package com.example.btn7.view;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btn7.R;
import com.example.btn7.model.Contact;
import com.example.btn7.view.Utility.FeedReaderContract;
import com.example.btn7.view.Utility.FeedReaderDbHelper;

public class ActivityDisplayContact extends AppCompatActivity {
    private EditText edtName, edtPhone, edtStreet, edtGmail, edtCity;
    private Button btnSave;
    private FeedReaderDbHelper dbHelper;
    private SQLiteDatabase db;
    private ContentValues values;
    private Contact contact;
    private long id;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);
        if(getIntent()!=null){
            if(getIntent().getSerializableExtra("contact")!=null){
                contact= (Contact) getIntent().getSerializableExtra("contact");
            }
        }
        initView();
        initEvent();
    }

    private void initEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCity.getText() != null && edtGmail.getText() != null
                        && edtName.getText() != null && edtPhone.getText() != null
                        && edtStreet.getText() != null) {
                    if(edtCity.getText().length()>0&&edtGmail.getText().length()>0&&edtName.getText().length()>0&&edtPhone.getText().length()>0&&edtStreet.getText().length()>0){
                        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, edtName.getText().toString());
                        values.put(FeedReaderContract.FeedEntry.COLUMN_ADDRESS_TITLE, edtCity.getText().toString());
                        values.put(FeedReaderContract.FeedEntry.COLUMN_EMAIL_TITLE, edtGmail.getText().toString());
                        values.put(FeedReaderContract.FeedEntry.COLUMN_PHONE_TITLE, edtPhone.getText().toString());
                        values.put(FeedReaderContract.FeedEntry.COLUMN_STREET_TITLE, edtStreet.getText().toString());

                        if(contact!=null){
                            String[] selectionArgs = { contact.getName() };
                            int x=db.update(FeedReaderContract.FeedEntry.TABLE_NAME,values, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?",selectionArgs);
                        }else{
                            long newRowId=db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
                        }
                        finish();

                    }else{
                        Toast.makeText(getApplicationContext(),"re-enter to confirm",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void initView() {
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtStreet = findViewById(R.id.edtStreet);
        edtGmail = findViewById(R.id.edtEmail);
        edtCity = findViewById(R.id.edtCity);
        btnSave = findViewById(R.id.btnSave);

        if (contact != null) {
            edtName.setText(contact.getName());
            edtPhone.setText(contact.getPhone());
            edtStreet.setText(contact.getStreet());
            edtGmail.setText(contact.getEmail());
            edtCity.setText(contact.getCity());
            id=contact.getId();
        }else{
            btnSave.setVisibility(View.VISIBLE);
        }

        dbHelper = new FeedReaderDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display_contact,menu);
        MenuItem menuItem1=menu.findItem(R.id.itemDelete);
        MenuItem menuItem2=menu.findItem(R.id.itemEdit);

        if(contact==null){
            menuItem1.setVisible(false);
            menuItem2.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemEdit:
                btnSave.setVisibility(View.VISIBLE);
               break;
            case R.id.itemDelete:
                String[] selectionArgs = { contact.getName() };
                AlertDialog.Builder builder=new AlertDialog.Builder(ActivityDisplayContact.this);
                builder.setTitle("do you want to delete");
                builder.setNegativeButton("No",(dialog, which) -> {
                    dialog.dismiss();
                });

                builder.setPositiveButton("Yes",(dialog, which) -> {
                    int deleteRow=db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?",selectionArgs);
                    dialog.dismiss();
                    finish();
                });
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
