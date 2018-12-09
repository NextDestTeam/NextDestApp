package com.nextdest.nextdest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nextdest.view.model.CommentViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private CommentViewModel mViewModel;

    private CircleImageView photo;
    private TextView comment;
    private RatingBar ratingBar;

    public CommentViewHolder(View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.commentFragmentPhoto);
        comment = itemView.findViewById(R.id.commentFragmentComment);
        ratingBar = itemView.findViewById(R.id.commentFragmentRatingBar);
    }




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        photo = container.findViewById(R.id.commentFragmentPhoto);
        comment = container.findViewById(R.id.commentFragmentComment);
        ratingBar = container.findViewById(R.id.commentFragmentRatingBar);
        comment.setText(mViewModel.getComment());
        ratingBar.setRating(mViewModel.getRating());
        photo.setImageDrawable(mViewModel.getPhoto());
        return inflater.inflate(R.layout.comment_item, container, false);
    }


    public CircleImageView getPhoto() {
        return photo;
    }

    public void setPhoto(CircleImageView photo) {
        this.photo = photo;
    }

    public TextView getComment() {
        return comment;
    }

    public void setComment(TextView comment) {
        this.comment = comment;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }
}
