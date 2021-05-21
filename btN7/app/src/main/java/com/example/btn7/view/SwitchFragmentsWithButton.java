package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.btn7.R;

public class SwitchFragmentsWithButton extends AppCompatActivity implements SwitchFragmentHandle {

    private SwitchFragmentHandle switchFragmentHandle = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_fragments_with_button);
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, new FragmentA(switchFragmentHandle)).commit();
    }

    @Override
    public void mSwitchFragment(String nameFragment) {
        if(nameFragment.equalsIgnoreCase("A")){
            getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, new FragmentA(switchFragmentHandle)).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, new FragmentB(switchFragmentHandle)).commit();
        }
    }
}