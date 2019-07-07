package com.gzeinnumer.firebaseuseraccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //todo 25
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //todo 26
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //todo 27
        if (user == null){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    //todo 33
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //todo 35
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //todo 34
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //todo 36
        switch (item.getItemId()){
            case R.id.logout_menu:
                //todo 37
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.refresh_menu:
                break;
            case R.id.setting_menu:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
