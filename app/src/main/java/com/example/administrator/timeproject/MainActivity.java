package com.example.administrator.timeproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private Button jump;
    int x=3;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    image.setImageResource(R.drawable.ss);
                    break;
                case 300:
                    jump.setText(x+"跳过");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getxian();

    }

    private void getxian() {
        Thread thread =new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(200);
            }
        };
        thread.start();
        Thread thread1 =new Thread(){
            @Override
            public void run() {
                super.run();
                final Timer timer =new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                      if(x>=0) {
                          handler.sendEmptyMessage(300);
                          x--;
                      }
                        if(x<0) {
                            startActivity(new Intent(MainActivity.this,Home.class));
                            timer.cancel();
                        }
                    }
                },1000,1000);
            }
        };
    }


    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        jump = (Button) findViewById(R.id.jump);
        jump.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump:

                break;
        }
    }


}
