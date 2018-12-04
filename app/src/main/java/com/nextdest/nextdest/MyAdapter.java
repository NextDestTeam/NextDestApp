package com.nextdest.nextdest;

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


    ArrayList<Row> list;
        Context context;


        public MyAdapter(ArrayList<Row> list, Context context) {
            this.list = list;
            this.context = context;


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.detail.setText(list.get(position).getDetal());
            holder.name.setText(list.get(position).getName());
            holder.Price.setText(list.get(position).getPrice());
            holder.time.setText(list.get(position).getTime());
            holder.date.setText(list.get(position).getDate());
            holder.place.setText(list.get(position).getPlace());
            holder.imag.setImageResource(list.get(position).getId());
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
              public void onClick(View v) {
                    Intent intent = new Intent(context,SelectedEventActivity.class);
                    intent.putExtra("image", list.get(position).getId());
                    intent.putExtra("detail", list.get(position).getDetal());
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("Price", list.get(position).getPrice());
                    intent.putExtra("time", list.get(position).getTime());
                    intent.putExtra("date", list.get(position).getDate());
                    intent.putExtra("place", list.get(position).getId());
                    context.startActivity(intent);


                }
            });

        }


        @Override
        public int getItemCount() {
            return list.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder  {

            public TextView detail;
            public TextView name;
            public TextView Price;
            public TextView time;
            public TextView date;
            public TextView place;
            public ImageView imag;
            public LinearLayout parentLayout;


            public ViewHolder(View itemView) {
                super(itemView);

                detail = itemView.findViewById(R.id.details);
                name = itemView.findViewById(R.id.name);
                Price = itemView.findViewById(R.id.price);
                time = itemView.findViewById(R.id.Time);
                date = itemView.findViewById(R.id.date);
                place = itemView.findViewById(R.id.place);
                imag = itemView.findViewById(R.id.imageView);
                parentLayout = itemView.findViewById(R.id.parent_layout);

            }



        }
    }



