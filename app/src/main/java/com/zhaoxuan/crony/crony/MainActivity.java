package com.zhaoxuan.crony.crony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhaoxuan.crony.CronyReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setAction(CronyReceiver.ACTION_CLIPBOARD);

                intent.putExtra("msg", "发送广播测试成功.....");
                sendBroadcast(intent);
                Log.d("Crony", "sendBroadcast");

            }
        });
    }
}
