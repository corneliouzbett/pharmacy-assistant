package com.example.jecihjoy.pharmacyasistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewUserActivity extends AppCompatActivity {

    TextView mLogin, mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
//        getSupportActionBar().hide();

        mLogin = (TextView) findViewById(R.id.txt_login);
        mAccount = (TextView) findViewById(R.id.text_account);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UserLoginActivity.class));
            }
        });

        mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            }
        });
    }
}
