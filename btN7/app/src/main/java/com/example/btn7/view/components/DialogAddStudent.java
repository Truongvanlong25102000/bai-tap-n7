package com.example.btn7.view.components;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.btn7.IStudentQueryHandle;
import com.example.btn7.R;
import com.example.btn7.view.Utility.FeedReaderContract;
import com.example.btn7.view.Utility.StudentReaderContract;

public class DialogAddStudent extends Dialog {

    private EditText edtName, edtClass, edtId;
    private TextView txt0,txt1,txt2;
    private Button btnCancel, btnComfirm;
    private Context context;
    private SQLiteDatabase db;
    private ContentValues values;
    private IStudentQueryHandle iStudentQueryHandle;
    private boolean isRemoveById = false;
    private boolean isUpdateById = false;

    public DialogAddStudent(@NonNull Context context, SQLiteDatabase db, ContentValues values, IStudentQueryHandle iStudentQueryHandle, boolean isUpdate, boolean isRemove) {
        super(context);
        setContentView(R.layout.dialog_add_student);
        this.db = db;
        this.context = context;
        this.values = values;
        this.iStudentQueryHandle = iStudentQueryHandle;
        this.isUpdateById = isUpdate;
        this.isRemoveById = isRemove;
        initView();
        initEvent();
    }

    private void initEvent() {
        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText() != null && edtClass.getText() != null) {
                    if (edtClass.getText().length() > 0 && edtName.getText().length() > 0&&!isRemoveById&&!isUpdateById) {
                        values.put(StudentReaderContract.StudentEntry.COLUMN_NAME_TITLE, edtName.getText().toString());
                        values.put(StudentReaderContract.StudentEntry.COLUMN_CLASS_TITLE, edtClass.getText().toString());
                        long newRowId = db.insert(StudentReaderContract.StudentEntry.TABLE_NAME, null, values);

                        iStudentQueryHandle.restoreDataToUI();

                    }else if(isRemoveById&&!isUpdateById&&edtId.getText().length()>0){
                        //remove student
                        iStudentQueryHandle.removeStudentById(Long.parseLong(edtId.getText().toString()));
                    }else if(!isRemoveById&&isUpdateById){
                        //update Student
                        iStudentQueryHandle.updateStudentById(Long.parseLong(edtId.getText().toString()),edtName.getText().toString(),edtClass.getText().toString());
                    }
                    else {
                        Toast.makeText(context, "input again", Toast.LENGTH_LONG).show();
                    }
                }
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initView() {
        edtClass = findViewById(R.id.edtClass);
        edtName = findViewById(R.id.edtName);
        edtId = findViewById(R.id.edtId);
        btnCancel = findViewById(R.id.btnCancel);
        btnComfirm = findViewById(R.id.btnComfirm);
        txt0=findViewById(R.id.txt0);
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        if (isRemoveById == false && isUpdateById == false) {
            edtId.setVisibility(View.GONE);
            txt0.setVisibility(View.GONE);
        }else{
            if(isRemoveById){
                edtId.setVisibility(View.VISIBLE);
                edtClass.setVisibility(View.GONE);
                edtName.setVisibility(View.GONE);
                txt1.setVisibility(View.GONE);
                txt2.setVisibility(View.GONE);
            }
        }
    }
}
