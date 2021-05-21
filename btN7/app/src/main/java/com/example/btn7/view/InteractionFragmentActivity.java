package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.btn7.R;

public class InteractionFragmentActivity extends AppCompatActivity implements IFragmentYesNoHandler {
    IFragmentYesNoHandler iFragmentYesNoHandler = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction_fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentYesNo(iFragmentYesNoHandler)).commit();
    }

    @Override
    public void question(String s) {
        if (s.equalsIgnoreCase("Y")) {
            Toast.makeText(InteractionFragmentActivity.this, "Yes", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(InteractionFragmentActivity.this, "No", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}