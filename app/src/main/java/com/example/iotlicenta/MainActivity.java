package com.example.iotlicenta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends Activity {

    private static int NUMARATOARE_INVERSA = 3000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler() .postDelayed(new Runnable(){
            //postDelayed va fi reluat pe firul la care este atasat HANDLER_UL
            //Variabila NUMARATOARE INVESA va fi timpul in care se va adauga o intarziere la executia Intentului
            //Dupa ce trec cele 4 secunde metoda intra pe run si Intentul ne muta automat pe LogareActivity
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LogareActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },NUMARATOARE_INVERSA);


    }
}
