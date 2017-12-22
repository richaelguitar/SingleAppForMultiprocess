package com.kad.alive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.kad.alive.ICoreKeepLive;
import com.kad.alive.entity.UserInfo;

public class AppCoreService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        bindLiveAService();
    }

    private void bindLiveAService() {
        Intent intent = new Intent(this,KeepAService.class);
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
            bindLiveAService();
        }
    };

    public AppCoreService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return (IBinder) iCoreKeepLive;
    }

    private ICoreKeepLive iCoreKeepLive = new ICoreKeepLive.Stub() {
        @Override
        public UserInfo getUserInfo() throws RemoteException {
            return new UserInfo(1,"主进程","YBRKF128620PFF525685");
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this,AppCoreService.class));
    }
}
