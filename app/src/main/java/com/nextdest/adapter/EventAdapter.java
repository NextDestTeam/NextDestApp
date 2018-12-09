package com.nextdest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.model.Event;
import com.nextdest.nextdest.R;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(@NonNull Context context, int resource, List<Event> objects) {
        super(context, resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_event_item,parent,false);
            }

            Event currentEvent = getItem(position);

            TextView tvItemName = convertView.findViewById(R.id.tvListEventItemName);
            tvItemName.setText(currentEvent.getName());

            TextView tvItemDescription =
                    convertView.findViewById(R.id.tvListEventItemDescription);
            tvItemDescription.setText(currentEvent.getShortDescription());

            ImageView img = convertView.findViewById(R.id.ivListEventItemPhoto);
            Bitmap bMap = BitmapFactory.decodeByteArray(currentEvent.getPhoto(), 0, currentEvent.getPhoto().length);
            img.setImageBitmap(bMap);

            return convertView;
    }
}
