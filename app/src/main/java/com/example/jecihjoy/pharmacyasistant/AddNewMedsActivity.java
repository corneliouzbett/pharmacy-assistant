package com.example.jecihjoy.pharmacyasistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class AddNewMedsActivity extends AppCompatActivity {
    EditText name;
    EditText type;
    EditText date;
    EditText usage;
    Button save;
    Spinner typeSpinner;
    private ArrayList<String> mValuesList;
    DatePicker expirydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_meds);

        name = (EditText)findViewById(R.id.edit_name);
        usage = (EditText)findViewById(R.id.edit_usage);
        typeSpinner = (Spinner) findViewById(R.id.spinner_category2);
        expirydate = (DatePicker) findViewById(R.id.datepicker_expirydate);

        mValuesList = new ArrayList<>();
        String[] items = new String[]{"antiacids", "painkillers", "antifungal", "antiviral"};

        mValuesList.addAll(Arrays.asList(items));

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mValuesList);
        typeSpinner.setAdapter(adapter);

        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int year1 = expirydate.getYear();
                int month1 = expirydate.getMonth();
                int day1 = expirydate.getDayOfMonth();
                String date = day1 +"/"+ month1 +"/"+ year1;
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext());
                databaseAdapter.open();
                try {
                    databaseAdapter.createNewMeds(
                            name.getText().toString(),
                            typeSpinner.getSelectedItem().toString(),
                            date,
                            usage.getText().toString());

                } catch (Exception e) {

                }
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                databaseAdapter.close();
            }
        });
    }
}
