package com.nextdest.service;


import android.content.Context;
import android.database.Cursor;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;
import com.nextdest.database.models.Login;

import java.util.List;

public class LoginService implements IService<Login> {


    private Context context;

    public LoginService(Context context){
        this.context = context;
    }


    @Override
    public int save(Login object) {
        return 0;
    }

    @Override
    public Login load(int id) {
        return null;
    }

    @Override
    public List<Login> getAll() {
        return null;
    }

    public Login getLogin(String username,String password){
        DB db = new DB(context);
        /*public static final String PERSON_ID = "PERSON_ID";
        public static final String LOGIN_NAME = "LOGIN_NAME";
        public static final String PASSWORD = "PASSWORD";*/
        Cursor cursor = db.get_LOGIN(username,password);
        while(cursor.moveToNext()){

            Login login = new Login();

            login.setPersonId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.PERSON_ID)));
            login.setLogin(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LOGIN_NAME)));
            login.setPassword(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.PASSWORD)));

            return login;
        }
        return null;
    }
}
