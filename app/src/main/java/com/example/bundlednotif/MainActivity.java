package com.example.bundlednotif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    NotificationManagerCompat manager;
    public static final String TAG = MainActivity.class.getSimpleName();
    PendingIntent pendingIntentImportant;
    PendingIntent pendingIntentNews;
    PendingIntent pendingIntentOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        button = findViewById(R.id.button);

        manager = NotificationManagerCompat.from(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBundledNotification();
            }
        });

    }

    private void createBundledNotification(){
        //This is the intent of PendingIntent
        Intent intentAction = new Intent(this, MyBroadcastReceiver.class);
        intentAction.setAction("important");

        Intent intentNews = new Intent(this, MyBroadcastReceiver.class);
        intentNews.setAction("news");

        Intent intentOthers = new Intent(this, MyBroadcastReceiver.class);
        intentOthers.setAction("others");

        pendingIntentImportant = PendingIntent.getBroadcast(this, 1, intentAction, 0);
        pendingIntentNews = PendingIntent.getBroadcast(this, 2, intentNews, 0);
        pendingIntentOthers = PendingIntent.getBroadcast(this, 3, intentOthers, 0);

        NotificationCompat.Builder regularBuilder = new NotificationCompat.Builder(this, "tring_id")
                .setContentTitle("TEST")
                .setContentText("TEST TOO!")
                .setSmallIcon(android.R.drawable.star_on)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis());

        NotificationCompat.Builder summaryBuilder = new NotificationCompat.Builder(this, "tring_id")
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setSubText("Something")
                .setGroup("Group")
                .setGroupSummary(true)
                .setSmallIcon(android.R.drawable.star_on);

        Notification notification = new Notification.Builder(this, "tring_id")
                .setContentTitle("5 New mails from ")
                .setContentText("Wtf")
                .setSmallIcon(android.R.drawable.star_on)
                .setStyle(new Notification.InboxStyle()
                        .addLine("Test1")
                        .addLine("Test2")
                        .setSummaryText("+3 more"))
                .addAction(android.R.drawable.btn_minus, "Important", pendingIntentImportant)
                .addAction(android.R.drawable.btn_plus, "News and Alerts", pendingIntentNews)
                .addAction(android.R.drawable.btn_star, "Others", pendingIntentOthers)
                .setOngoing(true)
                .build();


        boolean isBundledNotification = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
        if(isBundledNotification){
            //publish notifications
//            manager.notify(0, regularBuilder.build());
//            manager.notify(1, summaryBuilder.build());
//            manager.notify(2, summaryBuilder.build());
            manager.notify(0, notification);
        }
        else{
            manager.notify(0, regularBuilder.build());
        }

    }

    public PendingIntent getPendingIntentImportant(){
        return pendingIntentImportant;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("tring_id", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}




