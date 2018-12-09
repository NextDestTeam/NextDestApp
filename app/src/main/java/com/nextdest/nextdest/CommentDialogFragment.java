package com.nextdest.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.nextdest.model.Comment;
import com.nextdest.model.Rating;
import com.nextdest.service.CommentService;
import com.nextdest.service.RatingService;
import com.nextdest.view.model.CommentDialogViewModel;

public class CommentDialogFragment extends DialogFragment {

    private Integer idActivity;
    private Integer idUser;
    private RatingBar rating;
    private EditText comment;
    private Button btCancel;
    private Button btComment;

    private CommentDialogViewModel mViewModel;

    public CommentDialogFragment(){}

    public static CommentDialogFragment newInstance(Integer idActivity, Integer idUser) {

        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();

        commentDialogFragment.idUser = idUser;
        commentDialogFragment.idActivity = idActivity;

        return commentDialogFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.comment_dialog_fragment, container, false);
    }

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        comment = view.findViewById(R.id.etCommentDialogComment);
        rating = view.findViewById(R.id.rbCommentDialogRating);
        btCancel = view.findViewById(R.id.btCommentDialogCancel);
        btComment = view.findViewById(R.id.btCommentDialogComment);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!comment.getText().toString().trim().isEmpty()){
                    Comment c = new Comment();
                    c.setIdActivity(idActivity);
                    c.setIdUser(idUser);
                    c.setComment(comment.getText().toString());
                    CommentService.getInstance().save(c);
                //}
                Rating r = new Rating();
                r.setIdActivity(idActivity);
                r.setIdUser(idUser);
                r.setRating(rating.getRating());
                RatingService.getInstance().save(r);
                dismiss();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CommentDialogViewModel.class);

        comment.setText(mViewModel.getComment());
        rating.setRating(mViewModel.getRating());
    }

}
