package com.nextdest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class DB {
    private final Context context;
    MySQLiteDatabase mySQLiteDatabase;
    SQLiteDatabase db;

    public DB(Context context) {
        this.context = context;
        mySQLiteDatabase = new MySQLiteDatabase(context, "NextDest", null, 1);
    }

    public void open() {
        db = mySQLiteDatabase.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long addNew_PERSON_Type(String name) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.P_NAME_type, name);
        long id = db.insert(MySQLiteDatabase.PERSON_Type, null, values);
        return id;
    }

    public Cursor get_PERSON_Type() {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.PERSON_Type", null);

        return c;
    }

    public long addNew_PERSON(String F_name, String L_name, String email, Date Age, int PERSON_TYPE_ID) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.FIRST_NAME, F_name);
        values.put(MySQLiteDatabase.LAST_NAME, L_name);
        values.put(MySQLiteDatabase.EMAIL, email);
        values.put(MySQLiteDatabase.AGE, String.valueOf(Age));
        values.put(MySQLiteDatabase.PERSON_TYPE_ID, PERSON_TYPE_ID);
        long id = db.insert(MySQLiteDatabase.PERSON, null, values);
        return id;
    }

    public Cursor get_PERSON() {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.PERSON", null);

        return c;
    }

    public long addNew_LOGIN(int PERSON_ID, String login_name, String pass) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.PERSON_ID, PERSON_ID);
        values.put(MySQLiteDatabase.LOGIN_NAME, login_name);
        values.put(MySQLiteDatabase.PASSWORD, pass);
        long id = db.insert(MySQLiteDatabase.LOGIN, null, values);
        return id;
    }

    public Cursor get_LOGIN(String name, String pass) {
        Cursor c = db.rawQuery("SELECT * FROM MySQLiteDatabase.LOGIN WHERE MySQLiteDatabase.LOGIN_NAME = ? AND MySQLiteDatabase.PASSWORD = ?", new String[]{name, pass});

        return c;
    }

    public long addNew_ACTIVITY(String name, String short_Description, String Description, String Location, int price, int person_Id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.NAME, name);
        values.put(MySQLiteDatabase.SHORT_DESCRIPTION, short_Description);
        values.put(MySQLiteDatabase.DESCRIPTION, Description);
        values.put(MySQLiteDatabase.LOCATION, Location);
        values.put(MySQLiteDatabase.PRICE, price);
        values.put(MySQLiteDatabase.A_PERSON_ID, person_Id);
        long id = db.insert(MySQLiteDatabase.ACTIVITY, null, values);
        return id;
    }

    public Cursor get_ACTIVITY(String name, String pass) {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.ACTIVITY", null);

        return c;
    }

    public long addNew_ACTIVITY_TYPE(String name) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.Type_NAME, name);
        long id = db.insert(MySQLiteDatabase.ACTIVITY_TYPE, null, values);
        return id;
    }

    public Cursor get_ACTIVITY_TYPE() {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.ACTIVITY_TYPE", null);

        return c;
    }

    public long addNew_PERSON_ACTIVITY_COMMENT(String comment, int person_id, int activity_id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.COMMENT, comment);
        values.put(MySQLiteDatabase.C_PERSON_ID, person_id);
        values.put(MySQLiteDatabase.ACTIVITY_ID, activity_id);
        long id = db.insert(MySQLiteDatabase.PERSON_ACTIVITY_COMMENT, null, values);
        return id;
    }

    public Cursor get_PERSON_ACTIVITY_COMMENT(int id) {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.PERSON_ACTIVITY_COMMENT WHERE MySQLiteDatabase.ACTIVITY_ID = ?  ", new String[]{id + ""});

        return c;
    }

    public long addNew_REACTION(String Reaction, int person_id, int activity_id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.R_REACTION, Reaction);
        values.put(MySQLiteDatabase.R_PERSON_ID, person_id);
        values.put(MySQLiteDatabase.R_ACTIVITY_ID, activity_id);
        long id = db.insert(MySQLiteDatabase.REACTION, null, values);
        return id;
    }

    public Cursor get_REACTION(int id) {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.REACTION WHERE MySQLiteDatabase.R_ACTIVITY_ID = ?  ", new String[]{id + ""});

        return c;
    }

    public long addNew_PERSON_PREFERENCE(int person_id, int activity_id) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.PP__PERSON_ID, person_id);
        values.put(MySQLiteDatabase.ACTIVITY_TYPE_ID, activity_id);
        long id = db.insert(MySQLiteDatabase.PERSON_PREFERENCE, null, values);
        return id;
    }

    public Cursor get_PERSON_PREFERENCE(int id) {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.PERSON_PREFERENCE WHERE MySQLiteDatabase.PP__PERSON_ID = ?  ", new String[]{id + ""});

        return c;
    }

    public long addNew_ACTIVITY_IMAGE(int activity_id, byte[] image) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.I_ACTIVITY_ID, activity_id);
        values.put(MySQLiteDatabase.IMAGE, image);
        long id = db.insert(MySQLiteDatabase.ACTIVITY_IMAGE, null, values);
        return id;

    }

    public Cursor get_ACTIVITY_IMAGE(int id) {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.ACTIVITY_IMAGE WHERE MySQLiteDatabase.I_ACTIVITY_ID = ?  ", new String[]{id + ""});
//Retrieving data:
//
// byte[] image = cursor.getBlob(1);
        return c;


    }
}