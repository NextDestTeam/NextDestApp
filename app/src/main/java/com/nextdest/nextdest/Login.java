package com.nextdest.nextdest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nextdest.database.DB;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView signUp;
    Button login;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db =new DB(getApplicationContext());
        username = (EditText) findViewById(R.id.etLogin);
        signUp = (TextView) findViewById(R.id.link_signup);
        signUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        username = (EditText) findViewById(R.id.etLogin);
        password = (EditText) findViewById(R.id.pass);
        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Username =username.getText().toString();
                String Password=password.getText().toString();
                Cursor cursor = db.get_LOGIN(Username,Password);
                if(cursor.getCount()==0){
                    Toast.makeText(getApplicationContext(),"Invalid Username Or Password",Toast.LENGTH_LONG).show();
                }else {
                    Intent i = new Intent(getApplicationContext(),PopularEventNav.class);
                    i.putExtra("username", Username);
                    getApplicationContext().startActivity(i);
                }

            }
        });
    }



    }

