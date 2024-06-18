package com.bonifaz.scan_plate.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificacionService extends FirebaseMessagingService {

    private static final String TAG = "NotificacionMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message){

        super.onMessageReceived(message);
    }
}