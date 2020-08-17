package com.example.iotlicenta;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
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

public class SenzorApa extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refApa,refSenzori;
    ToggleButton btnToggle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apa, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refSenzori = refHome.child("senzori");
        refApa = refSenzori.child("Senzor_APA");

        btnToggle = getActivity().findViewById(R.id.toggleButton);
        btnToggle.setTextOn("ALERTA! SENZOR APA ACTIV");
        btnToggle.setTextOff("SENZOR APA INACTIV");




        senzorApa(refApa,btnToggle);



    }


    private void senzorApa(final DatabaseReference refApa, final ToggleButton btnToggle ) {

        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refApa.setValue(isChecked);
            }
        });

        refApa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_apa  = (Boolean) dataSnapshot.getValue();
                btnToggle.setChecked(stare_apa);
                if(stare_apa){
                    btnToggle.setTextOn("ALERTA! SENZOR APA ACTIV");
                } else {
                    btnToggle.setTextOff("SENZOR APA INACTIV");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }
}
