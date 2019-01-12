package com.nextdest.nextdest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdabter extends BaseAdapter {


    Context context;

    private final int [] images;
    private final String [] values;



    View view;
    LayoutInflater layoutInflater;
    public static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }


    public GridAdabter(Context context, int[] images, String[] values) {
        this.context = context;
        this.images = images;
        this.values=values;
        ;
    }

    @Override
    public int getCount() {
        return values.length;
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

        GridAdabter.ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageSingleItem);
            TextView textView = (TextView) convertView.findViewById(R.id.textSingleItem);
            result=convertView;
            convertView.setTag(viewHolder);
            imageView.setImageResource(images[position]);
            textView.setText(values[position]);
        }

        return convertView;
        }




}
