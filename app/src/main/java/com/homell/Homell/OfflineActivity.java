package com.homell.Homell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OfflineActivity extends AppCompatActivity {
    Button tryagainbutton;
    private BroadcastReceiver OfflineReceiver2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offlineactivity);
        tryagainbutton= (Button) findViewById(R.id.tryagain);
        OfflineReceiver2 = new OfflineReceiver2();

        tryagainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcastIntent();
            }
        });
    }
    public void broadcastIntent() {
        registerReceiver(OfflineReceiver2, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
   /* @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(OfflineReceiver2);
    }*/
}
