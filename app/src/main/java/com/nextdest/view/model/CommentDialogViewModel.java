package com.nextdest.view.model;

import android.arch.lifecycle.ViewModel;

public class CommentDialogViewModel extends ViewModel {
    private String comment;
    private float rating;

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
}
