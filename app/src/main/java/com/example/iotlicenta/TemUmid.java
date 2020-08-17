package com.example.iotlicenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TemUmid extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refTemperatura,refTemp ,refUmdi;

    TextView textViewTemp1;
    TextView textViewUmid1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperatura, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewTemp1 = getActivity().findViewById(R.id.textViewTemp);
        textViewUmid1 = getActivity().findViewById(R.id.textViewUmid);


        refTemperatura = refHome.child("temperatura");
        refTemp = refTemperatura.child("temp");
        refUmdi = refTemperatura.child("umiditate");

        veziTemp(refTemp,textViewTemp1);
        veziUmdi(refUmdi,textViewUmid1);
    }

    private void veziTemp(final DatabaseReference refTemp_1, final TextView textViewTemp) {

        refTemp_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String vezi_temp = dataSnapshot.getValue()+"°C";
                textViewTemp.setText(vezi_temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


    }

    private void veziUmdi(final DatabaseReference refUmdi, final TextView textViewUmid) {

        refUmdi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String vezi_umdi = dataSnapshot.getValue()+"%";
                textViewUmid.setText(vezi_umdi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }



}
