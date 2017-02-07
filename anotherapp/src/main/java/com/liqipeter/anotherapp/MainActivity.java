package com.liqipeter.anotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.liqipeter.startservicefromanotherapp.IAppInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    private Intent serviceintent;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceintent=new Intent();
        serviceintent.setComponent(new ComponentName("com.liqipeter.startservicefromanotherapp","com.liqipeter.startservicefromanotherapp.AppServie"));
        findViewById(R.id.btnstartservice).setOnClickListener(this);
        findViewById(R.id.btnstopservice).setOnClickListener(this);
        findViewById(R.id.btnbingdservice).setOnClickListener(this);
        findViewById(R.id.btnendservice).setOnClickListener(this);
        findViewById(R.id.btnSync).setOnClickListener(this);
        etInput=(EditText)findViewById(R.id.etInput);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnstartservice:

                startService(serviceintent);
                break;
            case R.id.btnstopservice:
                stopService(serviceintent);

                break;
            case R.id.btnbingdservice:

                bindService(serviceintent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnendservice:
                unbindService(this);
                binder=null;

                break;
            case R.id.btnSync:
                if (binder!=null){
                    try {
                        binder.setData(etInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }

                break;

        }

    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder service) {
        System.out.println("Bind Service");
        System.out.println(service);
        binder = IAppInterface.Stub.asInterface(service);


    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }



    private IAppInterface binder=null;

}
