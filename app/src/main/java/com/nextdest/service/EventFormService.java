package com.nextdest.service;

import com.nextdest.form.EventForm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventFormService {

    public static List<EventForm> events = new ArrayList<EventForm>();
    public static int index = 1;
    private static EventFormService instance = null;

    private EventFormService(){

    }

    public void save(EventForm eventForm) {
        if(eventForm.getId()==0) {
            eventForm.setId(index++);
            events.add(eventForm);
        }
    }

    public EventForm load(int id){
        for (EventForm event :
                events) {
            if(event.getId()==id)return event;
        }
        return new EventForm();
    }

    public List<EventForm> getEvents(){
        return Collections.unmodifiableList(events);
    }

    public static EventFormService getInstance(){
        if(instance==null)instance = new EventFormService();
        return instance;
    }


}
