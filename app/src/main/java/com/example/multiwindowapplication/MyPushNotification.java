package com.example.multiwindowapplication;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class MyPushNotification {
    private static final String CHANNEL_ID = "Base channel";
    private static final String CHANNEL_DESCRIPTION = "it's a base channel";
    private static final int NOTIFY_ID = 123;

    private final NotificationManager notificationManager;
    private final Context context;

    public MyPushNotification(Context context, NotificationManager manager) {
        this.notificationManager = manager;
        this.context = context;
        createNotificationChannel();
    }

    /** Отправка с id по умолчанию (как было раньше). */
    public void sendNotify(String title, String text) {
        sendNotify(NOTIFY_ID, title, text);
    }

    /** Отправка с собственным id, чтобы уведомления не затирали друг друга. */
    public void sendNotify(int id, String title, String text) {
        // На Android 13+ без разрешения уведомление не покажется
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(context,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_baseline_blender_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(id, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // IMPORTANCE_HIGH — «горячие» (всплывающие) уведомления
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
