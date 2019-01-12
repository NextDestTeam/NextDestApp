package com.nextdest.service;


import com.nextdest.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventService implements IService<Event>{

    public static List<Event> events = new ArrayList<Event>();
    public static int index = 1;
    private static EventService instance = null;

    private EventService(){

    }

    public void save(Event event) {
        if(event.getId()==0) {
            event.setId(index++);
            events.add(event);
        }
    }

    public Event load(int id){
        for (Event event :
                events) {
            if(event.getId()==id)return event;
        }
        return new Event();
    }

    public List<Event> getAll(){
        return Collections.unmodifiableList(events);
    }

    public static EventService getInstance(){
        if(instance==null)instance = new EventService();
        return instance;
    }


}
