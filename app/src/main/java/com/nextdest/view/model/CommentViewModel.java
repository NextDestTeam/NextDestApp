package com.nextdest.view.model;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.BitmapDrawable;

public class CommentViewModel extends ViewModel {
    private String comment;
    private float rating;
    private BitmapDrawable photo;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public BitmapDrawable getPhoto() {
        return photo;
    }

    public void setPhoto(BitmapDrawable photo) {
        this.photo = photo;
    }
}
