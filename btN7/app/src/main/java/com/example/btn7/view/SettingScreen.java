package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.btn7.R;
import com.example.btn7.SettingFragment;

public class SettingScreen extends AppCompatActivity {
    public static final String SETTINGS = "com.example.app_13.settings";
    public static final String FIRST_USE = "com.example.app_13.firstUse";
    LinearLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        rootLayout=findViewById(R.id.rootLayout);
        getFragmentManager().beginTransaction().replace(R.id.rootLayout,new SettingFragment()).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout,new SettingFragment()).commit();
    }
}