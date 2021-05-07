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
import androidx.core.content.ContextCompat;

public class ReminderBroadcast2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent=new Intent(context,home.class);



        final NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context, "WarrantyNotification")
                .setSmallIcon(R.drawable.ic_skylight_notification)
                .setContentTitle("Warranty Approaching")
                .setContentText("Your Product warranty is expiring soon")
                .setLargeIcon(BitmapFactory. decodeResource (context.getResources(), R.drawable. icon ))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        builder1.setOngoing(true);
        builder1.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder1.setSound(alarmsound);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        builder1.setVibrate(pattern);
        builder1.setContentIntent(PendingIntent.getActivity(context,0,myIntent,0));
        final NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(250,builder1.build());

        Handler h = new Handler();
        long delayInMilliseconds = 604800000;
        h.postDelayed(new Runnable() {
            public void run() {
                notificationManager.cancel(250);
            }
        }, delayInMilliseconds);
    }
}
