package com.nextdest.nextdest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    Activity PopularEventNav;

    public interface OnItemClickListener {
        void onItemClick(Row item);
    }


    private final ArrayList<Row> items;
    private final OnItemClickListener listener;

    public MyAdapter(ArrayList<Row> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.bind(items.get(position), listener);


    }


    @Override
    public int getItemCount() {
        return items.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView detail;
        public TextView name;
        public TextView Price;
        public TextView time;
        public TextView date;
        public TextView place;
        public ImageView imag;
        // public LinearLayout parentLayout;



        public ViewHolder(View itemView) {
            super(itemView);

            detail = itemView.findViewById(R.id.details);
            name = itemView.findViewById(R.id.name);
            Price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.Time);
            date = itemView.findViewById(R.id.date);
            place = itemView.findViewById(R.id.place);
            imag = itemView.findViewById(R.id.imageView);
            //parentLayout = itemView.findViewById(R.id.parent_layout);


        }




        public void bind( final Row item, final OnItemClickListener listener) {
            detail.setText(item.getDetal());
            name.setText(item.getName());
           // Price.setText(item.getPrice());
            date.setText(item.getDate());
            place.setText(item.getPlace());
            imag.setImageBitmap(item.getImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}




