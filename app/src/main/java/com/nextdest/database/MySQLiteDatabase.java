package com.nextdest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MySQLiteDatabase extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "NextDest";

    // Table Names
    public static final String PERSON_Type = "PERSON_Type";
    public static final String PERSON = "PERSON";
    public static final String LOGIN = "LOGIN";
    public static final String ACTIVITY = "ACTIVITY";
    public static final String ACTIVITY_TYPE = "ACTIVITY_TYPE";
    public static final String PERSON_ACTIVITY_COMMENT = "PERSON_ACTIVITY_COMMENT";
    public static final String REACTION = "REACTION";
    public static final String PERSON_PREFERENCE = "PERSON_PREFERENCE";
    public static final String ACTIVITY_IMAGE = "ACTIVITY_IMAGE";
    // Common column names
    public static final String KEY_ID = "_id";
    // PERSON_Type column names
    public static final String P_NAME_type = "P_NAME_type";
    // PERSON Table - column nmaes
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String AGE = "AGE";
    public static final String PERSON_TYPE_ID = "PERSON_TYPE_ID";

    // LOGIN Table - column nmaes
    public static final String PERSON_ID = "PERSON_ID";
    public static final String LOGIN_NAME = "LOGIN_NAME";
    public static final String PASSWORD = "PASSWORD";

    // ACTIVITY Table - column nmaes
    public static final String NAME = "NAME";
    public static final String SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String LOCATION = "LOCATION";
    public static final String PRICE = "PRICE";
    public static final String A_PERSON_ID = "A_PERSON_ID";
    // ACTIVITY_TYPE Table - column nmaes
    public static final String Type_NAME = "Type_NAME";
    // PERSON_ACTIVITY_COMMENT Table - column nmaes
    public static final String COMMENT = "COMMENT";
    public static final String C_PERSON_ID = "C_PERSON_ID";
    public static final String ACTIVITY_ID = "ACTIVITY_ID";
    // REACTION Table - column nmaes
    public static final String R_REACTION = "R_REACTION";
    public static final String R_PERSON_ID = "R_PERSON_ID";
    public static final String R_ACTIVITY_ID = "R_ACTIVITY_ID";
    // PERSON_PREFERENCE Table - column nmaes
    public static final String PP__PERSON_ID = "PP_PERSON_ID";
    public static final String ACTIVITY_TYPE_ID = "ACTIVITY_TYPE_ID ";
    // ACTIVITY_IMAGE Table - column nmaes
    public static final String IMAGE = "IMAGE";
    public static final String I_ACTIVITY_ID = "I_ACTIVITY_ID ";


    public MySQLiteDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Table Create Statements
        sqLiteDatabase.execSQL("Create Table " + PERSON_Type + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + P_NAME_type + " TEXT);");

        sqLiteDatabase.execSQL("Create Table " + PERSON + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT," + EMAIL + " TEXT," + AGE + " Date," + PERSON_TYPE_ID + "INTEGER," + " FOREIGN KEY (" + PERSON_TYPE_ID + ") REFERENCES " + PERSON_Type + "(" + KEY_ID + "));");

        sqLiteDatabase.execSQL("Create Table " + LOGIN + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + LOGIN_NAME + " TEXT, " + PASSWORD + " TEXT," + PERSON_ID + "INTEGER," + " FOREIGN KEY (" + PERSON_ID + ") REFERENCES " + PERSON + "(" + KEY_ID + "));");

        sqLiteDatabase.execSQL("Create Table " + ACTIVITY + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + SHORT_DESCRIPTION + " TEXT," + DESCRIPTION + " TEXT," + LOCATION + "TEXT," + PRICE + "INTEGER," + A_PERSON_ID + "INTEGER," + " FOREIGN KEY (" + A_PERSON_ID + ") REFERENCES " + PERSON + "(" + KEY_ID + "));");

        sqLiteDatabase.execSQL("Create Table " + ACTIVITY_TYPE + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + Type_NAME + " TEXT);");

        sqLiteDatabase.execSQL("Create Table " + PERSON_ACTIVITY_COMMENT + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + COMMENT + " TEXT," + C_PERSON_ID + "INTEGER," + " FOREIGN KEY (" + C_PERSON_ID + ") REFERENCES " + PERSON + "(" + KEY_ID + ")," + " FOREIGN KEY (" + ACTIVITY_ID + ") REFERENCES " + ACTIVITY + "(" + KEY_ID + "));");

        sqLiteDatabase.execSQL("Create Table " + REACTION + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + R_REACTION + " TEXT," + R_PERSON_ID + "INTEGER," + " FOREIGN KEY (" + R_PERSON_ID + ") REFERENCES " + PERSON + "(" + KEY_ID + ")," + " FOREIGN KEY (" + R_ACTIVITY_ID + ") REFERENCES " + ACTIVITY + "(" + KEY_ID + "));");

        sqLiteDatabase.execSQL("Create Table " + PERSON_PREFERENCE + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + PP__PERSON_ID + "INTEGER," + " FOREIGN KEY (" + PP__PERSON_ID + ") REFERENCES " + PERSON + "(" + KEY_ID + ")," + " FOREIGN KEY (" + ACTIVITY_TYPE_ID + ") REFERENCES " + ACTIVITY_TYPE + "(" + KEY_ID + "));");

        sqLiteDatabase.execSQL("Create Table " + ACTIVITY_IMAGE + "(" + KEY_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + IMAGE + " BLOB," + I_ACTIVITY_ID + "INTEGER," + " FOREIGN KEY (" + I_ACTIVITY_ID + ") REFERENCES " + ACTIVITY + "(" + KEY_ID + "));");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PERSON_Type);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PERSON);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOGIN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACTIVITY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_TYPE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PERSON_ACTIVITY_COMMENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + REACTION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PERSON_PREFERENCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACTIVITY_IMAGE);
        onCreate(sqLiteDatabase);
    }
}
