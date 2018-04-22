package com.example.jecihjoy.pharmacyasistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    TextView mname;
    EditText amount;
    Button mSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mname = (TextView) findViewById(R.id.edit_medname);
        amount = (EditText) findViewById(R.id.edit_orderamount);
        mSaveBtn = (Button) findViewById(R.id.btn_saveorder);
    }
}
