package com.nextdest.service;

import com.nextdest.model.Event;
import com.nextdest.model.Rating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatingService implements IService<Rating> {

    private static List<Rating> ratingList = new ArrayList<>();
    private static RatingService instance;
    private static int nextId = 1;
    private RatingService(){}
    public static RatingService getInstance(){
        if(instance==null)instance = new RatingService();
        return instance;
    }

    @Override
    public int save(Rating object) {
        if(object.getId()==0){
            object.setId(nextId++);
            ratingList.add(object);
//            Event event = EventService.getInstance().load(object.getIdActivity());
//            event.addRating(object);
        }
        return 0;
    }

    @Override
    public Rating load(int id) {
        for(Rating r:ratingList){
            if(r.getId()==id)return r;
        }
        return null;
    }

    @Override
    public List<Rating> getAll() {
        return Collections.unmodifiableList(ratingList);
    }
}
