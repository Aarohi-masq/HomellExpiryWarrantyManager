package com.homell.Homell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Weditproduct extends AppCompatActivity {
    private AdView madview;
    String _NAME, _CATEGORY, _MONTH, _DATE, _DESC,_ID;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    EditText editTextName, productdesc, date;
    ImageView weaddback;

    TextView realbrand;
    Button update,editeleccat,editfurcat,editotherscat;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Weditproduct.this, Warrantylist.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weditproduct);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        madview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);
        editTextName = (EditText) findViewById(R.id.editproductname);
        //productmonth =(EditText) findViewById(R.id.editexpirymonth);
        productdesc = (EditText) findViewById(R.id.editdesc);
        date = (EditText) findViewById(R.id.editmfgdate);
        realbrand = (TextView) findViewById(R.id.editcateg);
        update = (Button) findViewById(R.id.editsubbtn);
        editeleccat = (Button) findViewById(R.id.editfoodexpbtn);
        editfurcat = (Button) findViewById(R.id.editmedexpbtn);
        editotherscat = (Button) findViewById(R.id.editothersexpbtn);
        weaddback=(ImageView) findViewById(R.id.wedaddback);
        weaddback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Weditproduct.this,Warrantylist.class);
                startActivity(intent);
            }
        });

        editeleccat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Electronics");
            }
        });
        editfurcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Furniture");
            }
        });
        editotherscat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Others");
            }
        });
        showProductDetails();


    }
    private void showProductDetails(){
        Intent intent =getIntent();
        _NAME= intent.getStringExtra("editTextName");
        _CATEGORY= intent.getStringExtra("realbrand");
        _MONTH= intent.getStringExtra("productmonth");
        _DATE= intent.getStringExtra("date");
        _DESC= intent.getStringExtra("productdesc");
        _ID =intent.getStringExtra("productId");

        editTextName.setText(_NAME);

        productdesc.setText(_DESC);

        date.setText(_DATE);
        realbrand.setText(_CATEGORY);

    }



    public void update(View view){


        if(isNameChanged() || isCategoryChanged() || isMFGChanged() || isDescChanged()){
            Toast.makeText(Weditproduct.this,"Updated, Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Weditproduct.this, Warrantylist.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(Weditproduct.this,"Updated, Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isDescChanged() {
        if(!_DESC.equals(productdesc.getText().toString())){
            databaseReference.child(_ID).child("productdesc").setValue(productdesc.getText().toString());
            return isMFGChanged();
        }
        else{
            return false;
        }
    }



    private boolean isMFGChanged() {
        if(!_DATE.equals(date.getText().toString())){
            databaseReference.child(_ID).child("date").setValue(date.getText().toString());
            return isCategoryChanged();
        }
        else{
            return false;
        }
    }

    private boolean isCategoryChanged() {
        if(!_CATEGORY.equals(realbrand.getText().toString())){
            databaseReference.child(_ID).child("realbrand").setValue(realbrand.getText().toString());
            return isNameChanged();
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!_NAME.equals(editTextName.getText().toString())){
            databaseReference.child(_ID).child("editTextName").setValue(editTextName.getText().toString());
            return isDescChanged();
        }
        else{
            return false;
        }
    }

}
