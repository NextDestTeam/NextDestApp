package com.example.hp.mydest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class lunch extends AppCompatActivity {
private final int Splash_Lenght=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent=new Intent(lunch.this,MainActivity.class);
                lunch.this.startActivity(mainIntent);
                lunch.this.finish();

            }

        },Splash_Lenght);
    }
}
