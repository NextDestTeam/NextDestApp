package com.nextdest.nextdest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NaturalFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
    ArrayList<Row> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_natural, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.mou));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.mou));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h","10$","llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));

        recyclerView.setAdapter(new MyAdapter(items, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Row items) {
                Intent intent = new Intent(getActivity(),SelectedEventActivityRec.class);
                intent.putExtra("image", items.getId());
                intent.putExtra("detail", items.getDetal());
                intent.putExtra("name", items.getName());
                intent.putExtra("Price", items.getPrice());
                intent.putExtra("time", items.getTime());
                intent.putExtra("date", items.getDate());
                intent.putExtra("place", items.getId());
                startActivity(intent);
            }
        }));
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        // toolbar.setTitle("Natural Event");
    }
}
