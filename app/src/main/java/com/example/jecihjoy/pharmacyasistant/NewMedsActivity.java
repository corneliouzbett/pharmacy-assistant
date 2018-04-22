package com.example.jecihjoy.pharmacyasistant;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.Model.Replacements;
import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

import java.util.ArrayList;

public class NewMedsActivity extends AppCompatActivity {

    public static long id;
    public DatabaseAdapter databaseAdapter;
    public ArrayList<Replacements> medicines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Meds Invented");
        setContentView(R.layout.content_home);


        //dbAdapter class
        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        medicines = databaseAdapter.getNewMeds();
        databaseAdapter.close();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_recycler_view);
        if((medicines.size() != 0)){
            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setHasFixedSize(true);
            MyMedRecyclerAdapter adapter = new MyMedRecyclerAdapter(medicines);
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(this, "No new updates yet ", Toast.LENGTH_SHORT).show();
        }

    }

    public class MyMedRecyclerAdapter extends  RecyclerView.Adapter<MyMedRecyclerAdapter.MedAdapterViewHolder> {

        ArrayList<Replacements> myMeds;

        public MyMedRecyclerAdapter(ArrayList<Replacements> medData){
            myMeds = medData;
        }

        public class MedAdapterViewHolder extends RecyclerView.ViewHolder{

            public CardView mCardView;
            public TextView mTxtName;
            public TextView mTxtAmount;
            public TextView mTxtDate;
            public TextView mTxtType;
            public TextView mTxtDesc;
            public MedAdapterViewHolder( View viewItem){
                super(viewItem);

                mCardView = (CardView) viewItem.findViewById(R.id.card_view);
                mTxtName = (TextView) viewItem.findViewById(R.id.txt_name);
                mTxtAmount = (TextView) viewItem.findViewById(R.id.txt_amount);
                mTxtDate = (TextView) viewItem.findViewById(R.id.txt_date);
                mTxtType = (TextView) viewItem.findViewById(R.id.txt_type);
                mTxtDesc = (TextView) viewItem.findViewById(R.id.txt_desc);
            }
        }

        @Override
        public MedAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            int layout = R.layout.med_card;
            LayoutInflater infater = LayoutInflater.from(context);
            boolean attachImmeaditely = false;

            View view = infater.inflate(layout, parent,attachImmeaditely);
            MedAdapterViewHolder myViewHolder = new MedAdapterViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MedAdapterViewHolder holder, final int position) {
            Replacements md  = myMeds.get(position);
            holder.mTxtName.setText("Name: "+md.getNewMed());
            holder.mTxtType.setText("Type: "+md.getDate());
            holder.mTxtAmount.setText("Usage: "+md.getTypeA());
            holder.mTxtDate.setText("Date invented: "+md.getReason() );
            holder.mTxtDesc.setText( "");
        }

        @Override
        public int getItemCount() {

            return myMeds.size();
        }
    }
}
