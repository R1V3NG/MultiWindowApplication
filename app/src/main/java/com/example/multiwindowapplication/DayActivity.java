package com.example.multiwindowapplication;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        // Пуш: скоро конец рабочего дня
        MyPushNotification push = new MyPushNotification(this,
                getSystemService(NotificationManager.class));
        push.sendNotify(2, getString(R.string.push_day_title), getString(R.string.push_day_text));

        // Далее -> экран «Вечер»
        findViewById(R.id.btnNext).setOnClickListener(v ->
                startActivity(new Intent(this, EveningActivity.class)));

        // Назад -> главный экран
        findViewById(R.id.btnBack).setOnClickListener(v -> goHome());
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
