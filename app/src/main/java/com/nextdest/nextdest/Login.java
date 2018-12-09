package com.nextdest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText etLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
    }

    public void login(View view) {

        if(etLogin.getText().toString().equals("u")){
            Intent i = new Intent(this,PopularEventNav.class);
            this.startActivity(i);
        }else {
            Intent i = new Intent(this, ListEventActivity.class);
            this.startActivity(i);
        }
    }
}
