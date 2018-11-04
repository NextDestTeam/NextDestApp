package com.nextdest.service;

import com.nextdest.form.EventForm;
import java.util.ArrayList;
import java.util.List;

public class EventFormService {

    public static List<EventForm> events = new ArrayList<EventForm>();
    public static int index = 1;
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

}
