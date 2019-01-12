package com.nextdest.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.nextdest.R;
import com.nextdest.view.model.ReactionViewModel;

import java.util.List;

public class ReactionAdapter extends ArrayAdapter<ReactionViewModel>{


    public ReactionAdapter(@NonNull Context context, int resource, @NonNull List<ReactionViewModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.reaction_item,parent,false);
        }

        ReactionViewModel current = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.ivReactionItemImage);
        imageView.setImageDrawable(current.getDrawable());

        TextView textView = convertView.findViewById(R.id.tvReactionItem);
        textView.setText(current.getText());

        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.reaction_item,parent,false);
        }

        ReactionViewModel current = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.ivReactionItemImage);
        imageView.setImageDrawable(current.getDrawable());

        TextView textView = convertView.findViewById(R.id.tvReactionItem);
        textView.setText(current.getText());

        return convertView;
    }

}
