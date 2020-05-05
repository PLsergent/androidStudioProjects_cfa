package com.example.smsboomer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SMSService extends Service {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private boolean active;
    private int countSMSReceived;

    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(SMS_RECEIVED);
        registerReceiver(new SMSReceiver(), filter);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class SMSReceiver extends BroadcastReceiver {

        public SMSReceiver(){
            Log.v("SMSReceiver","SMSReceiver()");
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("SMSReceiver","onReceive()");
            Toast.makeText(getApplicationContext(), "You received a message", Toast.LENGTH_SHORT).show();

        }
    }
}
