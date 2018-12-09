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
    public static ReactionService getInstance(){
        if(instance==null)instance = new ReactionService();
        return instance;
    }

    @Override
    public void save(Reaction object) {
        List<Reaction> userReaction = getUserReactions(object.getIdUser());
        if(object.getId()==0 && userReaction.size()==0){
            object.setId(nextId++);
            reactionList.add(object);
        }

        for (Reaction reaction :
                userReaction) {
            if (reaction.getIdActivity() == object.getIdActivity()) {
                reaction.setReaction(object.getReaction());
            }
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

    private List<Reaction> getUserReactions(int userId){
        List<Reaction> result = new ArrayList<>();
        for (Reaction reaction :
                reactionList) {
            if(reaction.getIdUser()==userId)result.add(reaction);
        }
        return result;
    }

    private List<Reaction> getActivityReactions(int activityId){
        List<Reaction> result = new ArrayList<>();
        for (Reaction reaction :
                reactionList) {
            if(reaction.getIdActivity()==activityId)result.add(reaction);
        }
        return result;
    }
}
