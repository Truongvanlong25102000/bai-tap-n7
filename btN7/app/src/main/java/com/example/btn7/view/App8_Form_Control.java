package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btn7.R;

public class App8_Form_Control extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNameImageView;
    private ImageView imageButton;
    private Button btn1;
    private boolean viewType = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app8_form_control);
        initView();
        initEvent();
    }

    private void initEvent() {
//        btn1.setOnClickListener(this);
        imageButton.setOnClickListener(this);
    }

    private void initView() {
        txtNameImageView = findViewById(R.id.txtNameImageView);
        btn1 = findViewById(R.id.btnButton1);
        imageButton = findViewById(R.id.imageButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnButton1:
                break;
            case R.id.imageButton:
                viewType = !viewType;
                if (viewType) {
                    imageButton.setImageResource(R.drawable.ic_launcher1);
                    txtNameImageView.setText("Skateboarder");
                } else {
                    imageButton.setImageResource(R.drawable.ic_launcher2);
                    txtNameImageView.setText("Button");
                }
                break;
        }
    }
}