package com.exmaple.android.intentserviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    private static final String ACTION_INCREMENT = "increment_count";
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int intExtra = intent.getIntExtra(ACTION_INCREMENT, 0);
        SharedPreferencesHelper.increment(this, intExtra);
    }
}
