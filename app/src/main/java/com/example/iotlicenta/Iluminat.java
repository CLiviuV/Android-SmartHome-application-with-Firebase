package com.example.iotlicenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Iluminat extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refLumini, refLumini_camera_1,refLumini_camera_2,refLumini_camera_3,refLumini_camera_4;
    ToggleButton btnToggle1;
    ToggleButton btnToggle2;
    ToggleButton btnToggle3;
    ToggleButton btnToggle4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iluminat, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        refLumini = refHome.child("lumini");
        refLumini_camera_1 = refLumini.child("lumina_camera_1");
        refLumini_camera_2 = refLumini.child("lumina_camera_2");
        refLumini_camera_3 = refLumini.child("lumina_camera_3");
        refLumini_camera_4 = refLumini.child("lumina_camera_4");

        btnToggle1 = getActivity().findViewById(R.id.toggleButton_fragment);
        btnToggle2 = getActivity().findViewById(R.id.toggleButton2);
        btnToggle3 = getActivity().findViewById(R.id.toggleButton3);
        btnToggle4 = getActivity().findViewById(R.id.toggleButton4);



        controlLED1(refLumini_camera_1, btnToggle1);
        controlLED2(refLumini_camera_2, btnToggle2);
        controlLED3(refLumini_camera_3, btnToggle3);
        controlLED4(refLumini_camera_4, btnToggle4);

    }

        private void controlLED1(final DatabaseReference refLumini_camera_1, final ToggleButton toggle_btn ) {

        toggle_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refLumini_camera_1.setValue(isChecked);
            }
        });

        refLumini_camera_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_led  = (Boolean) dataSnapshot.getValue();
                toggle_btn.setChecked(stare_led);
                if(stare_led){
                    toggle_btn.setTextOn("APRINS BEC 1");
                } else {
                    toggle_btn.setTextOff("STINS BEC 1");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }

    private void controlLED2(final DatabaseReference refLumini_camera_2, final ToggleButton toggle_btn2 ) {

        toggle_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refLumini_camera_2.setValue(isChecked);
            }
        });

        refLumini_camera_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_led  = (Boolean) dataSnapshot.getValue();
                toggle_btn2.setChecked(stare_led);
                if(stare_led){
                    toggle_btn2.setTextOn("APRINS BEC 2");
                } else {
                    toggle_btn2.setTextOff("STINS BEC 2");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }


    private void controlLED3(final DatabaseReference refLumini_camera_3, final ToggleButton toggle_btn3 ) {

        toggle_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refLumini_camera_3.setValue(isChecked);
            }
        });

        refLumini_camera_3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_led  = (Boolean) dataSnapshot.getValue();
                toggle_btn3.setChecked(stare_led);
                if(stare_led){
                    toggle_btn3.setTextOn("APRINS BEC 3");
                } else {
                    toggle_btn3.setTextOff("STINS BEC 3");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }


    private void controlLED4(final DatabaseReference refLumini_camera_4, final ToggleButton toggle_btn4 ) {

        toggle_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refLumini_camera_4.setValue(isChecked);
            }
        });

        refLumini_camera_4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_led  = (Boolean) dataSnapshot.getValue();
                toggle_btn4.setChecked(stare_led);
                if(stare_led){
                    toggle_btn4.setTextOn("APRINS BEC 4");
                } else {
                    toggle_btn4.setTextOff("STINS BEC 4");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }
}