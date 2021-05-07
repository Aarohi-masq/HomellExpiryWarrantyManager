package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class Wshowdata extends AppCompatActivity {
    private AdView madvieww;
    String _NAME, _CATEGORY, _MONTH, _DATE, _DESC,_ID,_IMG;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    TextView editTextName, productdesc, date;
    ImageView imgpath,wshowback;
    TextView realbrand,greyinvoice,greyabout;
    RelativeLayout aboutselected,invoiceselected;
    LinearLayout aboutlayout,invoicelayout;


    FirebaseStorage storage= FirebaseStorage.getInstance();
    StorageReference ref =storage.getReferenceFromUrl("gs://homell-org.appspot.com/Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Wshowdata.this, Warrantylist.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wshowdata);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        madvieww = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        madvieww.loadAd(adRequest);

        editTextName = (TextView) findViewById(R.id.wshwproductname);
        imgpath =(ImageView) findViewById(R.id.previnvoiceshw);
        productdesc = (TextView) findViewById(R.id.wdescshw);
        date = (TextView) findViewById(R.id.wmfgdateshw);
        realbrand = (TextView) findViewById(R.id.wshwcateg);


        greyabout= (TextView) findViewById(R.id.aboutsele);
        greyinvoice= (TextView) findViewById(R.id.invoicesele);
       aboutselected= (RelativeLayout) findViewById(R.id.aboutselected);
        invoiceselected= (RelativeLayout) findViewById(R.id.invoiceselected);
        invoicelayout= (LinearLayout) findViewById(R.id.invoicelayout);
        aboutlayout= (LinearLayout) findViewById(R.id.aboutlayout);
        wshowback= (ImageView) findViewById(R.id.wshowback);
        wshowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Wshowdata.this,Warrantylist.class);
                startActivity(intent);

            }
        });

        greyabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutselected.setVisibility(View.VISIBLE);
                greyinvoice.setVisibility(View.VISIBLE);
                invoiceselected.setVisibility(View.GONE);
                greyabout.setVisibility(View.GONE);
                aboutlayout.setVisibility(View.VISIBLE);
                invoicelayout.setVisibility(View.GONE);

            }
        });
        greyinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invoiceselected.setVisibility(View.VISIBLE);
                aboutselected.setVisibility(View.GONE);
                greyabout.setVisibility(View.VISIBLE);
                greyinvoice.setVisibility(View.GONE);
                aboutlayout.setVisibility(View.GONE);
                invoicelayout.setVisibility(View.VISIBLE);
            }
        });





        Bundle intent =getIntent().getExtras();
        _NAME= intent.getString("editTextName");
        _CATEGORY= intent.getString("realbrand");
        _MONTH= intent.getString("productmonth");
        _DATE= intent.getString("date");
        _DESC= intent.getString("productdesc");
        _ID =intent.getString("productId");
        _IMG=intent.getString("imgpath");

        try {

            final File file = File.createTempFile("images", "jpg");

            if(_IMG!=null) {
                ref.child(_IMG).getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        imgpath.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
            }
            else{

            }
        }catch (IOException e)
        {

        }

        editTextName.setText(_NAME);

        productdesc.setText(_DESC);

        date.setText(_DATE);
        realbrand.setText("in "+""+_CATEGORY);

    }
    private void showProductDetails(){
        Intent intent =getIntent();


    }

    public void download(View view) {
        if(_IMG!=null){
            ref.child(_IMG).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String url=uri.toString();
                    downloadfiles(Wshowdata.this,_IMG,".jpg",DIRECTORY_DOWNLOADS,url);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Wshowdata.this,"Opss...Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
    public  void downloadfiles(Context context,String fileName,String fileExtension,String destinationDirectory,String url)
    {
        DownloadManager downloadManager=(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName+fileExtension);
        downloadManager.enqueue(request);

    }
}