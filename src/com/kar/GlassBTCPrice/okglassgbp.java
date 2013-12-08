package com.kar.GlassBTCPrice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Ben
 *
 */
public class okglassgbp extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent dialogIntent = new Intent(getBaseContext(), kar.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(dialogIntent);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}