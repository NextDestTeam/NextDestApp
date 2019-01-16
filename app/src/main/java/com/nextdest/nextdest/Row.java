package com.nextdest.nextdest;

import android.graphics.Bitmap;

import com.nextdest.model.Comment;
import com.nextdest.model.Rating;
import com.nextdest.model.Reaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 10/31/2017.
 */

public class Row {

    private String name;
    private String date;
    private int Price;
    private String place;
    private String detal;
    private Bitmap image;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Reaction> getReactionList() {
        return reactionList;
    }

    public void setReactionList(List<Reaction> reactionList) {
        this.reactionList = reactionList;
    }

    private List<Comment> commentList = new ArrayList<>();
    private List<Rating> ratingList = new ArrayList<>();
    private List<Reaction> reactionList = new ArrayList<>();


    public Row(String name, String date, int price, String place, String detal, Bitmap image) {
        this.name = name;
        this.date = date;
        this.Price = price;
        this.place = place;
        this.detal = detal;
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetal() {
        return detal;
    }

    public void setDetal(String detal) {
        this.detal = detal;
    }


}

