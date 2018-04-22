package com.example.jecihjoy.pharmacyasistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.jecihjoy.pharmacyasistant.GoogleMaps.NewMapActivity;
import com.example.jecihjoy.pharmacyasistant.Model.Medicine;
import com.example.jecihjoy.pharmacyasistant.adapters.DatabaseAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener{

    public static final String MEDICINE_NAME_EXTRA  = "com.example.jecihjoy.pharmacyasistant.name";
    public static final String MEDICINE_AMOUNT_EXTRA  = "com.example.jecihjoy.pharmacyasistant.amount";

    public static final String MEDICINE_FRAGMENT_TO_LOAD_EXTRA = "com.dummy.mynotebook.fragment_to_load";

    public enum fragmentToLaunch{ISSUE, STOCK}

    public static long id;
    public DatabaseAdapter databaseAdapter;
    public ArrayList<Medicine> medicines;
    public ArrayList<Medicine> minMeds;
    int allMinMeds;
    public MedsRecyclerAdapter adapter;
    FloatingActionButton mAddBtn;
    RecyclerView rv;
    int medicineID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        //add btn
        FloatingActionButton mAddBtn = (FloatingActionButton) findViewById(R.id.add_new_med);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMedActivity = new Intent(getApplicationContext(), AddMedsActivity.class);
                startActivity(startMedActivity);

            }
        });


        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        medicines = databaseAdapter.getAllMeds();
        minMeds = databaseAdapter.getMinimumMeds("200");
        allMinMeds = minMeds.size();
        Log.e("minmeds", allMinMeds + "");
        databaseAdapter.close();

       rv = (RecyclerView) findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        adapter = new MedsRecyclerAdapter(medicines, HomeActivity.this);
        if(!(medicines.size() < 1)){
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(this, "No any medication information, Click the plus button to add your medicines", Toast.LENGTH_SHORT).show();
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Medicine> newList = new ArrayList<>();
        for(Medicine med : medicines){
            String name = med.getName().toLowerCase();
            if(name.contains(newText)){
                newList.add(med);
            }
        }
        adapter.setFilter(newList);
        return  true;
    }

    //options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)  search.getActionView();

        if (search != null) {
            searchView.setOnQueryTextListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent startMedActivity = new Intent(getApplicationContext(), HomeAdminActivity.class);
            startActivity(startMedActivity);
            return true;
        }
        if (id == R.id.action_logout) {
            Intent startMedActivity = new Intent(getApplicationContext(), NewUserActivity.class);
            startActivity(startMedActivity);
            return true;
        }
        if (id == R.id.action_updates) {
            Intent startMedActivity = new Intent(getApplicationContext(), MedsUpdatesActivity.class);
            startActivity(startMedActivity);
            return true;
        }

        if (id == R.id.action_notification) {
            Intent startMedActivity = new Intent(getApplicationContext(), MinimumMedsActivity.class);
            startActivity(startMedActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_replaces) {
            startActivity(new Intent(getApplicationContext(), MedsReplacementsActivity.class));

        } else if (id == R.id.nav_newmeds) {
            startActivity(new Intent(getApplicationContext(), NewMedsActivity.class));

        } else if (id == R.id.nav_contradicting) {
            startActivity(new Intent(getApplicationContext(), ContradictionsActivity.class));

        } else if (id == R.id.nav_updates) {
            startActivity(new Intent(getApplicationContext(), MedsUpdatesActivity.class));

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_supplirs) {
            startActivity(new Intent(getApplicationContext(), NewMapActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //viewholder class
    public class MedAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CardView mCardView;
        public TextView mTxtName;
        public TextView mTxtAmount;
        public TextView mTxtDate;
        public TextView mTxtType;
        public TextView mTxtDesc;

        ArrayList<Medicine> myMeds;

        public MedAdapterViewHolder(View viewItem, ArrayList<Medicine> myMeds) {
            super(viewItem);

            this.myMeds = myMeds;

            mCardView = (CardView) viewItem.findViewById(R.id.card_view);
            mTxtName = (TextView) viewItem.findViewById(R.id.txt_name);
            mTxtAmount = (TextView) viewItem.findViewById(R.id.txt_amount);
            mTxtDate = (TextView) viewItem.findViewById(R.id.txt_date);
            mTxtType = (TextView) viewItem.findViewById(R.id.txt_type);
            mTxtDesc = (TextView) viewItem.findViewById(R.id.txt_desc);
        }

        @Override
        public void onClick(View view) {
            final int selectedItemPosition = rv.getChildAdapterPosition(view);
            launchDetailActivity(fragmentToLaunch.ISSUE,selectedItemPosition, myMeds );

        }
    }

    //recycler view adapter class
    public class MedsRecyclerAdapter  extends  RecyclerView.Adapter<MedAdapterViewHolder> {

        ArrayList<Medicine> myMeds;
        Context ctx;
        public MedsRecyclerAdapter(ArrayList<Medicine> medData, Context ctx)

        {
            myMeds = medData;
            this.ctx = ctx;
        }
        @Override
        public MedAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            int layout = R.layout.med_card;
            LayoutInflater infater = LayoutInflater.from(context);
            boolean attachImmeaditely = false;

            View view = infater.inflate(layout, parent,attachImmeaditely);
            MedAdapterViewHolder myViewHolder = new MedAdapterViewHolder(view, myMeds);
            return myViewHolder;
        }

        @Override
        public void onViewRecycled(MedAdapterViewHolder holder) {
            holder.mCardView.setOnLongClickListener(null);
            super.onViewRecycled(holder);
        }

        @Override
        public void onBindViewHolder(final MedAdapterViewHolder holder, final int position) {
            Medicine md  = myMeds.get(position);
            holder.mTxtName.setText(md.getName().toUpperCase());
            holder.mTxtType.setText("Category:  "+ md.getType());
            holder.mTxtDate.setText("Expiry Date:  "+md.getExpirydate());
            holder.mTxtDesc.setText( "Description: "+md.getDesc());
            holder.mTxtAmount.setText("Amount: " +md.getAmount());
            medicineID = md.getId();
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // String currentValue = medicines[position];
                    Log.d("CardView", "CardView Clicked: "  );//+currentValue);
                    launchDetailActivity(fragmentToLaunch.ISSUE,position, myMeds );
                }
            });

            holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    launchDetailActivity(fragmentToLaunch.STOCK,position, myMeds );
                    return false;
                }
            });


        }

        @Override
        public int getItemCount() {

            return myMeds.size();
        }

        //used for searching
        public void setFilter(ArrayList<Medicine> newList){
            myMeds = new ArrayList<>();
            myMeds.addAll(newList);
            notifyDataSetChanged();
        }
    }

    //lauches specified fragment
    private void launchDetailActivity(HomeActivity.fragmentToLaunch ftl, int position, ArrayList<Medicine> medsList){
        //grab note information associated with whatever note item we clicked on
        Medicine med = medsList.get(position);

        //create intent that launches note detail activity
        Intent intent = new Intent(HomeActivity.this, ManipulateMedActivity.class);

        //pass along info of the note we clicked on the note detail activity
        intent.putExtra("medID", medicineID);
        intent.putExtra("expiredate", med.getExpirydate());
        intent.putExtra(HomeActivity.MEDICINE_NAME_EXTRA, med.getName());
        intent.putExtra(HomeActivity.MEDICINE_AMOUNT_EXTRA, med.getAmount());

        switch (ftl){
            case ISSUE:
                intent.putExtra(HomeActivity.MEDICINE_FRAGMENT_TO_LOAD_EXTRA, HomeActivity.fragmentToLaunch.ISSUE);
                break;
            case STOCK:
                intent.putExtra(HomeActivity.MEDICINE_FRAGMENT_TO_LOAD_EXTRA, HomeActivity.fragmentToLaunch.STOCK);
                break;
        }
        startActivity(intent);
    }

}
