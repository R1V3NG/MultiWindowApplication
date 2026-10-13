package com.example.multiwindowapplication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_NOTIFICATIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestNotificationPermission();

        findViewById(R.id.btnMorning).setOnClickListener(v -> open(MorningActivity.class));
        findViewById(R.id.btnDay).setOnClickListener(v -> open(DayActivity.class));
        findViewById(R.id.btnEvening).setOnClickListener(v -> open(EveningActivity.class));
        findViewById(R.id.btnNight).setOnClickListener(v -> open(NightActivity.class));
    }

    private void open(Class<?> target) {
        startActivity(new Intent(this, target));
    }

    // Android 13+ (API 33): разрешение на уведомления нужно запрашивать явно
    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATIONS);
        }
    }
}
