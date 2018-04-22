package com.example.jecihjoy.pharmacyasistant;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ManipulateMedActivity extends AppCompatActivity implements MedsIssueFragment.OnFragmentInteractionListener,
        StockMedsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulate_med);
        fragmentManager();
    }

    //for dynamically adding the fragments
    private void fragmentManager(){
        Intent intent  = getIntent();
        HomeActivity.fragmentToLaunch  fragment =
                (HomeActivity.fragmentToLaunch) intent.getSerializableExtra(HomeActivity.MEDICINE_FRAGMENT_TO_LOAD_EXTRA);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragment ){
            case ISSUE:
                MedsIssueFragment noteEditFragment = new MedsIssueFragment();
                setTitle("ISSUE MEDICINE");
                fragmentTransaction.add(R.id.note_container, noteEditFragment, "EDIT _WITH_FRAGMENT" );

                break;
            case STOCK:
                StockMedsFragment noteViewFragment = new StockMedsFragment();
                setTitle("ADD NEW AMOUNT" );
                fragmentTransaction.add(R.id.note_container, noteViewFragment, "VIEW_WITH_FRAGMENT" );
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
