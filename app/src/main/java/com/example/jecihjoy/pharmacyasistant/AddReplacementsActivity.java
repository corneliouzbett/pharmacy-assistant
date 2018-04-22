package com.example.jecihjoy.pharmacyasistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

public class AddReplacementsActivity extends AppCompatActivity {

    EditText oldMed;
    EditText newMed;
    EditText reason;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replaced);

        oldMed = (EditText) findViewById(R.id.edit_old);
        newMed = (EditText) findViewById(R.id.edit_new);
        reason = (EditText) findViewById(R.id.edit_reason);

        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
                databaseAdapter.open();
                try {
                    databaseAdapter.insertReplaced(
                            oldMed.getText().toString(),
                            newMed.getText().toString(),
                            reason.getText().toString());

                } catch (Exception e) {

                }
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                databaseAdapter.close();
            }
        });
    }
}
