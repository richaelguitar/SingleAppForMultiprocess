package com.kad.alive.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kad.alive.ICoreKeepLive;
import com.kad.alive.R;
import com.kad.alive.entity.UserInfo;

public class KeepAService extends Service {


    public KeepAService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bindLiveBService();
    }

    private void bindLiveBService() {
        Intent intent = new Intent(getApplicationContext(),KeepBService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ICoreKeepLive iCoreKeepLive = ICoreKeepLive.Stub.asInterface(service);
            if(iCoreKeepLive!=null){
                try {
                    Log.i("MyLog",iCoreKeepLive.getUserInfo().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindLiveBService();
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return (IBinder)iCoreKeepLive;
    }

    private ICoreKeepLive iCoreKeepLive = new ICoreKeepLive.Stub() {
        @Override
        public UserInfo getUserInfo() throws RemoteException {
            return new UserInfo(2,"保活A","SGFETE55565GJKDJEM");
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyLog","KeepAService is onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyLog","KeepAService is onDestroy");
    }
}
