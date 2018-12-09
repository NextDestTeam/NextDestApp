package com.nextdest.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextdest.view.model.CommentViewModel;
import com.nextdest.activity.CommentViewHolder;
import com.nextdest.activity.R;

import java.util.List;

public class CommentAdpter extends RecyclerView.Adapter<CommentViewHolder> {

    List<CommentViewModel> commentViewModels;

    public CommentAdpter(List<CommentViewModel> commentViewModels){
        this.commentViewModels = commentViewModels;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.comment_item, parent, false);

        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentViewModel commentViewModel = commentViewModels.get(position);
        holder.getComment().setText(commentViewModel.getComment());
        holder.getRatingBar().setRating(commentViewModel.getRating());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            holder.getPhoto().setBackground(null);
        }
        holder.getPhoto().setImageDrawable(commentViewModel.getPhoto());
    }

    @Override
    public int getItemCount() {
        return commentViewModels.size();
    }
}
