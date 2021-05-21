package com.example.btn7.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btn7.R;

import org.jetbrains.annotations.NotNull;

public class FragmentYesNo extends Fragment {
    private IFragmentYesNoHandler iFragmentYesNoHandler;
    private Button btnY,btnN;
    private TextView txt;
    public FragmentYesNo(IFragmentYesNoHandler iFragmentYesNoHandler) {
        this.iFragmentYesNoHandler = iFragmentYesNoHandler;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yes_no, container, false);
        btnY=view.findViewById(R.id.buttonYes);
        btnN=view.findViewById(R.id.buttonNo);
        txt=view.findViewById(R.id.textQuestion);

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFragmentYesNoHandler.question("N");
            }
        });

        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFragmentYesNoHandler.question("Y");
            }
        });

        return view;
    }
}
