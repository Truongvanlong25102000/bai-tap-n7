package com.example.btn7.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.btn7.R;
import com.example.btn7.view.components.BasicDialogFragment;
import com.example.btn7.view.components.CustomAlertDialogFragment;
import com.example.btn7.view.components.ImageDialogFragment;

import java.util.Calendar;

public class App11 extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app11);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBasicDialog();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleAlertDialog();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        CustomAlertDialogFragment customAlertDialogFragment=new CustomAlertDialogFragment();
        customAlertDialogFragment.show(getSupportFragmentManager(),"Cocacola");
    }

    private void showListDialog() {
        String[] items = {"Cocacola", "Pesi", "Redbull", "Sting","Soda", "Fanta","Cafe",};
        AlertDialog.Builder builder = new AlertDialog.Builder(App11.this);
        builder.setTitle("Your choices are:");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(App11.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void showSimpleAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(App11.this);
        builder.setTitle("Do you like dialog?");
        builder.setNegativeButton("No", (dialog, which) -> {
            Toast.makeText(App11.this, "No", Toast.LENGTH_SHORT).show();
        });
        builder.setNeutralButton("Cancel", (dialog, which) -> {
            Toast.makeText(App11.this, "Cancel", Toast.LENGTH_SHORT).show();
        });
        builder.setPositiveButton("Yes", (dialog, which) -> {
            Toast.makeText(App11.this, "Yes", Toast.LENGTH_SHORT).show();
        });
        builder.create().show();
    }

    private void showTimePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        Toast.makeText(getApplicationContext(), String.format("%d : %d", hourOfDay, minute), Toast.LENGTH_SHORT).show();
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Toast.makeText(getApplicationContext(), String.format("%d - %d - %d", dayOfMonth, monthOfYear, year), Toast.LENGTH_SHORT).show();

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showImageDialog() {
        ImageDialogFragment imageDialogFragment = new ImageDialogFragment();
        imageDialogFragment.show(getSupportFragmentManager(), "Image dialog fragment");
    }

    private void showBasicDialog() {
        BasicDialogFragment basicDialogFragment = new BasicDialogFragment();
        basicDialogFragment.show(getSupportFragmentManager(), "Basic dialog fragment");
    }

    private void addControl() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
    }
}