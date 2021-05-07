package com.homell.Homell;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.FirebaseDatabase;

public class ReminderBroadcast1 extends BroadcastReceiver {
    @Override

    public void onReceive(final Context context, Intent intent) {
        Intent myIntent=new Intent(context,home.class);



        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ExpiryNotification")
                .setSmallIcon(R.drawable.ic_skylight_notification)
                .setContentTitle("Expiry Approaching")
        .setLargeIcon(BitmapFactory. decodeResource (context.getResources(), R.drawable. icon ))

                .setContentText("Your Product is expiring soon")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        builder.setOngoing(true);
        builder.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmsound);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        builder.setVibrate(pattern);
        builder.setContentIntent(PendingIntent.getActivity(context,0,myIntent,0));
        final NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(240,builder.build());

        Handler h = new Handler();
        long delayInMilliseconds = 604800000;
        h.postDelayed(new Runnable() {
            public void run() {
                notificationManager.cancel(240);
            }
        }, delayInMilliseconds);


    }


    }

