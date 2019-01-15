package com.nextdest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nextdest.database.models.Person;
import com.nextdest.model.Reaction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DB {
    private final Context context;
    MySQLiteDatabase mySQLiteDatabase;
    SQLiteDatabase db;

    public DB(Context context) {
        this.context = context;
        mySQLiteDatabase = new MySQLiteDatabase(context, "NextDest", null, 1);
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

    public long addNew_LOGIN( String login_name, String pass,String email,Integer personId) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
       values.put(MySQLiteDatabase.EMAIL, email);
        values.put(MySQLiteDatabase.LOGIN_NAME, login_name);
        values.put(MySQLiteDatabase.PASSWORD, pass);
        values.put(MySQLiteDatabase.PERSON_ID, personId);
        long id = db.insert(MySQLiteDatabase.LOGIN, null, values);
        return id;
    }

    public Cursor get_LOGIN(String name, String pass) {
        db = mySQLiteDatabase.getWritableDatabase();
       // Cursor c = db.rawQuery( "select * from LOGIN",null);
        Cursor cursor = db.rawQuery("select * from " + MySQLiteDatabase.LOGIN +" where " +MySQLiteDatabase.LOGIN_NAME + " like ? AND " + MySQLiteDatabase.PASSWORD+ " like ? " , new String[] { ""+name,""+pass});
        return cursor;
    }
    public Cursor get_LOGIN_Data(String name) {
        db = mySQLiteDatabase.getWritableDatabase();
        // Cursor c = db.rawQuery( "select * from LOGIN",null);
        Cursor cursor = db.rawQuery("select * from " + MySQLiteDatabase.LOGIN +" where " +MySQLiteDatabase.LOGIN_NAME + " like ? "  , new String[] { "%"+name+"%"});
        return cursor;
    }

    public Cursor get_ProfileImage_Data(Integer person_id) {
        db = mySQLiteDatabase.getReadableDatabase();
        // Cursor c = db.rawQuery( "select * from LOGIN",null);
        Cursor cursor = db.rawQuery("select * from " + MySQLiteDatabase.PROFILE_IMAGE +" where " +
                MySQLiteDatabase.P_PERSON_ID + " = ? "  , new String[] {person_id.toString()});
        return cursor;
    }

    public long addNew_ACTIVITY(String name, String short_Description, String Description, String
            Location, int price, int person_Id,String date ,int Activity_id) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.NAME, name);
        values.put(MySQLiteDatabase.SHORT_DESCRIPTION, short_Description);
        values.put(MySQLiteDatabase.DESCRIPTION, Description);
        values.put(MySQLiteDatabase.LOCATION, Location);
        values.put(MySQLiteDatabase.PRICE, price);
        values.put(MySQLiteDatabase.A_PERSON_ID, person_Id);
        values.put(MySQLiteDatabase.Date, date);
        values.put(MySQLiteDatabase.A_ACTIVITY_ID,Activity_id);
        long id = db.insert(MySQLiteDatabase.ACTIVITY, null, values);
        return id;
    }

    public Cursor get_ACTIVITY_id(int id) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+MySQLiteDatabase.ACTIVITY+"  WHERE "+MySQLiteDatabase.A_ACTIVITY_ID+" = ?  ", new String[]{id + ""});


        return c;
    }
    public Cursor get_ACTIVITY_filter(int price,int Activity_type) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + MySQLiteDatabase.ACTIVITY +" where " +MySQLiteDatabase.PRICE + " = ? AND " + MySQLiteDatabase.A_ACTIVITY_ID+ " = ? " , new String[] { price+"",Activity_type+""});
        return cursor;
    }
    public Cursor get_ACTIVITY() {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + MySQLiteDatabase.ACTIVITY + "", null);
        return c;
    }
    public Cursor get_ACTIVITY(String name, String pass) {
        Cursor c = db.rawQuery("select * from MySQLiteDatabase.ACTIVITY", null);

        return c;
    }

    public Cursor get_ActivitySearch(String name) {
        db = mySQLiteDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + MySQLiteDatabase.ACTIVITY +" where " +MySQLiteDatabase.NAME + " like ? "  , new String[] { "%"+name+"%"});
        return cursor;
    }



    public long addNew_ACTIVITY_TYPE(String name) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.Type_NAME, name);
        long id = db.insert(MySQLiteDatabase.ACTIVITY_TYPE, null, values);
        return id;
    }

    public Cursor get_ACTIVITY_TYPE() {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+MySQLiteDatabase.ACTIVITY_TYPE+"", null);

        return c;
    }

    public long addNew_PERSON_ACTIVITY_COMMENT(String comment, int person_id, int activity_id) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.COMMENT, comment);
        values.put(MySQLiteDatabase.C_PERSON_ID, person_id);
        values.put(MySQLiteDatabase.ACTIVITY_ID, activity_id);
        long id = db.insert(MySQLiteDatabase.PERSON_ACTIVITY_COMMENT, null, values);
        return id;
    }

    public Cursor get_PERSON_ACTIVITY_COMMENT(int id) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+MySQLiteDatabase.PERSON_ACTIVITY_COMMENT+" WHERE "+MySQLiteDatabase.ACTIVITY_ID+" = ?  ", new String[]{id + ""});

        return c;
    }

    public long addNew_REACTION(String Reaction, int person_id, int activity_id) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.R_REACTION, Reaction);
        values.put(MySQLiteDatabase.R_PERSON_ID, person_id);
        values.put(MySQLiteDatabase.R_ACTIVITY_ID, activity_id);
        long id = db.insert(MySQLiteDatabase.REACTION, null, values);
        return id;
    }

    public Cursor get_REACTION(int id) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+MySQLiteDatabase.REACTION+" WHERE "+MySQLiteDatabase.R_ACTIVITY_ID+" = ?  ", new String[]{id + ""});

        return c;
    }

    public long addNew_PERSON_PREFERENCE(int person_id, int activity_id) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.PP__PERSON_ID, person_id);
        values.put(MySQLiteDatabase.ACTIVITY_TYPE_ID, activity_id);
        long id = db.insert(MySQLiteDatabase.PERSON_PREFERENCE, null, values);
        return id;
    }

    public Cursor get_PERSON_PREFERENCE(int id) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+MySQLiteDatabase.PERSON_PREFERENCE+" WHERE "+MySQLiteDatabase.PP__PERSON_ID+" = ?  ", new String[]{id + ""});

        return c;
    }

    public long addNew_ACTIVITY_IMAGE(int activity_id, byte[] image) {
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.I_ACTIVITY_ID, activity_id);
        values.put(MySQLiteDatabase.IMAGE, image);
        long id = db.insert(MySQLiteDatabase.ACTIVITY_IMAGE, null, values);
        return id;

    }

    public Cursor get_ACTIVITY_IMAGE(int id) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+MySQLiteDatabase.ACTIVITY_IMAGE+" WHERE "+MySQLiteDatabase.I_ACTIVITY_ID+" = ?  ", new String[]{id + ""});
