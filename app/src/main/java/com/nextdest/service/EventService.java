package com.nextdest.service;


import android.content.Context;
import android.database.Cursor;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;
import com.nextdest.database.models.Activity;
import com.nextdest.database.models.ActivityType;
import com.nextdest.model.Comment;
import com.nextdest.model.Event;
import com.nextdest.model.Reaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventService implements IService<Event>{

    //public static List<Event> events = new ArrayList<Event>();
    //public static int index = 1;
    //private static EventService instance = null;
    Context context;

    public EventService(Context context){
        this.context = context;
    }

    public int save(Event event) {
        DB db = new DB(context);
        if(event.getId()>0) {
            //db.
        }else{

        }
        return 0;
    }

    public Event load(int id){
        try {
            DB db = new DB(context);
            Cursor cursorActivity = db.get_ACTIVITY_id(id);
            while (cursorActivity.moveToNext()) {


                Activity a = cursorToActivity(cursorActivity);
                cursorActivity.close();
                Cursor cursor = db.get_ACTIVITY_TYPE(a.getActivityType());

                cursor.moveToNext();
                ActivityType activityType = new ActivityType();
                activityType.setId(a.getActivityType());
                activityType.setName(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.Type_NAME)));

                cursor.close();


                cursor = db.get_ACTIVITY_IMAGE(a.getImgageId());
                cursor.moveToNext();
                byte[] photo = cursor.getBlob(cursor.getColumnIndex(MySQLiteDatabase.IMAGE));

                cursor.close();

                CommentService commentService = new CommentService(context);

                List<Comment> comments = commentService.getAllByActivity(a.getId());

                ReactionService reactionService = new ReactionService(context);
                List<Reaction> reactions = reactionService.getAllByActivity(a.getId());

                return activityToEvent(a, activityType.getName(), photo, comments, reactions);


            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    private Event activityToEvent(Activity a, String type, byte[] photo, List<Comment> comments, List<Reaction> reactions) {
        Event event = new Event();
        event.setId(a.getId());
        event.setCost(a.getPrice());
        event.setShortDescription(a.getShortDescription());
        event.setDescription(a.getDescription());
        event.setDate(a.getDate());
        event.setLocation(a.getLocation());
        event.setName(a.getName());

        event.setType(type);
        event.setPhoto(photo);
        for (int i=0;i<comments.size();i++) {
            event.addComment(comments.get(i));
        }
        for (int i=0;i<reactions.size();i++) {
            event.addReation(reactions.get(i));
        }
        return event;
    }

    private Activity cursorToActivity(Cursor cursor) throws ParseException {
        Activity activity = new Activity();
        activity.setActivityType(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.A_ACTIVITY_TYPE_ID)));
        activity.setName(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.NAME)));
        activity.setShortDescription(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.SHORT_DESCRIPTION)));
        activity.setDescription(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.DESCRIPTION)));
        activity.setLocation(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LOCATION)));
        activity.setPrice(cursor.getDouble(cursor.getColumnIndex(MySQLiteDatabase.PRICE)));
        activity.setPerson(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.A_PERSON_ID)));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            String date = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.Date));
            activity.setDate(sdf.parse(date));
        }catch (Exception ex){
            activity.setDate(new Date(2019,02,01,11,0));
        }
        activity.setId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.A_ACTIVITY_ID)));
        activity.setImgageId(cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.A_IMAGE_ID)));
        return activity;
    }

    public List<Event> getAll(){

        List<Event> list = new ArrayList<>();
        try {
            DB db = new DB(context);
            Cursor cursorActivity = db.get_ACTIVITY();
            Cursor cursor;
            while (cursorActivity.moveToNext()) {


                Activity a = cursorToActivity(cursorActivity);

                cursor = db.get_ACTIVITY_TYPE(a.getActivityType());

                ActivityType activityType = new ActivityType();
                if(cursor.moveToNext()) {

                    activityType.setId(a.getActivityType());
                    activityType.setName(cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.Type_NAME)));
                }

                cursor.close();


                cursor = db.get_ACTIVITY_IMAGE(a.getImgageId());
                byte[] photo = null;
                if(cursor.moveToNext()) {
                    photo = cursor.getBlob(cursor.getColumnIndex(MySQLiteDatabase.IMAGE));
                }

                cursor.close();

                CommentService commentService = new CommentService(context);

                List<Comment> comments = commentService.getAllByActivity(a.getId());

                ReactionService reactionService = new ReactionService(context);
                List<Reaction> reactions = reactionService.getAllByActivity(a.getId());

                list.add(activityToEvent(a, activityType.getName(), photo, comments, reactions));


            }
            cursorActivity.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;

    }



}
