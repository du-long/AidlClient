package com.dulong.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dulong.aidlserver.IMyAidlInterface;
import com.dulong.aidlserver.Person;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface aidlService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View tv_aidl = findViewById(R.id.tv_aidl);
        tv_aidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (aidlService != null) {
                        aidlService.basicTypes(1, 2, true, 3, 4, "5");
                        Toast.makeText(MainActivity.this, "" + aidlService.add(10, 20), Toast.LENGTH_SHORT).show();
                        Person person = new Person();
                        person.setAge(28);
                        person.setName("杜龙");
                        person.setSex("男");
                        aidlService.setPerson(person);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent();
        intent.setAction("com.dulong.aidlserver.AIDLService");
       // intent.setPackage("com.dulong.aidlserver");
        boolean b = bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
    }

    class MyConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
