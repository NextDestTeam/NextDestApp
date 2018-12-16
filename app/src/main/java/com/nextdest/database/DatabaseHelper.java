package com.nextdest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {
   private static final String DB_NAME = "happyweekend";
   private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) { super(context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       updateDatabase(db, 0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      db.execSQL("DROP TABLE IF EXISTS " + "PERSON");
      db.execSQL("DROP TABLE IF EXISTS " + "LOGIN");
      db.execSQL("DROP TABLE IF EXISTS " + "ACTIVITY");
      db.execSQL("DROP TABLE IF EXISTS " + "ACTIVITY_TYPE");
      db.execSQL("DROP TABLE IF EXISTS " + "PERSON_ACTIVITY_COMMENT");

      updateDatabase(db, oldVersion, newVersion);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
      if (oldVersion <1) {

          //create our happweekend DB tables here
          db.execSQL(createPerson());
          db.execSQL(createLogin());
          db.execSQL(createActivity());
          db.execSQL(createActivityType());
          db.execSQL(createActivityComment());

          //inserting into Activity type table
          insertActivityType(db, "Others");
          insertActivityType(db, "Concert");
          insertActivityType(db, "Sport");
          insertActivityType(db, "Music");
          insertActivityType(db, "Dance");

          insertPerson(db, "leo", "Leo", "Messi", "leomessi@gmail.com");

      }
    }

    public String createPerson(){
        return "CREATE TABLE PERSON(" +
                "ID SERIAL PRIMARY KEY, " +
                "USERNAME VARCHAR(255) NOT NULL,, " +
                "FIRST_NAME VARCHAR(255) NOT NULL, "+
                "LAST_NAME VARCHAR(255) NOT NULL,"+
                "EMAIL VARCHAR(255) NOT NULL," +
                "IMAGE BLOB);";

    }

    public String createLogin() {
        return "CREATE TABLE LOGIN( " +
                "ID SERIAL PRIMARY KEY AUTOINCREMENT," +
                "PERSON_ID INT NOT NULL REFERENCES PERSON(ID), " +
                "LOGIN_NAME VARCHAR(50) NOT NULL, " +
                "PASSWORD VARCHAR(255) NOT NULL);";
    }

     public String createActivity() {
         return "CREATE TABLE ACTIVITY(," +
                 " ID SERIAL PRIMARY KEY," +
                 " NAME VARCHAR(255) NOT NULL," +
                 " SHORT_DESCRIPTION VARCHAR(255) NOT NULL," +
                 " DESCRIPTION VARCHAR(255)," +
                 " LOCATION VARCHAR(255) NOT NULL," +
                 " PRICE DECIMAL(10,2),);";
    }

    public String createActivityType() {
        return "CREATE TABLE ACTIVITY_TYPE(," +
                "ID SERIAL PRIMARY KEY AUTOINCREMENT,"+
                "ACTIVITY_NAME VARCHAR(255) NOT NULL);";
    }

    public String createActivityComment(){
        return "CREATE TABLE PERSON_ACTIVITY_COMMENT(,"+
                "ID SERIAL PRIMARY KEY," +
                "COMMENT VARCHAR(1500) NOT NULL," +
                "PERSON_ID INT NOT NULL REFERENCES PERSON," +
                "ACTIVITY_ID INT NOT NULL REFERENCES ACTIVITY(ID);";
    }


  public void insertActivityType(SQLiteDatabase db, String activityName) {
      ContentValues activitytypeValues = new ContentValues();
      activitytypeValues.put("ACTIVITY_NAME", activityName);
      db.insert("ACTIVITY_TYPE", null,activitytypeValues );
  }


  public void insertPerson(SQLiteDatabase db, String username, String firstname, String lastname, String email )
  {
      ContentValues personValues = new ContentValues();
      personValues.put("USERNAME", username);
      personValues.put("FIRST_NAME", firstname);
      personValues.put("LAST_NAME", lastname);
      personValues.put("EMAIL", email);
      db.insert("PERSON", null, personValues);

  }

  public static void updatePerson(SQLiteDatabase db, String username, String firstname, String lastname, String email, int
                                  personID )
  {
    ContentValues personValues = new ContentValues();
      personValues.put("USERNAME", username);
      personValues.put("FIRST_NAME", firstname);
      personValues.put("LAST_NAME", lastname);
      personValues.put("EMAIL", email);
      db.update("PERSON", personValues, "ID = ?", new String[]{String.valueOf(personID)});
  }


public static Cursor selectPerson(SQLiteDatabase db, int personID){
        return db.query("PERSON", null, " ID = ? ",
                new String[]{String.valueOf(personID)}, null, null, null, null);
}



}
