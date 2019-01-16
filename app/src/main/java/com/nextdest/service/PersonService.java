package com.nextdest.service;

import android.content.Context;
import android.database.Cursor;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;
import com.nextdest.database.models.Person;

import java.util.List;

public class PersonService implements IService<Person> {

    private Context context;
    public PersonService(Context context){
        this.context = context;
    }

    @Override
    public int save(Person object) {

        DB db = new DB(context);
        if (object.getId()!=null && object.getId()>0){
            return (int)db.updatePerson(object);
        }else{
            return (int)db.addNew_PERSON(object.getFirstName(),object.getLastName(),object.getEmail(),
                    object.getAge(),object.getPersonTypeId());
        }
    }

    @Override
    public Person load(int id) {
        DB db = new DB(context);
        //db.open();
        Cursor cursor = db.get_PERSON(id);
        while (cursor.moveToNext()){
            Person person = new Person();
            /*public static final String FIRST_NAME = "FIRST_NAME";
            public static final String LAST_NAME = "LAST_NAME";
            public static final String EMAIL = "EMAIL";
            public static final String AGE = "AGE";
            public static final String PERSON_TYPE_ID = "PERSON_TYPE_ID";*/
            person.setId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.KEY_ID)));
            //person.setAge(cursor.get(cursor.getColumnIndex(MySQLiteDatabase.AGE)));
            person.setEmail(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.EMAIL)));
            person.setFirstName(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.FIRST_NAME)));
            person.setLastName(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LAST_NAME)));
            person.setPersonTypeId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.PERSON_TYPE_ID)));
            return person;
        }
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }
}
