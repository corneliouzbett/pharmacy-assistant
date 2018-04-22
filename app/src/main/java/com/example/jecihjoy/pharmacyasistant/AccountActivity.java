package com.example.jecihjoy.pharmacyasistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

public class AccountActivity extends AppCompatActivity {
    EditText mid;
    EditText mfname;
    EditText lname;
    EditText musername;
    EditText phone;
    EditText memail;
    EditText mlocation;
    EditText mpass1;
    EditText mpass2;
    Button mySave;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mid = (EditText) findViewById(R.id.edit_nationid);
        mfname = (EditText) findViewById(R.id.edit_fname);
        lname = (EditText) findViewById(R.id.edit_lname);
        musername = (EditText) findViewById(R.id.edit_username);
        phone = (EditText) findViewById(R.id.edit_phone);
        memail = (EditText) findViewById(R.id.edit_email);
        mlocation = (EditText) findViewById(R.id.edit_location);
        mpass1 = (EditText) findViewById(R.id.edit_pass1);
        mpass2 = (EditText) findViewById(R.id.edit_pass2);

        mySave = (Button) findViewById(R.id.save);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMedActivity = new Intent(getApplicationContext(), NewUserActivity.class);
                startActivity(startMedActivity);
            }
        });

        mySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
                databaseAdapter.open();
                try {
                    databaseAdapter.insertUsers(
                            Integer.parseInt(mid.getText().toString()),
                                    mfname.getText().toString(),
                                    lname.getText().toString(),
                                    musername.getText().toString(),
                                    phone.getText().toString(),
                                    memail.getText().toString(),
                                    mlocation.getText().toString(),
                                    mpass1.getText().toString(),
                                    mpass2.getText().toString());


                } catch (Exception e) {

                }
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                databaseAdapter.close();

                Intent startMedActivity = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(startMedActivity);
            }
        });



    }
}
