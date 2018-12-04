package com.example.hp.mydest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login =(Button) findViewById(R.id.btn_login);
        Button reg =(Button) findViewById(R.id.btn_reg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent =new Intent(MainActivity.this,Login.class);
                startActivity(logIntent);
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent =new Intent(MainActivity.this,Register.class);
                startActivity(regIntent);
            }
        });


        viewPager =(ViewPager)findViewById(R.id.viewbager);
        ImageAdapter adapter=new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),4000,4000);

    }
     public class MyTimerTask extends TimerTask{

         @Override
         public void run() {
             MainActivity.this.runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     if(viewPager.getCurrentItem()==0){
                         viewPager.setCurrentItem(1);
                     }else if (viewPager.getCurrentItem()==1){
                         viewPager.setCurrentItem(2);
                     }else{
                         viewPager.setCurrentItem(0);
                     }
                 }
             });
         }
     }
}
