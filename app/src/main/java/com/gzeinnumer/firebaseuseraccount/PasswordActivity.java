package com.gzeinnumer.firebaseuseraccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

//todo 45
public class PasswordActivity extends AppCompatActivity {

    //todo 46 buka firebase  , dan setting template password reset

    //todo 48
    EditText edEmail;
    Button btnResetPass;

    //todo 52
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //todo 49
        setupUIViews();

        //todo 53
        firebaseAuth = FirebaseAuth.getInstance();

        //todo 51
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 52
                String email = edEmail.getText().toString().trim();

                if (email.equals("")){
                    Toast.makeText(PasswordActivity.this, "masukan Email", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this, "Email reset password terkirim", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            } else {
                                Toast.makeText(PasswordActivity.this, "Email reset password gagal dikirim", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    //todo 50
    private void setupUIViews() {
        edEmail = findViewById(R.id.ed_email);
        btnResetPass = findViewById(R.id.btn_reset_pass);
    }
}
