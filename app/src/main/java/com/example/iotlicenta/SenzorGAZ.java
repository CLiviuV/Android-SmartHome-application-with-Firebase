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

public class SenzorGAZ extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refGaz,refSenzori;
    ToggleButton btnToggle5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gaz, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refSenzori = refHome.child("senzori");
        refGaz = refSenzori.child("Senzor_GAZ");

        btnToggle5 = getActivity().findViewById(R.id.toggleButton5);
        btnToggle5.setTextOn("ALERTA! SENZOR GAZ ACTIV");
        btnToggle5.setTextOff("SENZOR GAZ INACTIV");


        senzorGaz(refGaz,btnToggle5);

    }


    private void senzorGaz(final DatabaseReference refGaz, final ToggleButton btnToggle5 ) {

        btnToggle5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refGaz.setValue(isChecked);
            }
        });

        refGaz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_apa  = (Boolean) dataSnapshot.getValue();
                btnToggle5.setChecked(stare_apa);
                if(stare_apa){
                    btnToggle5.setTextOn("ALERTA! SENZOR GAZ ACTIV");
                } else {
                    btnToggle5.setTextOff("SENZOR GAZ INACTIV");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }
}
