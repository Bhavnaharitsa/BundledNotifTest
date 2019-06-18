package com.example.bundlednotif;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;
import com.example.bundlednotif.Constants;

import static com.example.bundlednotif.MainActivity.TAG;

public class MyBroadcastReceiver extends BroadcastReceiver {

    Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
    static int counter = 0;

    Intent intentAction;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
         Constants.counter++;

         inboxStyle.addLine("" + Constants.counter);

         intentAction = new Intent(context.getApplicationContext(), MyBroadcastReceiver.class);

         pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 1, intentAction, 0);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        inboxStyle.addLine("" + counter++);
        Notification notification = new Notification.Builder(context, "tring_id")
                .setContentTitle("5 New mails from ")
                .setContentText("Wtf")
                .setSmallIcon(android.R.drawable.star_on)
                .setStyle(inboxStyle)
                .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                .build();

        manager.notify(0, notification);

        Log.d(TAG, "modifyNotification: ");

        Toast.makeText(context.getApplicationContext(), "Pending intent fired", Toast.LENGTH_SHORT).show();
    }
}