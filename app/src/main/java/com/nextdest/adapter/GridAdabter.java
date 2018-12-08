package com.nextdest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.nextdest.R;

public class GridAdabter extends BaseAdapter {


    Context context;

    private final int [] images;
    private final String [] values;
    View view;
    LayoutInflater layoutInflater;


    public GridAdabter(Context context, int[] images ,String [] values) {
        this.context = context;
        this.images = images;
        this.values=values;
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
        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null){
            view=new View(context);
            view=layoutInflater.inflate(R.layout.single_item , null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageSingleItem);
            TextView textView= (TextView) view.findViewById(R.id.textSingleItem);
            imageView.setImageResource(images[position]);
            textView.setText(values[position]);



        }
        return view;
    }


}
