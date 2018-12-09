package com.nextdest.service;

import com.nextdest.model.Reaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReactionService implements IService<Reaction> {

    private static List<Reaction> reactionList = new ArrayList<>();
    private static ReactionService instance;
    private static int nextId = 1;
    private ReactionService(){    }
    public ReactionService getInstance(){
        if(instance==null)instance = new ReactionService();
        return instance;
    }

    @Override
    public void save(Reaction object) {
        if(object.getId()==0){
            object.setId(nextId++);
            reactionList.add(object);
        }
    }

    @Override
    public Reaction load(int id) {
        for (Reaction r :
                reactionList) {
            if(r.getId()==id)return r;
        }
        return null;
    }

    @Override
    public List<Reaction> getAll() {
        return Collections.unmodifiableList(reactionList);
    }
}
