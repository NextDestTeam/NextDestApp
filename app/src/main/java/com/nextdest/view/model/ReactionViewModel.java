package com.nextdest.view.model;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import com.nextdest.model.ReactionType;

public class ReactionViewModel extends ViewModel {

    private Drawable drawable;
    private String text;
    private ReactionType code;

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ReactionType getCode() {
        return code;
    }

    public void setCode(ReactionType code) {
        this.code = code;
    }
}
