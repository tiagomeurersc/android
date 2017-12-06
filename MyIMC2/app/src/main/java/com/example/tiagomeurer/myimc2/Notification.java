package com.example.tiagomeurer.myimc2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import java.util.Random;

/**
 * Created by tiagomeurer on 30/11/2017.
 */

public class Notification extends BroadcastReceiver {

     String[] frases = {"Que tal praticar alguns exercícios agora?","Você é o que você come!","Já pensou em acordar mais cedo para aproveitar mais o dia?",
             "Stay hungry, stay foolish!","Será que vai ficar bom esse aplicativo?"};
        int idx = new Random().nextInt(frases.length);
        String rand = (frases[idx]);
        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(context,MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                    setSmallIcon(R.drawable.imcicon).
                    setContentIntent(pendingIntent).
                    setContentText(rand).
                    setContentTitle("Notificação...").
                    setAutoCancel(true);
            notificationManager.notify(100,builder.build());
        }

}

