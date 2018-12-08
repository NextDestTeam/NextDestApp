package com.nextdest.nextdest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nextdest.networking.HttpGetRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    EditText etLogin;
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.pass);
    }

    public void login(View view) throws ExecutionException, InterruptedException, JSONException {
        HttpGetRequest httpGetRequest = new HttpGetRequest();
        final String HTTP_METHOD = "GET";
        String url = httpGetRequest.getURL() + "loginName/" + etLogin.getText().toString();
        String result;
        JSONObject jsonObject;

        result = httpGetRequest.execute(url, HTTP_METHOD).get();

        jsonObject = new JSONObject(result);
        String userName = jsonObject.getString("login");
        String password = jsonObject.getString("password");

        if(result != null && userName.equals(etLogin.getText().toString()) && password.equals(etPassword.getText().toString())){
            Intent i = new Intent(this,PopularEventNav.class);
            this.startActivity(i);
        }else {
            // TODO show incorrect login error
            //Intent i = new Intent(this, ListEventActivity.class);
            //this.startActivity(i);
        }
    }
}
