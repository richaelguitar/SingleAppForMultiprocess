package com.kad.alive;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kad.alive.service.AppCoreService;
import com.kad.alive.service.KeepAService;
import com.kad.alive.service.KeepBService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_kill_main).setOnClickListener(this);

        findViewById(R.id.btn_kill_a).setOnClickListener(this);

        findViewById(R.id.btn_kill_b).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_kill_main:
                Process.killProcess(Process.myPid());
                break;
            case R.id.btn_kill_a:
                Process.killProcess(getProcessKid("com.kad.alive:liveA"));
                break;
            case R.id.btn_kill_b:
                Process.killProcess(getProcessKid("com.kad.alive:liveB"));
                break;
        }
    }


    private int  getProcessKid(String processName){
        int kid =0 ;
        ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo>  processes = manager.getRunningAppProcesses();
        for(int i=0;i<processes.size();i++){
            if(processName.equalsIgnoreCase(processes.get(i).processName)){
                kid = processes.get(i).pid;
                Log.i("MyLog","processName="+processes.get(i).processName+" pid="+kid);
                break;
            }
        }

                return kid;
    }
}
