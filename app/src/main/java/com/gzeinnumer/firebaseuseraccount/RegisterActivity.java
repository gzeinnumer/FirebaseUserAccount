package com.gzeinnumer.firebaseuseraccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password;
    private Button btnRegis;
    private TextView btnToLogin;

    //todo 13
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupUIVIews();

        //todo 14
        firebaseAuth = FirebaseAuth.getInstance();

        //todo 2
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 3
                if (validate()){ //true
                    //upload data to database
                    //todo 15
                    String strEmail = email.getText().toString().trim();
                    String strPass = password.getText().toString().trim();

                    //todo 16
                    firebaseAuth.createUserWithEmailAndPassword(strEmail, strPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //todo 17
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Register sukses!!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Register gagal!!", Toast.LENGTH_SHORT).show();
                                    }
                                    //test ke browser
                                }
                            });
                }
            }
        });

        //todo 4
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 5
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void setupUIVIews() {
        name = findViewById(R.id.ed_name);
        email = findViewById(R.id.ed_email);
        password = findViewById(R.id.ed_pass);
        btnRegis = findViewById(R.id.btn_register);
        btnToLogin = findViewById(R.id.btn_to_login);
    }

    //todo 1
    private Boolean validate(){
        Boolean result = false;

        String strName = name.getText().toString();
        String strEmail = email.getText().toString();
        String strPass = password.getText().toString();

        if (strName.isEmpty() && strEmail.isEmpty() && strPass.isEmpty()){
            Toast.makeText(this, "Lengkapi data", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }
}
