package com.example.jecihjoy.pharmacyasistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class AddContradictionsActivity extends AppCompatActivity {

    EditText med1;
    EditText med2;
    EditText effects;
    EditText alternatives;
    Button btn_save;
    Spinner type1Spinner;
    Spinner type2Spinner;
    private ArrayList<String> mValuesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contradictions);

        med1 = (EditText) findViewById(R.id.edit_name1);
        med2 = (EditText) findViewById(R.id.edit_name2);
        effects = (EditText) findViewById(R.id.edit_effects);
        alternatives = (EditText) findViewById(R.id.edit_others);
        type1Spinner = (Spinner) findViewById(R.id.spinner_category);
        type2Spinner = (Spinner) findViewById(R.id.spinner_category2);

        mValuesList = new ArrayList<>();
        String[] items = new String[]{"antiacids", "painkillers", "antifungal", "antiviral"};

        mValuesList.addAll(Arrays.asList(items));

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mValuesList);
        type1Spinner.setAdapter(adapter);
        type2Spinner.setAdapter(adapter);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
                databaseAdapter.open();
                try {
                    databaseAdapter.createcontradicting(
                            med1.getText().toString(),
                            type1Spinner.getSelectedItem().toString(),
                            med2.getText().toString(),
                            type2Spinner.getSelectedItem().toString(),
                            effects.getText().toString(),
                            alternatives.getText().toString());

                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }
                databaseAdapter.close();
            }
        });

    }
}
