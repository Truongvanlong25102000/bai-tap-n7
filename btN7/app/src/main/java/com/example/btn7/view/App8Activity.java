package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.btn7.R;

public class App8Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnFormControl;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app8);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnFormControl.setOnClickListener(this);
    }

    private void initView() {
        btnFormControl = findViewById(R.id.btnFormControl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFormControl:
                intent = new Intent(this, App8_Form_Control.class);
        }

        startActivity(intent);
    }
}