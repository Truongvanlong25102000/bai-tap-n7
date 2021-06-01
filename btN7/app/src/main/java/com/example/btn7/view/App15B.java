package com.example.btn7.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.btn7.IStudentQueryHandle;
import com.example.btn7.R;
import com.example.btn7.model.Student;
import com.example.btn7.view.Utility.FeedReaderContract;
import com.example.btn7.view.Utility.StudentReaderContract;
import com.example.btn7.view.Utility.StudentReaderDbHelper;
import com.example.btn7.view.adapter.StudentAdapter;
import com.example.btn7.view.components.DialogAddStudent;

import java.util.ArrayList;

public class App15B extends AppCompatActivity implements IStudentQueryHandle {

    private SQLiteDatabase db;
    private StudentReaderDbHelper dbHelper;
    private ContentValues values;
    private StudentAdapter studentAdapter;
    private ArrayList<Student> students = new ArrayList<>();
    private RecyclerView recyclerView;
    private Cursor cursor;
    private IStudentQueryHandle iStudentQueryHandle=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app15_b);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        values = new ContentValues();
        dbHelper = new StudentReaderDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();
        getData();
        studentAdapter = new StudentAdapter(getApplicationContext(), students);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(studentAdapter);
    }

    private void getData() {
        dbHelper = new StudentReaderDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + StudentReaderContract.StudentEntry.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            students.add(new Student(
                    cursor.getLong(cursor.getColumnIndexOrThrow(StudentReaderContract.StudentEntry.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_CLASS_TITLE)),
                    cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_NAME_TITLE))
            ));
        }
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_15b, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addStudent:
                addStudent(false,false);
                break;
            case R.id.removeList:
                removeListStudent();
                break;
            case R.id.removeById:
                //this is remove student by id
                addStudent(false,true);
                Log.d("","");
                break;

            case R.id.updateById:
                addStudent(true, false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeListStudent() {
        int deleteRow=db.delete(StudentReaderContract.StudentEntry.TABLE_NAME,null,null);
        students.clear();
        getData();
        studentAdapter.notifyDataSetChanged();
    }

    private void addStudent(boolean isUpdate,boolean isRemove) {
        DialogAddStudent dialogAddStudent = new DialogAddStudent(App15B.this, db, values,iStudentQueryHandle,isUpdate,isRemove);
        dialogAddStudent.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (studentAdapter != null) {
            students.clear();
            getData();
            studentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void restoreDataToUI() {
        if (studentAdapter != null) {
            students.clear();
            getData();
            studentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void removeStudentById(long id) {
        boolean searchId=false;
        for(int i=0;i<students.size();i++){
            if(students.get(i).getId()==id){
                searchId=true;
                String[] args={students.get(i).getNameStudent()};
                long newRowId = db.delete(StudentReaderContract.StudentEntry.TABLE_NAME, StudentReaderContract.StudentEntry.COLUMN_NAME_TITLE + " LIKE ?", args);
                break;
            }
        }

        if(searchId==false){
            Toast.makeText(App15B.this,"id not found",Toast.LENGTH_LONG).show();
        }else{
            students.clear();
            getData();
            studentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateStudentById(long id,String name,String mClass) {
        boolean searchId=false;
        for(int i=0;i<students.size();i++){
            if(students.get(i).getId()==id){
                searchId=true;
                values=new ContentValues();
                values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_TITLE, name);
                values.put(StudentReaderContract.StudentEntry.COLUMN_CLASS_TITLE, mClass);

                String[] args={students.get(i).getNameStudent()};
                int x=db.update(StudentReaderContract.StudentEntry.TABLE_NAME,values,StudentReaderContract.StudentEntry.COLUMN_NAME_TITLE + " LIKE ?",args);
                break;
            }
        }

        if(searchId==false){
            Toast.makeText(App15B.this,"id not found",Toast.LENGTH_LONG).show();
        }else{
            students.clear();
            getData();
            studentAdapter.notifyDataSetChanged();
        }
    }
}