package com.example.jecihjoy.pharmacyasistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class MedsIssueFragment extends Fragment {

    Button btnIssue;
    int oldAmount = 0;
    int id;
    int newAmount;
    int issueAmount;
    String expirydate;

    TextView medname;
    Spinner frequencySpinner;
    EditText days;
    TextView amount;
    private ArrayList<String> mValuesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentlayout = inflater.inflate(R.layout.fragment_meds_issue, container, false);

        medname = (TextView) fragmentlayout.findViewById(R.id.edit_medname);
        frequencySpinner = (Spinner) fragmentlayout.findViewById(R.id.spinner_frequency);
        days = (EditText) fragmentlayout.findViewById(R.id.edit_days);
        amount = (TextView) fragmentlayout.findViewById(R.id.edit_remaining);

        mValuesList = new ArrayList<>();
        String[] items = new String[]{"1*1", "1*2", "1*3", "2*1", "2*2", "2*3", "3*1", "3*2", "3*3"};

        mValuesList.addAll(Arrays.asList(items));

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mValuesList);
        frequencySpinner.setAdapter(adapter); //frequencySpinner.getSelectedItem().toString();


        Intent intent = getActivity().getIntent();

        if (intent != null) {
            id = intent.getIntExtra("medID", 0);
            expirydate = intent.getStringExtra("expiredate");
            medname.setText(intent.getExtras().getString(HomeActivity.MEDICINE_NAME_EXTRA));
            oldAmount = intent.getIntExtra(HomeActivity.MEDICINE_AMOUNT_EXTRA, 0);
        }

        btnIssue = (Button) fragmentlayout.findViewById(R.id.btn_issue);
        btnIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String frequency = frequencySpinner.getSelectedItem().toString();
                String first = String.valueOf(frequency.charAt(0));
                String last = frequency.substring(2);
                Log.e("Manipulate", first+ " "+ last);
                issueAmount = Integer.parseInt(first) * Integer.parseInt(last) *
                        Integer.parseInt(days.getText().toString());

                newAmount = oldAmount - issueAmount;
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getActivity().getBaseContext());
                databaseAdapter.open();
                databaseAdapter.updateStock(id, newAmount, expirydate );
                databaseAdapter.close();

                amount.setText("New amount remaining is   "+newAmount);
            }
        });
        return  fragmentlayout;
    }













    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MedsIssueFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MedsIssueFragment newInstance(String param1, String param2) {
        MedsIssueFragment fragment = new MedsIssueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
