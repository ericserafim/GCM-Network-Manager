package ericserafim.br.com.gcmnetwork.service;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ericserafim.br.com.gcmnetwork.MainActivity;
import ericserafim.br.com.gcmnetwork.firebase.Storage;
import ericserafim.br.com.gcmnetwork.helper.NotificationCustom;

public class CustomService extends GcmTaskService {
    private static int COUNT = 1;
    private static Date data = Calendar.getInstance().getTime();

    @Override
    public void onInitializeTasks() {
        super.onInitializeTasks();

        Log.i("ERIC", "onInitializeTasks()");
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        try {
            Log.i("ERIC", "onRunTask() - " + COUNT);
            Date agora = Calendar.getInstance().getTime();
            //long tempo = getDateDiff(data, agora, TimeUnit.MINUTES);

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss:SSS");

            Storage.insert("LOG Antes " + String.valueOf(COUNT), format.format(agora));
            NotificationCustom.notify(
                    this,
                    MainActivity.class,
                    String.valueOf(COUNT));
            Storage.insert("LOG Depois " + String.valueOf(COUNT), format.format(agora));

            COUNT++;
            //data = agora;
            return GcmNetworkManager.RESULT_SUCCESS;
        } catch (Exception e) {
            Storage.insert("ERRO", e.getMessage());
            return GcmNetworkManager.RESULT_RESCHEDULE;
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.SECONDS);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("ERIC",  "onTaskRemoved()");
        super.onTaskRemoved(rootIntent);
    }
}