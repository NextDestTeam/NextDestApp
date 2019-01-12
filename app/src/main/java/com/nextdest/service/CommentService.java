package com.nextdest.service;

import com.nextdest.model.Comment;
import com.nextdest.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentService implements IService<Comment>{

    private static List<Comment> commentList = new ArrayList<>();
    private static int nextId = 1;
    private static CommentService instance;
    private CommentService(){}

    public static CommentService getInstance(){
        if(instance==null)instance = new CommentService();
        return instance;
    }

    @Override
    public void save(Comment object) {
        if(object.getId()==0){
            object.setId(nextId++);
            commentList.add(object);
            Event event = EventService.getInstance().load(object.getIdActivity());
            event.addComment(object);
        }
    }

    @Override
    public Comment load(int id) {

        for(Comment comment: commentList){
            if(comment.getId()==id)return comment;
        }
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return Collections.unmodifiableList(commentList);
    }
}
