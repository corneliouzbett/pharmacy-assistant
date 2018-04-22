package com.example.jecihjoy.pharmacyasistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserLoginActivity extends AppCompatActivity {

    EditText username;
    Button login;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = (EditText) findViewById(R.id.edit_username);

        login = (Button) findViewById(R.id.savebtn);
        cancel = (Button) findViewById(R.id.cancelbtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeAdminActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString() == "Admin"){
                    startActivity(new Intent(getApplicationContext(), HomeAdminActivity.class));
                }else if (username.getText().toString() != "Admin"){
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                }
            }
        });
    }
}
