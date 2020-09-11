package com.moise.gadsledersboards;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class StartActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate:started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        displayWelcome("Welcome to GADS LeaderBoard");
        delay(5000);

    }


    private void displayWelcome(String message) {
        View view = findViewById(R.id.background);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
    public void delay(int time) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity (new Intent(StartActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, time);
    }

}