package com.gzeinnumer.firebaseuseraccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnLogin;
    private TextView btnToRegister, tv_counter;

    //todo 18
    private int counter =5;

    //todo 28
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUIVIews();

        //todo 19
        tv_counter.setText("Kesempatan tinggal: "+counter);

        //todo 29
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //todo 7
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 8
//                if (validate()){ //true
//
//                }
                //di todo23 todo8 dikomentarkan

                //todo 23
                validate(email.getText().toString().trim(), password.getText().toString().trim());
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
//    private Boolean validate(){
//        boolean result = false;
//
//        String strEmail = email.getText().toString();
//        String strPassword= password.getText().toString();
//
//        if (strEmail.isEmpty() && strPassword.isEmpty()){
//            Toast.makeText(this, "Isi semua data!", Toast.LENGTH_SHORT).show();
//
//            //todo 20
//            counter--;
//            if (counter == 0){
//                btnLogin.setEnabled(false);
//            }
//
//            //todo 21 run dan test register ke firebase
//            //hasil nya register bisa dan data masuk ke fireabase
//            //todo 22 todo6 akan dikomentarkan
//
//        } else {
//            result = true;
//        }
//
//        return result;
//    }
    //end todo 6

    //todo 24
    private void validate(String email, String pass){
        //todo 30
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //todo 31
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //todo 32
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    //todo 40 komentarkan baris diatas
                    checkEmailVerification();
                    //end todo 40
                } else {
                    Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                    counter--;
                    tv_counter.setText("Kesempatan habis");
                    progressDialog.dismiss();
                    if (counter ==0){
                        btnLogin.setEnabled(false);
                    }
                }
            }
        });
        //coba jalankan
    }

    //todo 39
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            Toast.makeText(this, "Verificasi email dahulu", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
