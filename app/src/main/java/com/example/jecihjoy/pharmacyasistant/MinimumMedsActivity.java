package com.example.jecihjoy.pharmacyasistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.Model.Medicine;
import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;
import com.example.jecihjoy.pharmacyasistant.adapters.MyMedRecyclerAdapter;

import java.util.ArrayList;

public class MinimumMedsActivity extends AppCompatActivity {

    public static long id;
    public DatabaseAdapter databaseAdapter;
    public ArrayList<Medicine> medicines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        setTitle("Drugs below minimum");

        //dbAdapter class
        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        medicines = databaseAdapter.getMinimumMeds("200");
        databaseAdapter.close();

        // Toast.makeText(this,""+medicines,Toast.LENGTH_LONG).show();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_recycler_view);
        if((medicines.size() != 0)){
            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setHasFixedSize(true);
            MyMedRecyclerAdapter adapter = new MyMedRecyclerAdapter(medicines);
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(this, "No drugs are below minimum level, 200 ", Toast.LENGTH_SHORT).show();
        }

    }
}
