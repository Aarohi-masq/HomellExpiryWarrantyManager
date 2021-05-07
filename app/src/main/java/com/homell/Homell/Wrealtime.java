package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class Wrealtime extends AppCompatActivity {
    private AdView madview;
    EditText editTextName, description;
    Button Buttonsub,eleccat,furcat,otherscat;
    private TextView btnChoose;
    ImageView datedrop,timedrop;
    private final int PICK_IMAGE_REQUEST = 22;

    private Uri filePath;
    Warranty warranty;

    Calendar c;
    ImageView titlebackadd;
    DatePicker pickerDate;
    TimePicker pickerTime ;
    FirebaseStorage storage;
    StorageReference storageReference;
    TextView tp,realbrand,date,time;
    private ImageView invoice;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final static int RQS_1 = 1;

    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference dbproducts = firebaseDatabase.getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrealtime);

        createNotificationChannel();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        madview = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        titlebackadd= (ImageView) findViewById(R.id.waddback);
        titlebackadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Wrealtime.this,Warrantylist.class);
                startActivity(intent);

            }
        });

        editTextName=(EditText) findViewById(R.id.wproductname);
        realbrand=(TextView)findViewById(R.id.wcateg);
        date=(TextView)findViewById(R.id.wmfgdate);
        time=(TextView) findViewById(R.id.wexpirytime);
        Buttonsub=(Button) findViewById(R.id.wsubbtn);
        description=(EditText)findViewById(R.id.wdesc);
        pickerTime=(TimePicker) findViewById(R.id.wpickertime);
        pickerDate=(DatePicker) findViewById(R.id.wpickerdate);
        eleccat=(Button) findViewById(R.id.wfoodexpbtn);
        furcat=(Button) findViewById(R.id.wmedexpbtn) ;
        otherscat=(Button) findViewById(R.id.wothersexpbtn);
        datedrop=(ImageView) findViewById(R.id.wexpdatedrop) ;
        timedrop=(ImageView) findViewById(R.id.wexptimedrop);
        invoice = findViewById(R.id.previnvoice);
        btnChoose = findViewById(R.id.wselefile);
        storage = FirebaseStorage.getInstance();
        warranty=new Warranty();
        storageReference = storage.getReference("Warranty Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();

            }
        });
        datedrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerDate.setVisibility(View.VISIBLE);
            }
        });
        timedrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerTime.setVisibility(View.VISIBLE);
                pickerDate.setVisibility(View.GONE);

            }
        });
        eleccat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Electronics");
            }
        });
        furcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Furniture");
            }
        });
        otherscat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Others");
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerDate.setVisibility(View.VISIBLE);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerTime.setVisibility(View.VISIBLE);
            }
        });
        Calendar now = Calendar.getInstance();
        long t=now.getTimeInMillis();
        pickerDate.setMinDate(t);

        pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);
        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));




        Buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(pickerDate.getYear(),
                        pickerDate.getMonth(),
                        pickerDate.getDayOfMonth()-7,
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                00);
                int year=pickerDate.getYear();
                int month=pickerDate.getMonth()+1;
                int day=pickerDate.getDayOfMonth();
                if (month<10)
                {
                    if(day<10)
                    {
                        String month1="0"+month;
                        String day1="0"+day;
                        date.setText(year + "/" +(month1) + "/"+day1);

                    }
                    else
                    {
                        String month1="0"+month;
                        date.setText(year + "/" +(month1) + "/"+day);
                    }

                }
                else
                {
                    if (day<10)
                    {
                        String day1="0"+day;
                        date.setText(year + "/" +(month) + "/"+day1);
                    }
                    else
                    {
                        date.setText(year + "/" +(month) + "/"+day);
                    }
                }




                if(cal.compareTo(current) <= 0){
                    date.setError("Invalid Date");
                    Toast.makeText(Wrealtime.this, "Please enter the date 7 days after", Toast.LENGTH_SHORT).show();
                    date.setText("");

                }else {
                    setAlarm(cal);

                    addProduct();
                }
            }
        });
    }

    public void onBackPressed()
    {
        Intent intent=new Intent(Wrealtime.this,Warrantylist.class);
        startActivity(intent);

    }
    private void setAlarm(Calendar targetCal){


        Intent intent = new Intent(getBaseContext(), ReminderBroadcast2.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Warranty Notifications";
            String description = "Dont Disable it otherwise Warranty Expiry Notification will not be delivered to you properly";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel1 = new NotificationChannel("WarrantyNotification", name, importance);
            channel1.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
        }
    }

    private void addProduct(){
        String Apname = editTextName.getText().toString().trim();
        String Apbrand = realbrand.getText().toString().trim();
        String Apdate = date.getText().toString().trim();

        String Apdesc = description.getText().toString().trim();
        warranty.setEditTextName(editTextName.getText().toString().trim());
        warranty.setRealbrand(realbrand.getText().toString().trim());
        warranty.setDate(date.getText().toString().trim());
        warranty.setProductdesc(description.getText().toString().trim());





        if(!TextUtils.isEmpty(Apname) && !TextUtils.isEmpty(Apdate)){
            String id=dbproducts.push().getKey();

            warranty.setProductId(id);
            dbproducts.child(id).setValue(warranty);
            Toast.makeText(this, "Product Added", Toast.LENGTH_LONG).show();
            editTextName.setText("");
            realbrand.setText("");
            date.setText("");
            btnChoose.setText("");
            pickerDate.setVisibility(View.GONE);
            pickerTime.setVisibility(View.GONE);
            //    month.setText("");
            description.setText("");
        }else{
            Toast.makeText(this, "You should enter name and warranty period", Toast.LENGTH_LONG).show();
        }
    }
    private void SelectImage()
    {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);


        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {


            filePath = data.getData();
            try {

                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                invoice.setImageBitmap(bitmap);
                uploadImage();
            }

            catch (IOException e) {
                btnChoose.setError("File size must be less than 5 MB");
                // Log the exception
                e.printStackTrace();
            }
        }
    }
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final String s= UUID.randomUUID().toString();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child(s);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(Wrealtime.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded

                            progressDialog.dismiss();
                            Toast
                                    .makeText(Wrealtime.this,
                                            "Image size must be less than 5 MB " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                    btnChoose.setText(s);
                                    warranty.setImgpath(s);
                                }
                            });
        }

    }
}
