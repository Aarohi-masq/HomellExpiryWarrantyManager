package com.homell.Homell;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.BroadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;

import android.net.ConnectivityManager;

import android.os.Bundle;

import android.os.Handler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Locale;



public class home extends AppCompatActivity {



    private FirebaseAuth firebaseAuth;
    private ImageView logout, warranty, expiry;
    private Button addP, note, notetim;

    TextView dispdate, textview01, textview02;
    TextView dispmonth;
    RelativeLayout relativeLayout;
    TextView homename, hwarview, hexpview;
    NavigationView navigationView;
    ProgressBar progressBar;
    DatabaseReference ref;
    LinearLayout navopen, navhomlay;
    Button showname;
    ScrollView scrllview;
    ImageView navtapexit, navpass,rateus,contactus;

    SearchView searchView;

    ArrayList<Expire> elist;
    hAdapter eadapter;
    RecyclerView erecyclerView;
    DatabaseReference eref = FirebaseDatabase.getInstance().getReference("Expiry Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    Query meQuery = eref.limitToFirst(5).orderByChild("date");

    ArrayList<Warranty> wlist;
    hWAdapter wadapter;
    RecyclerView wrecyclerView;
    DatabaseReference wref = FirebaseDatabase.getInstance().getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    Query mQuery = wref.limitToFirst(5).orderByChild("date");

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click Back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

   private BroadcastReceiver OfflineReceiver1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();




        OfflineReceiver1 = new OfflineReceiver1();
        broadcastIntent();


        textview01 = (TextView) findViewById(R.id.textview01);
        scrllview = (ScrollView) findViewById(R.id.scrllview);

        progressBar = (ProgressBar) findViewById(R.id.proghome);
        relativeLayout = (RelativeLayout) findViewById(R.id.relprog);
        textview02 = (TextView) findViewById(R.id.textview02);
        navpass = (ImageView) findViewById(R.id.navpass);
        navpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,changepass.class);
                startActivity(intent);
            }
        });

        searchView = (SearchView) findViewById(R.id.hsearchView);

        erecyclerView = (RecyclerView) findViewById(R.id.hexplist);
        LinearLayoutManager ehorizontalLayoutManagaer = new LinearLayoutManager(home.this, LinearLayoutManager.HORIZONTAL, false);
        erecyclerView.setLayoutManager(ehorizontalLayoutManagaer);
        elist = new ArrayList<Expire>();

        navigationView = (NavigationView) findViewById(R.id.navellipes);
        navigationView.setClickable(true);
        contactus = (ImageView) findViewById(R.id.contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, com.homell.Homell.contactus.class);
                startActivity(intent);
            }
        });

        navopen = (LinearLayout) findViewById(R.id.navopen);
        navhomlay = (LinearLayout) findViewById(R.id.navhomlay);
        navtapexit = (ImageView) findViewById(R.id.navexittap);
        rateus = (ImageView) findViewById(R.id.rateus);
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navigationView.setVisibility(View.GONE);
                Intent intent = new Intent(home.this,homeredirect.class);
                startActivity(intent);





                }
        });

        wrecyclerView = (RecyclerView) findViewById(R.id.hwarlist);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(home.this, LinearLayoutManager.HORIZONTAL, false);
        wrecyclerView.setLayoutManager(horizontalLayoutManagaer);
        wlist = new ArrayList<Warranty>();

        homename = (TextView) findViewById(R.id.homename);


        dispmonth = (TextView) findViewById(R.id.homemonth);
        dispdate = (TextView) findViewById(R.id.homedate);

        hexpview = (TextView) findViewById(R.id.hexpview);
        hwarview = (TextView) findViewById(R.id.hwarview);

        navopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setVisibility(View.VISIBLE);

            }
        });
        homename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setVisibility(View.VISIBLE);

            }
        });
        navtapexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setVisibility(View.GONE);

            }
        });
        navhomlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationView.setVisibility(View.GONE);

            }
        });
        hexpview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, expirylist.class);
                startActivity(intent);
            }
        });
        hwarview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(home.this, Warrantylist.class);
                startActivity(i1);
            }
        });

        final LoadingAlertDialog d2 = new LoadingAlertDialog(home.this);
        Calendar c = Calendar.getInstance();



        String currentDate = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String monthname = month.format(c.getTime());


        dispdate.setText(currentDate);
        dispmonth.setText(monthname);
        firebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                String refname = dataSnapshot2.child("uname").getValue().toString();
                if (homename.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    scrllview.setClickable(false);
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.GONE);
                    scrllview.setClickable(true);
                }
                homename.setText(refname);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        logout = (ImageView) findViewById(R.id.lout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(home.this, Login.class));
            }
        });
        meQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Expire e = dataSnapshot1.getValue(Expire.class);
                    elist.add(e);
                }
                eadapter = new hAdapter(home.this, elist);
                if (elist.isEmpty()) {
                    erecyclerView.setVisibility(View.GONE);
                    textview02.setVisibility(View.VISIBLE);
                }
                erecyclerView.setAdapter(eadapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(home.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Warranty e = dataSnapshot1.getValue(Warranty.class);
                    wlist.add(e);
                }
                wadapter = new hWAdapter(home.this, wlist);
                if (wlist.isEmpty()) {
                    wrecyclerView.setVisibility(View.GONE);
                    textview01.setVisibility(View.VISIBLE);
                }
                wrecyclerView.setAdapter(wadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(home.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

            }
        });
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }


        expiry = (ImageView) findViewById(R.id.expbtn);
        expiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2.loadingdialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        d2.dismissDialog();
                    }
                }, 2000);
                Intent intent = new Intent(home.this, expirylist.class);
                startActivity(intent);

            }
        });
        warranty = (ImageView) findViewById(R.id.warrhomebtn);
        warranty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2.loadingdialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        d2.dismissDialog();
                    }
                }, 2000);

                Intent intent = new Intent(home.this, Warrantylist.class);
                startActivity(intent);

            }
        });


    }



    private void search(String str) {
        ArrayList<Expire> mlist = new ArrayList<>();
        for (Expire object : elist) {
            if (object.getEditTextName().toLowerCase().contains(str.toLowerCase())) {
                mlist.add(object);
            }
        }
        hAdapter adapter1 = new hAdapter(home.this, mlist);
        erecyclerView.setAdapter(adapter1);
        ArrayList<Warranty> wmlist = new ArrayList<>();
        for (Warranty object1 : wlist) {
            if (object1.getEditTextName().toLowerCase().contains(str.toLowerCase())) {
                wmlist.add(object1);
            }
        }
        hWAdapter wadapter1 = new hWAdapter(home.this, wmlist);
        wrecyclerView.setAdapter(wadapter1);
    }

    public void broadcastIntent() {
        registerReceiver(OfflineReceiver1, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /*@Override
    protected void onPause() {
        super.onPause();
       unregisterReceiver(OfflineReceiver1);
    }*/


}


