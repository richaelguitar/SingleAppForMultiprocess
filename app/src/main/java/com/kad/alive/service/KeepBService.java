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

public class KeepBService extends Service {
    public KeepBService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bindAppService();
        bindLiveAService();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ICoreKeepLive coreKeepLive = ICoreKeepLive.Stub.asInterface(service);
            if(coreKeepLive!=null){
                try {
                    Log.i("MyLog",coreKeepLive.getUserInfo().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindAppService();
            bindLiveAService();
        }
    };

    private void bindLiveAService() {
        Intent intent = new Intent(this,AppCoreService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }



    private void bindAppService() {
        Intent intent = new Intent(this,KeepAService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return (IBinder) iCoreKeepLive;
    }

    private ICoreKeepLive iCoreKeepLive = new ICoreKeepLive.Stub() {
        @Override
        public UserInfo getUserInfo() throws RemoteException {
            return new UserInfo(3,"保活B","YINFY566JKDBH00789");
        }
    };
}