//Retrieving data:
//
// byte[] image = cursor.getBlob(1);
        return c;


    }

    public long addNew_PROFILE_IMAGE(int person_id, byte[] image){
        db = mySQLiteDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.P_PERSON_ID, person_id);
        values.put(MySQLiteDatabase.PIMAGE, image);
        long id = db.insert(MySQLiteDatabase.PROFILE_IMAGE, null, values);
        return id;
    }
    public Cursor get_PROFILE_IMAGE(int id){
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c  = db.rawQuery("select * from MySQLiteDatabase.PROFILE_IMAGE WHERE MySQLiteDatabase.P_PERSON_ID = ?  ", new String[]{id + ""});
        return c;
    }

    public Cursor get_REACTION(int personId, int activityId) {
        db = mySQLiteDatabase.getReadableDatabase();
        /*public static final String R_REACTION = "R_REACTION";
        public static final String R_PERSON_ID = "R_PERSON_ID";
        public static final String R_ACTIVITY_ID = "R_ACTIVITY_ID";*/
        Cursor cursor = db.rawQuery("select * from "+ com.nextdest.database.MySQLiteDatabase.REACTION+" WHERE "+
                        com.nextdest.database.MySQLiteDatabase.R_PERSON_ID+" = ? AND "+ com.nextdest.database.MySQLiteDatabase.R_ACTIVITY_ID+" = ?",
                new String[]{String.valueOf(personId),String.valueOf(activityId)});

        return cursor;
    }

    public int update_reaction(Reaction rec) {
        ContentValues values = new ContentValues();
        values.put(com.nextdest.database.MySQLiteDatabase.R_ACTIVITY_ID, rec.getIdActivity());
        values.put(com.nextdest.database.MySQLiteDatabase.R_PERSON_ID, rec.getIdUser());
        //values.put(com.nextdest.database.MySQLiteDatabase.R_REACTION, String.valueOf(rec.getReaction().value()));
        return db.update(com.nextdest.database.MySQLiteDatabase.REACTION, values,
                com.nextdest.database.MySQLiteDatabase.R_PERSON_ID+" = "+String.valueOf(rec.getIdUser())+
                        " "+ com.nextdest.database.MySQLiteDatabase.R_ACTIVITY_ID+" = "+String.valueOf(rec.getIdActivity()),null);

    }

    public Cursor get_PERSON_ACTIVITY_COMMENT(int idUser, int idActivity) {
        db = mySQLiteDatabase.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+ com.nextdest.database.MySQLiteDatabase.PERSON_ACTIVITY_COMMENT+" WHERE "+
                        com.nextdest.database.MySQLiteDatabase.ACTIVITY_ID+" = ?  AND "+ com.nextdest.database.MySQLiteDatabase.R_PERSON_ID+" = ?",
                new String[]{idActivity + "",idUser+""});

        return c;
    }

    public int updatePersonActivityComment(String comment, int idUser, int idActivity) {
        /*public static final String COMMENT = "COMMENT";
            public static final String C_PERSON_ID = "C_PERSON_ID";
            public static final String ACTIVITY_ID = "ACTIVITY_ID";*/
        ContentValues values = new ContentValues();
        values.put(com.nextdest.database.MySQLiteDatabase.COMMENT, comment);
        values.put(com.nextdest.database.MySQLiteDatabase.C_PERSON_ID, idUser);
        values.put(com.nextdest.database.MySQLiteDatabase.ACTIVITY_ID, idActivity);
        return db.update(com.nextdest.database.MySQLiteDatabase.PERSON_ACTIVITY_COMMENT, values,
                com.nextdest.database.MySQLiteDatabase.R_PERSON_ID+" = "+String.valueOf(idUser)+
                        " "+ com.nextdest.database.MySQLiteDatabase.R_ACTIVITY_ID+" = "+String.valueOf(idActivity),null);
    }

    public long updatePerson(Person object) {
        /*public static final String FIRST_NAME = "FIRST_NAME";
        public static final String LAST_NAME = "LAST_NAME";
        public static final String EMAIL = "EMAIL";
        public static final String AGE = "AGE";
        public static final String PERSON_TYPE_ID = "PERSON_TYPE_ID";*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.FIRST_NAME, object.getFirstName());
        values.put(MySQLiteDatabase.LAST_NAME, object.getLastName());
        values.put(MySQLiteDatabase.EMAIL, object.getEmail());
        values.put(MySQLiteDatabase.AGE, sdf.format(object.getAge()));
        values.put(MySQLiteDatabase.KEY_ID, object.getId());
        return db.update(MySQLiteDatabase.PERSON,values,
                MySQLiteDatabase.KEY_ID +" = ?",new String[]{String.valueOf(object.getId())});
    }
}