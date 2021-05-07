package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;

public class showdata extends AppCompatActivity {
    private AdView madvieew;
    String _NAME, _CATEGORY, _MONTH, _DATE, _DESC,_ID;

    TextView editTextName, productdesc, date;
    ImageView showback;
    TextView realbrand,greyinvoice,greyabout;
    RelativeLayout aboutselected,invoiceselected;
    LinearLayout aboutlayout,invoicelayout;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(showdata.this, expirylist.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        madvieew = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        madvieew.loadAd(adRequest);
        editTextName = (TextView) findViewById(R.id.shwproductname);
        productdesc = (TextView) findViewById(R.id.descshw);
        date = (TextView) findViewById(R.id.mfgdateshw);
        realbrand = (TextView) findViewById(R.id.shwcateg);

        showback = (ImageView) findViewById(R.id.showback);
        showback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(showdata.this,expirylist.class);
                startActivity(intent);

            }
        });





        Bundle intent =getIntent().getExtras();
        _NAME= intent.getString("editTextName");
        _CATEGORY= intent.getString("realbrand");
        _MONTH= intent.getString("productmonth");
        _DATE= intent.getString("date");
        _DESC= intent.getString("productdesc");
        _ID =intent.getString("productId");


        editTextName.setText(_NAME);

        productdesc.setText(_DESC);

        date.setText(_DATE);
        realbrand.setText("in "+""+_CATEGORY);

    }
}
