package com.example.jecihjoy.pharmacyasistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;


public class StockMedsFragment extends Fragment {

    TextView medname;
    EditText newamount;
    TextView amount;
    Button btnStock;
    DatePicker expirydate;
    int oldAmount = 0;
    int id;
    int newAmount;
    private OnFragmentInteractionListener mListener;

    public StockMedsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentlayout = inflater.inflate(R.layout.fragment_stock_meds, container, false);

        medname = (TextView) fragmentlayout.findViewById(R.id.edit_medname);
        newamount = (EditText) fragmentlayout.findViewById(R.id.edit_amount);
        expirydate = (DatePicker) fragmentlayout.findViewById(R.id.datepicker_expirydate);
        amount= (TextView) fragmentlayout.findViewById(R.id.edit_remaining2);


        Intent intent = getActivity().getIntent();
        if (intent != null) {
            id = intent.getIntExtra("medID", 0);
            medname.setText(intent.getExtras().getString(HomeActivity.MEDICINE_NAME_EXTRA));
            oldAmount = intent.getIntExtra(HomeActivity.MEDICINE_AMOUNT_EXTRA, 0);
        }

        amount.setText("Amount remaining is   "+oldAmount);

        btnStock = (Button) fragmentlayout.findViewById(R.id.btn_stock);
        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year1 = expirydate.getYear();
                int month1 = expirydate.getMonth();
                int day1 = expirydate.getDayOfMonth();
                String date = day1 +"/"+ month1 +"/"+ year1;

                newAmount = oldAmount + Integer.parseInt(newamount.getText().toString());
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getActivity().getBaseContext());
                databaseAdapter.open();
                databaseAdapter.updateStock(id, newAmount, date );
                databaseAdapter.close();

                amount.setText("New amount remaining is   "+newAmount);
            }
        });



        return  fragmentlayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
