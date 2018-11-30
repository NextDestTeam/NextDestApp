package com.example.hp.mydest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UserService extends Service {
    boolean msg;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        if(username=="NextDest"&&password=="123"){
            msg=true;

        }
        else{
             msg=false;
        }


        Intent i = new Intent("android.intent.action.MAIN").putExtra("some_msg", msg);
        this.sendBroadcast(i);

        this.stopSelf();
    }

}
