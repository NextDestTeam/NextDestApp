package com.nextdest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class likeFragment extends Fragment {
    ArrayList dataModels;
    ListView listView;
    private CustomAdapter adapter;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_like, container, false);
        listView= (ListView) rootView.findViewById(R.id.listView);
        dataModels = new ArrayList();

        dataModels.add(new DataModel("Music", true));
        dataModels.add(new DataModel("Sport", false));
        dataModels.add(new DataModel("Fun Time", false));
        dataModels.add(new DataModel("Natural", false));
        adapter = new CustomAdapter(dataModels,getContext(). getApplicationContext());
         listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                DataModel dataModel= (DataModel) dataModels.get(position);
                dataModel.checked = !dataModel.checked;
                adapter.notifyDataSetChanged();


            }
        });

        return rootView;
    }

}
