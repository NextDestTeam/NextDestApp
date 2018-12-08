package com.nextdest.nextdest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nextdest.networking.HttpPostRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {
    EditText etEmail;
    EditText etUserName;
    EditText etPassword1;
    EditText etPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.email);
        etUserName = (EditText) findViewById(R.id.userName);
        etPassword1 = (EditText) findViewById(R.id.password1);
        etPassword2 = (EditText) findViewById(R.id.password2);
    }

    public void register(View view) throws JSONException, ExecutionException, InterruptedException {
        HttpPostRequest httpPostRequestLogin = new HttpPostRequest();
        HttpPostRequest httpPostRequestPerson = new HttpPostRequest();
        String urlLogin = httpPostRequestLogin.getUrl() + "logins";
        String urlPerson = httpPostRequestPerson.getUrl() + "persons";
        String resultLogin;
        String resultPerson;
        JSONObject jsonObjectLogin = new JSONObject();
        JSONObject jsonObjectPerson = new JSONObject();

        // Create person
        jsonObjectPerson.put("age", "2018-12-08T17:39:10.103Z");
        jsonObjectPerson.put("email", etEmail.getText().toString());
        jsonObjectPerson.put("firstName", etUserName.getText().toString());
        jsonObjectPerson.put("lastName", etUserName.getText().toString());
        jsonObjectPerson.put("personTypeId", 1);

        httpPostRequestPerson.setPostData(jsonObjectPerson);

        httpPostRequestPerson.execute(urlPerson).get();
        resultPerson = httpPostRequestPerson.getResponse();
        JSONObject personResponse = new JSONObject(resultPerson);

        //Create account
        //If passwords are the same create account
        if(etPassword1.getText().toString().equals(etPassword2.getText().toString())){
            jsonObjectLogin.put("login",  etUserName.getText().toString());
            jsonObjectLogin.put("password", etPassword1.getText().toString());
            jsonObjectLogin.put("personId", personResponse.getString("id"));

            httpPostRequestLogin.setPostData(jsonObjectLogin);
            httpPostRequestLogin.execute(urlLogin).get();
        }else{
            // TODO Show error: ("Passwords don't match)
        }

        // TODO redirect to login/register screen
    }
}
