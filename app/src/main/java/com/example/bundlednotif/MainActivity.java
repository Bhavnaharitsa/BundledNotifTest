package com.example.bundlednotif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.bundlednotif.MainActivity.TAG;

public class MainActivity extends AppCompatActivity {
    Button button;
    NotificationManagerCompat manager;
    public static final String TAG = MainActivity.class.getSimpleName();
    PendingIntent pendingIntent;

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

         pendingIntent = PendingIntent.getBroadcast(this, 1, intentAction, 0);

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
                .addAction(android.R.drawable.btn_minus, "Action 1", pendingIntent)
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

    public PendingIntent getPendingIntent(){
        return pendingIntent;
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




