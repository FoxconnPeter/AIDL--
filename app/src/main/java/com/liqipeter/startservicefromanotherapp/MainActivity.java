package com.liqipeter.startservicefromanotherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // startService(new Intent(this,AppServie.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // stopService(new Intent(this,AppServie.class));
    }
}
