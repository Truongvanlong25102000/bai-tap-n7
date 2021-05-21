package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.example.btn7.R;

public class SwitchBetweenPortraitAndLandscape extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_between_portrait_and_landscape);
        Configuration config = getResources().getConfiguration();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new Landscape_Fragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new Portrait_Fragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}