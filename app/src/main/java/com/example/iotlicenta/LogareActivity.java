package com.example.iotlicenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogareActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegistre;
    BazaDateAjutor db;
    EditText mPorecla;

    public final static String MESSAGE_KEY ="Stringul folosit pentru trasmiterea Poreclei in PanouActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logare);

        db = new BazaDateAjutor(this);
        //mPorecla = (EditText) findViewById(R.id.porecla);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegistre = (TextView) findViewById(R.id.textview_register);
        mTextViewRegistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent (LogareActivity.this, InregitrareActivity.class);
                startActivity(registerIntent);
            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                //String message = mPorecla.getText().toString();
                Boolean res = db.checkUser(user, pwd);
                if (res == true) {
                    Intent PanouPage = new Intent(LogareActivity.this,ControlActivity.class);
                    //PanouPage.putExtra(MESSAGE_KEY,message);
                    startActivity(PanouPage);
                }
                else
                {
                    Toast.makeText(LogareActivity.this,"Login Error",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

}
