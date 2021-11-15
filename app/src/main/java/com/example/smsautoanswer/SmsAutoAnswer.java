package com.example.smsautoanswer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class SmsAutoAnswer extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("sms", "on receive," + intent.getAction());
//        System.out.println("oguzcan "+intent.getAction());
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            System.out.println("test"+ MainActivity.check);
            if(MainActivity.check==false){
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    String address = smsMessage.getOriginatingAddress();
                    String autoResponse = "This phone does not accept text messages. Please call instead.";
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String message = sharedPreferences.getString("Message", autoResponse);
                    SavedMessage savedMessage = new SavedMessage(address,);
                    for(int i=0;i<MainActivity.savedMessages.size();i++){
                        if()
                    }
                    SmsManager.getDefault().sendTextMessage(address, null, message, null, null);
                }
            }
        }
    }
}
