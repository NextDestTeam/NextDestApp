package com.nextdest.nextdest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchAdapter extends ArrayAdapter<String> {

    String [] eNames;
    int [] eImages;
    Context mContext;

    public SearchAdapter(@NonNull Context context, String [] eventNames, int [] eventImages ){
        super(context, R.layout.search_list_item);
        this.eNames = eventNames;
        this.eImages = eventImages;
        this.mContext =context;

    }

    @Override
    public int getCount() {
        return eNames.length;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = new ViewHolder();

        if (convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.search_list_item, parent, false);
            mViewHolder.mImages = (ImageView) convertView.findViewById(R.id.act_image);
            mViewHolder.mNames = (TextView) convertView.findViewById(R.id.actTextName);
            convertView.setTag(mViewHolder);

        }else {
            mViewHolder = (ViewHolder)convertView.getTag();
        }
        mViewHolder.mImages.setImageResource(eImages[position]);
        mViewHolder.mNames.setText(eNames[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView mImages;
        TextView mNames;

    }
}
