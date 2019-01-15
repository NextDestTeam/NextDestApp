package com.nextdest.models;

public class Reaction {
    private Integer id;
    private String reaction;
    private Integer personReactionId;
    private Integer activityReactionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public Integer getPersonReactionId() {
        return personReactionId;
    }

    public void setPersonReactionId(Integer personReactionId) {
        this.personReactionId = personReactionId;
    }

    public Integer getActivityReactionId() {
        return activityReactionId;
    }

    public void setActivityReactionId(Integer activityReactionId) {
        this.activityReactionId = activityReactionId;
    }
}
