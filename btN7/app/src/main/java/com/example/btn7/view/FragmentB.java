package com.example.btn7.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btn7.R;

import org.jetbrains.annotations.NotNull;

public class FragmentB extends Fragment {
    Button btn;
    SwitchFragmentHandle switchFragmentHandle;

    public FragmentB(SwitchFragmentHandle switchFragmentHandle){
        this.switchFragmentHandle=switchFragmentHandle;
    }

    public FragmentB(){

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_b,container,false);
        btn=view.findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchFragmentHandle!=null){
                    switchFragmentHandle.mSwitchFragment("A");
                }
            }
        });
        return view;
    }
}
