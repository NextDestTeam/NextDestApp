package com.nextdest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Event {

    private int id;
    private String type;
    private String name;
    private Date date;
    private String shortDescription;
    private String description;
    private double cost;
    private String location;
    private byte[] photo;
    private List<Comment> commentList = new ArrayList<>();
    private List<Rating> ratingList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void addComment(Comment object) {
        commentList.add(object);
    }

    public void addRating(Rating rating){
        ratingList.add(rating);
    }

    public List<Comment> getCommentList(){
        return Collections.unmodifiableList(commentList);
    }

    public List<Rating> getRatingList(){
        return Collections.unmodifiableList(ratingList);
    }

}
