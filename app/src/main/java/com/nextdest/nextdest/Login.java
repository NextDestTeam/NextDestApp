package com.nextdest.nextdest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nextdest.database.DB;
import com.nextdest.service.LoginService;
import com.nextdest.service.PersonService;
import com.nextdest.synchronization.Sync;

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


                String user = username.getText().toString();
                String pass = password.getText().toString();

                LoginService loginService = new LoginService(getApplicationContext());
                Sync syncAdapter = new Sync(getApplicationContext());
                com.nextdest.database.models.Login login = loginService.getLogin(user,pass);

                if(login == null){
                    Toast.makeText(getApplicationContext(),"Invalid Username Or Password",Toast.LENGTH_LONG).show();
                }else {

                    PersonService personService = new PersonService(getApplicationContext());
                    Session.LoggedPerson = personService.load(login.getPersonId());
                    Intent i = new Intent(getApplicationContext(),PopularEventNav.class);
                    syncAdapter.sync();
                    i.putExtra("username", user);
                    getApplicationContext().startActivity(i);
                }

            }
        });
    }



}

