package com.example.btn7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.btn7.view.App10;
import com.example.btn7.view.App11;
import com.example.btn7.view.App13;
import com.example.btn7.view.App14;
import com.example.btn7.view.App15;
import com.example.btn7.view.App8Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnApp7, btnApp8, btnApp9, btnApp10, btnApp11, btnApp13,btnApp14,btnApp15;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnApp7.setOnClickListener(this);
        btnApp8.setOnClickListener(this);
        btnApp9.setOnClickListener(this);
        btnApp10.setOnClickListener(this);
        btnApp11.setOnClickListener(this);
        btnApp13.setOnClickListener(this);
        btnApp14.setOnClickListener(this);
        btnApp15.setOnClickListener(this);
    }

    private void initView() {
        btnApp7 = findViewById(R.id.btnApp7);
        btnApp8 = findViewById(R.id.btnApp8);
        btnApp9 = findViewById(R.id.btnApp9);
        btnApp10 = findViewById(R.id.btnApp10);
        btnApp11 = findViewById(R.id.btnApp11);
        btnApp13 = findViewById(R.id.btnApp13);
        btnApp14 = findViewById(R.id.btnApp14);
        btnApp15 = findViewById(R.id.btnApp15);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnApp7:
                break;
            case R.id.btnApp8:
                intent = new Intent(this, App8Activity.class);
                break;
            case R.id.btnApp9:
                intent = new Intent(this, App8Activity.class);
                break;
            case R.id.btnApp10:
                intent = new Intent(this, App10.class);
                break;
            case R.id.btnApp11:
                intent = new Intent(this, App11.class);
                break;
            case R.id.btnApp13:
                intent = new Intent(this, App13.class);
                break;
            case R.id.btnApp14:
                intent = new Intent(this, App14.class);
                break;
            case R.id.btnApp15:
                intent = new Intent(this, App15.class);
                break;
        }

        startActivity(intent);
    }
}