package com.nextdest.service;

import android.content.Context;
import android.database.Cursor;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;
import com.nextdest.model.Reaction;
import com.nextdest.model.ReactionType;

import java.util.List;

public class ReactionService implements IService<Reaction> {

    //private static List<Reaction> reactionList = new ArrayList<>();
    private static ReactionService instance;
    private static int nextId = 1;
    private Context context;

    public ReactionService(Context context){
        this.context = context;
    }
    /*public static ReactionService getInstance(){
        if(instance==null)instance = new ReactionService();
        return instance;
    }*/

    @Override
    public int save(Reaction object) {

        Reaction rec = getReaction(object.getIdUser(),object.getIdActivity());

        DB db = new DB(context);
        if(rec == null){
            //object.setId(nextId++);
            //reactionList.add(object);

           return (int) db.addNew_REACTION(String.valueOf(object.getReaction().value()),object.getIdActivity(),object.getIdUser());
        }else{
            return db.update_reaction(rec);
        }
    }

    @Override
    public Reaction load(int id) {

        return null;
    }

    @Override
    public List<Reaction> getAll() {
        //return Collections.unmodifiableList(reactionList);
        return null;
    }

    private List<Reaction> getUserReactions(int userId){
        return null;
    }

    private List<Reaction> getActivityReactions(int activityId){
        return null;
    }

    private Reaction getReaction(int personId, int activityId){

        DB db = new DB(context);

        Cursor cursor = db.get_REACTION(personId,activityId);

        while(cursor.moveToNext()){
            Reaction reaction = new Reaction();

            //reaction.setId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.R_)));
            reaction.setIdUser(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.R_PERSON_ID)));
            reaction.setIdActivity(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.R_ACTIVITY_ID)));
            reaction.setReaction(
                    ReactionType.valueOf(
                        cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.R_REACTION)))
            );

            return reaction;
        }

        return null;
    }
}
