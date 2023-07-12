package com.example.penguin_project.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.penguin_project.R;
import com.example.penguin_project.view.activity.AddTodoActivity;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Retrieve the user input from the intent extras
        String todoName = intent.getStringExtra("todoName");
        String description = intent.getStringExtra("description");

        String notificationType = intent.getStringExtra("type");
        // Create an intent for the notification
        Intent notificationIntent = new Intent(context, AddTodoActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (notificationType.equals("dueTime")) {
            pendingIntent = PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        } else if (notificationType.equals("reminder")) {
            pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1")
                .setSmallIcon(R.drawable.icon_sun)
                .setContentTitle(todoName)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Notify the notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }

        Toast.makeText(context, "Notification received", Toast.LENGTH_SHORT).show();
    }
}
