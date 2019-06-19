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

    int SUMMARY_ID = 0;
    String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";
    int emailNotificationId1 = 2;
    int emailNotificationId2 = 3;

    NotificationManagerCompat manager;

    @Override
    public void onReceive(Context context, Intent intent) {
       if(intent.getAction().equals("important")) {
            Constants.counter++;

            inboxStyle.addLine("" + Constants.counter);

            intentAction = new Intent(context.getApplicationContext(), MyBroadcastReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 1, intentAction, 0);

           Notification important1 =
                   new NotificationCompat.Builder(context.getApplicationContext(), "tring_id")
                           .setSmallIcon(android.R.drawable.ic_dialog_email)
                           .setContentTitle("Important 1")
                           .setContentText(":/...")
                           .setGroup(GROUP_KEY_WORK_EMAIL)
                           .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                           .addAction(android.R.drawable.btn_plus, "News and Alerts", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .addAction(android.R.drawable.btn_star, "Others", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .build();

           Notification important2 =
                   new NotificationCompat.Builder(context.getApplicationContext(), "tring_id")
                           .setSmallIcon(android.R.drawable.ic_dialog_email)
                           .setContentTitle("Email 2")
                           .setContentText(".-._....")
                           .setGroup(GROUP_KEY_WORK_EMAIL)
                           .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                           .addAction(android.R.drawable.btn_plus, "News and Alerts", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .addAction(android.R.drawable.btn_star, "Others", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .build();

            manager = NotificationManagerCompat.from(context.getApplicationContext());
            inboxStyle.addLine("" + counter++);

            Notification notification = new Notification.Builder(context, "tring_id")
                    .setContentTitle("5 New mails from ")
                    .setContentText("Wtf")
                    .setSmallIcon(android.R.drawable.star_on)
                    .setGroup(GROUP_KEY_WORK_EMAIL)
                    .setGroupSummary(true)
                    .build();

            manager.notify(1, important1);
            manager.notify(2, important2);
            manager.notify(0, notification);

            Log.d(TAG, "modifyNotification: ");

            Toast.makeText(context.getApplicationContext(), "Pending intent fired", Toast.LENGTH_SHORT).show();
        }

       else if(intent.getAction().equals("news")){


           Notification newsAndAlerts1 =
                   new NotificationCompat.Builder(context.getApplicationContext(), "tring_id")
                           .setSmallIcon(android.R.drawable.ic_dialog_email)
                           .setContentTitle("News 1")
                           .setContentText("Okay...")
                           .setGroup(GROUP_KEY_WORK_EMAIL)
                           .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                           .addAction(android.R.drawable.btn_plus, "News and Alerts", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .addAction(android.R.drawable.btn_star, "Others", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .build();

           Notification newsAndAlerts2 =
                   new NotificationCompat.Builder(context.getApplicationContext(), "tring_id")
                           .setSmallIcon(android.R.drawable.ic_dialog_email)
                           .setContentTitle("News 2")
                           .setContentText("Okay again...")
                           .setGroup(GROUP_KEY_WORK_EMAIL)
                           .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                           .addAction(android.R.drawable.btn_plus, "News and Alerts", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .addAction(android.R.drawable.btn_star, "Others", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .build();

           manager = NotificationManagerCompat.from(context.getApplicationContext());

           Notification notification = new Notification.Builder(context, "tring_id")
                   .setContentTitle("5 New mails from ")
                   .setContentText("Wtf")
                   .setSmallIcon(android.R.drawable.star_on)
                   .setGroup(GROUP_KEY_WORK_EMAIL)
                   .setGroupSummary(true)
                   .build();
           manager.notify(3, newsAndAlerts1);
           manager.notify(4, newsAndAlerts2);
           manager.notify(0, notification);

           Toast.makeText(context.getApplicationContext(), "News Clicked", Toast.LENGTH_SHORT).show();


       }

       else if(intent.getAction().equals("others")){
           Notification others1 =
                   new NotificationCompat.Builder(context.getApplicationContext(), "tring_id")
                           .setSmallIcon(android.R.drawable.ic_dialog_email)
                           .setContentTitle("Others 1")
                           .setContentText("Dude...")
                           .setGroup(GROUP_KEY_WORK_EMAIL)
                           .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                           .addAction(android.R.drawable.btn_plus, "News and Alerts", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .addAction(android.R.drawable.btn_star, "Others", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .build();

           Notification others2 =
                   new NotificationCompat.Builder(context.getApplicationContext(), "tring_id")
                           .setSmallIcon(android.R.drawable.ic_dialog_email)
                           .setContentTitle("Others 2")
                           .setContentText("Office mat ja yaar :( ...")
                           .setGroup(GROUP_KEY_WORK_EMAIL)
                           .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
                           .addAction(android.R.drawable.btn_plus, "News and Alerts", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .addAction(android.R.drawable.btn_star, "Others", PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(), 0))
                           .build();

           manager = NotificationManagerCompat.from(context.getApplicationContext());

           Notification notification = new Notification.Builder(context, "tring_id")
                   .setContentTitle("5 New mails from ")
                   .setContentText("Wtf")
                   .setSmallIcon(android.R.drawable.star_on)
                   .setGroup(GROUP_KEY_WORK_EMAIL)
                   .setGroupSummary(true)
                   .setStyle(new Notification.BigTextStyle()
                           .bigText("TEXT"))
                   .build();

           manager.notify(5, others1);
           manager.notify(6, others2);
           manager.notify(0, notification);

           Toast.makeText(context.getApplicationContext(), "Others Clicked", Toast.LENGTH_SHORT).show();
       }
    }
}