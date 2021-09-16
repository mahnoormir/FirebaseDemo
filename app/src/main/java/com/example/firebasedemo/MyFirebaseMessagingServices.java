package com.example.firebasedemo;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingServices extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingService";



    public MyFirebaseMessagingServices() {

    }


    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        String title= remoteMessage.getNotification().getTitle();
        String msg= remoteMessage.getNotification().getBody();
        Log.d(TAG, "onMessageReceived: Message Received: \n"+ "Title:"+ title + "\n" + "Message: " + msg);
        sendNotification(title,msg);

    }

    @Override
    public void onDeletedMessages() {

    }

    private void sendNotification(String title, String messageBody){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
