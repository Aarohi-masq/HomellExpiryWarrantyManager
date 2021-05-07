package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class expirylist extends AppCompatActivity {
    private AdView mAdView;

    ArrayList<Expire> list;
    Adapter adapter;
    RelativeLayout tcheck;
    RecyclerView recyclerView;
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Expiry Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    SearchView searchView;
    ImageView abtn,imageviewwlist;
    ImageView sbtn;
    TextView nodata5,backtitleexp,textView8;
    Query mQuery1=ref.orderByChild("date");


    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(expirylist.this,home.class);

        startActivity(mIntent);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expirylist);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
            }
        }, 6000);




        tcheck=(RelativeLayout) findViewById(R.id.tcheck);


        recyclerView=(RecyclerView) findViewById(R.id.expiryrecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        abtn=(ImageView) findViewById(R.id.abtn);

        list = new ArrayList<Expire>();
        nodata5=(TextView) findViewById(R.id.nodata5);
        backtitleexp=(TextView) findViewById(R.id.textView8);
        imageviewwlist=(ImageView) findViewById(R.id.imageViewwlist);
        imageviewwlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expirylist.this,home.class);
                startActivity(intent);

            }
        });
        searchView= findViewById(R.id.searchview);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
// searchview closed
                backtitleexp.setVisibility(View.VISIBLE);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtitleexp.setVisibility(View.INVISIBLE);
            }
        });
        abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expirylist.this,realtime.class);
                startActivity(intent);
            }
        });

         mQuery1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Expire e= dataSnapshot1.getValue(Expire.class);
                        list.add(e);
                    }
                    adapter =new Adapter(expirylist.this,list);
                    if(list.isEmpty()){
                        recyclerView.setVisibility(View.GONE);
                        nodata5.setVisibility(View.VISIBLE);
                    }
                    else{
                        recyclerView.setAdapter(adapter);
                        nodata5.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(expirylist.this,"Opss...Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        if (searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
            {

                @Override
                public boolean onQueryTextSubmit(String query)
                {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);

                    return true;

                }

            });
        }

    }

    private void search(String str) {
        ArrayList<Expire> mlist=new ArrayList<>();
        for (Expire object : list)
        {
            if(object.getEditTextName().toLowerCase().contains(str.toLowerCase())){
                mlist.add(object);
            }
        }
        Adapter adapter1 =new Adapter(expirylist.this,mlist);
        recyclerView.setAdapter(adapter1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
