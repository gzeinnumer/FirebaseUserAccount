package com.gzeinnumer.firebaseuseraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnLogin;
    private TextView btnToRegister, tv_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUIVIews();

        //todo 7
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 8
                if (validate()){ //true

                }
            }
        });

        //todo 9
        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 10
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

                //TODO 11 buat koneksi ke FIREBASE dengan tools dari AndroiStudio
                //Database yang dipakai adalah FirebaseDemo
                //todo 12 buka Firebase dan izinkan login dengan email.
            }
        });

    }

    private void setupUIVIews() {
        email = findViewById(R.id.ed_email);
        password = findViewById(R.id.ed_pass);
        btnLogin = findViewById(R.id.btn_login);
        tv_counter = findViewById(R.id.tv_counter);
        btnToRegister = findViewById(R.id.btn_to_regis);
    }

    //todo 6
    private Boolean validate(){
        boolean result = false;

        String strEmail = email.getText().toString();
        String strPassword= password.getText().toString();

        if (strEmail.isEmpty() && strPassword.isEmpty()){
            Toast.makeText(this, "Isi semua data!", Toast.LENGTH_SHORT).show();


        } else {
            result = true;
        }

        return result;
    }
}
