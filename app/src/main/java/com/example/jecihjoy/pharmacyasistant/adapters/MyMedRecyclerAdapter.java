package com.example.jecihjoy.pharmacyasistant.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jecihjoy.pharmacyasistant.HomeActivity;
import com.example.jecihjoy.pharmacyasistant.Model.Medicine;
import com.example.jecihjoy.pharmacyasistant.R;

import java.util.ArrayList;

/**
 * Created by Jecihjoy on 4/13/2018.
 */

public class MyMedRecyclerAdapter extends  RecyclerView.Adapter<MyMedRecyclerAdapter.MedAdapterViewHolder> {

    ArrayList<Medicine> myMeds;

    public MyMedRecyclerAdapter(ArrayList<Medicine> medData){
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
        Medicine md  = myMeds.get(position);
        holder.mTxtName.setText(md.getName().toUpperCase());
        holder.mTxtType.setText("Category:  "+ md.getType());
        holder.mTxtDate.setText("Expiry Date:  "+md.getExpirydate());
        holder.mTxtDesc.setText( "Description: "+md.getDesc());
        holder.mTxtAmount.setText("Amount: " +md.getAmount());
        holder.itemView.setTag(md.getId());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String currentValue = medicines[position];
                Log.d("CardView", "CardView Clicked: "  );//+currentValue);
            }
        });
    }

    @Override
    public int getItemCount() {

        return myMeds.size();
    }
    public void setFilter(ArrayList<Medicine> newList){
        myMeds = new ArrayList<>();
        myMeds.addAll(newList);
        notifyDataSetChanged();
    }
}





