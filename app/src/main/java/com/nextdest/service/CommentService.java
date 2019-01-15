package com.nextdest.service;

import android.content.Context;
import android.database.Cursor;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;
import com.nextdest.database.models.PersonActivityComment;
import com.nextdest.model.Comment;

import java.util.List;

public class CommentService implements IService<Comment>{



    private Context context;

    public CommentService(Context context){
        this.context = context;
    }

    @Override
    public int save(Comment object) {
        DB db = new DB(context);
        PersonActivityComment personActivityComment = load(object.getIdUser(),object.getIdActivity());
        if(personActivityComment==null){
            return (int)db.addNew_PERSON_ACTIVITY_COMMENT(object.getComment(),object.getIdUser(),object.getIdActivity());
        }else{
            return db.updatePersonActivityComment(object.getComment(),object.getIdUser(),object.getIdActivity());
        }
    }

    private PersonActivityComment load(int idUser, int idActivity) {
        DB db = new DB(context);
        Cursor cursor = db.get_PERSON_ACTIVITY_COMMENT(idUser,idActivity);

        while(cursor.moveToNext()){

            PersonActivityComment personActivityComment = new PersonActivityComment();
            /*public static final String COMMENT = "COMMENT";
            public static final String C_PERSON_ID = "C_PERSON_ID";
            public static final String ACTIVITY_ID = "ACTIVITY_ID";*/
            personActivityComment.setComment(
                    cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.COMMENT)));
            personActivityComment.setPersonId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.C_PERSON_ID)));
            personActivityComment.setActivityId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.ACTIVITY_ID)));

            return personActivityComment;
        }
        cursor.close();
        return null;
    }

    @Override
    public Comment load(int id) {

        return null;
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }
}
