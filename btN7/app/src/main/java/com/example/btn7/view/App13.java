package com.example.btn7.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.btn7.R;

public class App13 extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app13);
        txt=findViewById(R.id.txt);
        SharedPreferences sharedPreferences = getSharedPreferences("Cocacola", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("wellcome",false)){
            txt.setVisibility(View.GONE);
        }else{
            txt.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("wellcome",true);
            editor.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_13, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Intent intent=new Intent(App13.this,SettingScreen.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}