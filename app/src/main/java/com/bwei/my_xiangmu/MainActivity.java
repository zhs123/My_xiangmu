package com.bwei.my_xiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterHome();

    }

        private void enterHome(){
            Timer time = new Timer();
            TimerTask tk = new TimerTask() {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    startActivity(intent);
                    finish();
                }
            };time.schedule(tk, 2000);
        }
}


