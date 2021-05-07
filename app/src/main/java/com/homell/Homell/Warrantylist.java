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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Warrantylist extends AppCompatActivity {
    private AdView mAdView;
    ArrayList<Warranty> list;
    WAdapter adapter;
    TextView nodata4,backtitleexp;
    RecyclerView recyclerView;
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
    String currentDate = sdf.format(new Date());



    Query mQuery=ref.orderByChild("date");

    SearchView searchView;
    ImageView abtn;
    ImageView imageview2;




    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent(Warrantylist.this, home.class);

        startActivity(mIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warrantylist);

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

        recyclerView=(RecyclerView) findViewById(R.id.wexpiryrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        abtn=(ImageView) findViewById(R.id.wabtn);
        imageview2=(ImageView) findViewById(R.id.imageView2);
        backtitleexp=(TextView) findViewById(R.id.textView7);
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Warrantylist.this,home.class);
                startActivity(intent);
            }
        });
        nodata4=(TextView) findViewById(R.id.nodata4);
        list = new ArrayList<Warranty>();
        searchView= findViewById(R.id.wsearchview);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

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
                Intent intent=new Intent(Warrantylist.this,Wrealtime.class);
                startActivity(intent);
            }
        });

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Warranty e= dataSnapshot1.getValue(Warranty.class);
                    list.add(e);
                }
                adapter =new WAdapter(Warrantylist.this,list);
                if(list.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    nodata4.setVisibility(View.VISIBLE);
                }
                else{
                    recyclerView.setAdapter(adapter);
                    nodata4.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Warrantylist.this,"Opss...Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        if (searchView!=null){
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
    }
    private void search(String str) {
        ArrayList<Warranty> mlist=new ArrayList<>();
        for (Warranty object : list)
        {
            if(object.getEditTextName().toLowerCase().contains(str.toLowerCase())){
                mlist.add(object);
            }
        }
        WAdapter adapter1 =new WAdapter(Warrantylist.this,mlist);
        recyclerView.setAdapter(adapter1);
    }
}
