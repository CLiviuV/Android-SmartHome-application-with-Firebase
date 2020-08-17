package com.example.iotlicenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class SenzorPIR extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refPir,refSenzori,refButoane, refButon_A;
    ToggleButton btnToggle;
    ToggleButton btnToggleOnOff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarma, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refSenzori = refHome.child("senzori");
        refPir = refSenzori.child("Senzor_PIR");

        refButoane = refHome.child("butoane");
        refButon_A = refButoane.child("buton_a");

        btnToggle = getActivity().findViewById(R.id.toggleButton6);
        btnToggle.setTextOn("ALERTA! CINEVA ESTE IN CASA");
        btnToggle.setTextOff("SENZOR MISCARE INACTIV");

//        btnToggleOnOff = getActivity().findViewById(R.id.toggleButton7);





        senzorPir(refPir,btnToggle);
        //actdezAlarma(refButon_A,btnToggleOnOff);



    }


    private void senzorPir(final DatabaseReference refPir, final ToggleButton btnToggle ) {

        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refPir.setValue(isChecked);
            }
        });

        refPir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean stare_pir  = (Boolean) dataSnapshot.getValue();
                btnToggle.setChecked(stare_pir);
                if(stare_pir){
                    btnToggle.setTextOn("ALERTA! ESTE CINEVA IN CASA");
                } else {
                    btnToggle.setTextOff("SENZOR MISCARE INACTIV");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }

        });
    }

//    private void actdezAlarma(final DatabaseReference refButon_A, final ToggleButton btnToggleOnOff) {
//
//        btnToggleOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                refButon_A.setValue(isChecked);
//            }
//        });
//
//        refButon_A.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Boolean stare_alarma  = (Boolean) dataSnapshot.getValue();
//                btnToggleOnOff.setChecked(stare_alarma);
//                if(stare_alarma){
//                    btnToggleOnOff.setTextOn("");
//                } else {
//                    btnToggleOnOff.setTextOff("");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) { }
//
//        });
//    }
}
