package com.example.iotlicenta;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControlActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference  refButoane, refButon_A,refSenzori,refApa,refGaz,refPir;



    public static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified coding";
    private static final String CHANNEL_DESC = "Simplified Coding Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//        if (savedInstanceState == null){
//             getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new TemUmid()).commit();
//             navigationView.setCheckedItem(R.id.nav_temperatura);
//        }

        refSenzori = refHome.child("senzori");
        refApa = refSenzori.child("Senzor_APA");
        refGaz = refSenzori.child("Senzor_GAZ");
        refPir = refSenzori.child("Senzor_PIR");
//
//        refButoane = refHome.child("butoane");
//        refButon_A = refButoane.child("buton_a");




//
//
       //textStareButon = (TextView) findViewById(R.id.textView);

        //textViewSenzprApa = findViewById(R.id.textViewSenzprApa);
//
     //  controlLED(refLumini_camera_1, btnToggle);
//
       senzorApa(refApa);
        senzorGaz(refGaz);
        senzorPIR(refPir);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_temperatura:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TemUmid()).commit();
                break;
            case R.id.nav_iluminat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Iluminat()).commit();
                break;

            case R.id.nav_apa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SenzorApa()).commit();
                break;

            case R.id.nav_gaz:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SenzorGAZ()).commit();
                break;
            case R.id.nav_alarma:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SenzorPIR()).commit();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }



    private void senzorApa(final DatabaseReference refSenzori_apa) {

        refSenzori_apa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean senzor_apa = (Boolean) dataSnapshot.getValue();
                if (senzor_apa){
                     displaynotificationAPA();
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void senzorGaz(final DatabaseReference refSenzori_gaz) {

        refSenzori_gaz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean senzor_gaz = (Boolean) dataSnapshot.getValue();
                if (senzor_gaz){
                    displaynotificationGAZ();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void senzorPIR(final DatabaseReference refSenzori_pir) {

        refSenzori_pir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean senzor_pir = (Boolean) dataSnapshot.getValue();
                if (senzor_pir){
                    displaynotificationPIR();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }



    public void displaynotificationAPA() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_not5)
                        .setVibrate(new long[]{300, 900, 2000})
                        .setLights(0xff0000ff, 300, 1000)
                        .setContentTitle("ALERTA CASA!")
                        .setContentText("S-a detectat o scurgere de apa in casa")
//                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());

    }

    public void displaynotificationGAZ() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_not5)
                        .setVibrate(new long[]{300, 900, 2000})
                        .setLights(0xff0000ff, 300, 1000)
                        .setContentTitle("ALERTA CASA!")
                        .setContentText("Exista gaz in incapere")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());

    }
    public void displaynotificationPIR() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_not5)
                        .setVibrate(new long[]{300, 900, 2000})
                        .setLights(0xff0000ff, 300, 1000)
                        .setContentTitle("ALERTA CASA!")
                        .setContentText("S-a detectat o miscare in casa")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());

   }
}
