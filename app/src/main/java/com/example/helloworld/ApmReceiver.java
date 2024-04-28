package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ApmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.myapp.AIRPLANE_MODE_CHANGED")) {
            boolean state = intent.getBooleanExtra("state", false);
            if (state) {
                Toast.makeText(context, "Lentokonetila päällä", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Lentokonetila pois päältä", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
