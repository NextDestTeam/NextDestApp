package com.nextdest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.nextdest.GridAdabter;
import com.nextdest.nextdest.R;

public class SearchAdabter extends BaseAdapter {


    Context context;

    private final int [] imagestemp;
    private final String [] valuestemp;



    View view;
    LayoutInflater layoutInflater;
    public static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }


    public SearchAdabter(Context context, int[] imagestemp, String[] valuestemp) {
        this.context = context;
        this.imagestemp = imagestemp;
        this.valuestemp=valuestemp;
        ;
    }

    @Override
    public int getCount() {
        return valuestemp.length;
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
            viewHolder = new GridAdabter.ViewHolder();
            convertView = layoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageSingleItem);
            TextView textView = (TextView) convertView.findViewById(R.id.textSingleItem);
            result=convertView;
            convertView.setTag(viewHolder);
            imageView.setImageResource(imagestemp[position]);
            textView.setText(valuestemp[position]);
        }

        return convertView;
    }

}
