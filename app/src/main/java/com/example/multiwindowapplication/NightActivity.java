package com.example.multiwindowapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class NightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        // Кнопка без текста
        findViewById(R.id.btnSleep).setOnClickListener(v -> askSleeping());
    }

    private void askSleeping() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.question_sleep)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        // Да -> закрыть приложение
                        ActivityCompat.finishAffinity(this))
                .setNegativeButton(R.string.no, (dialog, which) -> {
                    // Нет -> главный экран
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}
