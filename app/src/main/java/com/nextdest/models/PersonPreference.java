package com.nextdest.models;

public class PersonPreference {
    private Integer id;
    private Integer personPreferenceId;
    private Integer activityTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonPreferenceId() {
        return personPreferenceId;
    }

    public void setPersonPreferenceId(Integer personPreferenceId) {
        this.personPreferenceId = personPreferenceId;
    }

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }
}
