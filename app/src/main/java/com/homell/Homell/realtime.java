package com.homell.Homell;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.homell.Homell.R;

import java.util.Calendar;


public class realtime extends AppCompatActivity {
    private AdView maadview;
    EditText editTextName, description;
    Button Buttonsub,foodcat,medicinecat,otherscat;
    ImageView datedrop,timedrop,addback;

    Calendar c;
    DatePicker pickerDate;
    TimePicker pickerTime ;
    TextView tp,realbrand,date,time;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final static int RQS_1 = 1;

    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference dbproducts = firebaseDatabase.getReference("Expiry Items").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);
        createNotificationChannel();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        maadview = findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder().build();
        maadview.loadAd(adRequest);


        editTextName=(EditText) findViewById(R.id.productname);
        realbrand=(TextView)findViewById(R.id.categ);
        date=(TextView)findViewById(R.id.mfgdate);
        time=(TextView) findViewById(R.id.expirytime);
        Buttonsub=(Button) findViewById(R.id.subbtn);
        description=(EditText)findViewById(R.id.desc);
        pickerTime=(TimePicker) findViewById(R.id.pickertime);
        pickerDate=(DatePicker) findViewById(R.id.pickerdate);
        foodcat=(Button) findViewById(R.id.foodexpbtn);
        medicinecat=(Button) findViewById(R.id.medexpbtn) ;
        otherscat=(Button) findViewById(R.id.othersexpbtn);
        datedrop=(ImageView) findViewById(R.id.expdatedrop) ;
        timedrop=(ImageView) findViewById(R.id.exptimedrop);
        addback= (ImageView) findViewById(R.id.addback);
        addback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(realtime.this,expirylist.class);
                startActivity(intent);

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
        foodcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Food");
            }
        });
        medicinecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realbrand.setText("Medicine");
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
                    Toast.makeText(realtime.this, "Please enter the date 7 days after", Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(realtime.this,expirylist.class);
        startActivity(intent);

    }
    private void setAlarm(Calendar targetCal){


        Intent intent = new Intent(getBaseContext(), ReminderBroadcast1.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Expiry Notifications";
            String description = "Dont Disable it otherwise Expiry Notification will not be delivered to you properly";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("ExpiryNotification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addProduct(){
        String Apname = editTextName.getText().toString().trim();
        String Apbrand = realbrand.getText().toString().trim();
      String Apdate = date.getText().toString().trim();

        String Apdesc = description.getText().toString().trim();



        if(!TextUtils.isEmpty(Apname) && !TextUtils.isEmpty(Apbrand)){
            String id=dbproducts.push().getKey();
            Products product = new Products( Apname, Apbrand, Apdate, id, Apdesc);
            dbproducts.child(id).setValue(product);
            Toast.makeText(this, "Product Added", Toast.LENGTH_LONG).show();
            editTextName.setText("");
            realbrand.setText("");
            date.setText("");
            pickerDate.setVisibility(View.GONE);
            pickerTime.setVisibility(View.GONE);

            description.setText("");
        }else{
            Toast.makeText(this, "You should enter name and category", Toast.LENGTH_LONG).show();
        }
    }

}
