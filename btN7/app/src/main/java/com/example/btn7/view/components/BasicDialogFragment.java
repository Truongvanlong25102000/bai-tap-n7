package com.example.btn7.view.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.btn7.R;

import org.jetbrains.annotations.NotNull;

public class BasicDialogFragment extends DialogFragment {
    Button mOK;
    Button mCancel;
    EditText mNameField;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_name, container, false);
        mNameField=view.findViewById(R.id.editText1);
        mCancel=view.findViewById(R.id.buttonCancel);
        mOK=view.findViewById(R.id.buttonOK);

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicDialogFragment.this.dismiss();
            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Input: "+mNameField.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
