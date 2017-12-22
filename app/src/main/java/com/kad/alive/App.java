package com.kad.alive;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.kad.alive.service.AppCoreService;

/**
 * Created by xiaowenwu on 2017/12/20.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this,AppCoreService.class));
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i("MyLog","level="+level);
        startService(new Intent(this,AppCoreService.class));
    }
}
