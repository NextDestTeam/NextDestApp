package com.nextdest.nextdest;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.model.Event;

import java.util.List;

public class GridAdapter extends BaseAdapter {


    Context context;

    private final List<Event> eventList;



    View view;
    LayoutInflater layoutInflater;
    public static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }


    public GridAdapter(Context context, List<Event> events) {
        this.context = context;
        this.eventList = events;
        ;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GridAdapter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageSingleItem);
            TextView textView = (TextView) convertView.findViewById(R.id.textSingleItem);
            result=convertView;
            convertView.setTag(viewHolder);

            byte[] photoBytes = eventList.get(position).getPhoto();
            BitmapDrawable bitmapDrawable = new BitmapDrawable(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length));
            imageView.setImageDrawable(bitmapDrawable);

            textView.setText(eventList.get(position).getName());
        }

        return convertView;
        }




}
