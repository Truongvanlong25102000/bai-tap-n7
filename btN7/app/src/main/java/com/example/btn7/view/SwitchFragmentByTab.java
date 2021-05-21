package com.example.btn7.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.btn7.R;

import static androidx.appcompat.app.ActionBar.NAVIGATION_MODE_TABS;

public class SwitchFragmentByTab extends AppCompatActivity {

    LinearLayout layout_container_tab;
    ActionBar.Tab mTab1, mTab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_fragment_by_tab);
        layout_container_tab = findViewById(R.id.layout_container_tab);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(NAVIGATION_MODE_TABS);
        mTab1 = getSupportActionBar().newTab().setText("Fragment A").setTabListener(new androidx.appcompat.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(androidx.appcompat.app.ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentA()).commit();
            }

            @Override
            public void onTabUnselected(androidx.appcompat.app.ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(androidx.appcompat.app.ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

            }
        });
        mTab2 = actionBar.newTab().setText("Fragment B").setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentB()).commit();
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        });

        actionBar.addTab(mTab1);
        actionBar.addTab(mTab2);
    }
}