package com.nextdest.nextdest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;

import java.util.ArrayList;


public class NaturalFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
    ArrayList<Row> items = new ArrayList<>();
    DB db=new DB(getActivity().getApplicationContext());
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_natural, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        Cursor cursor = db.get_ACTIVITY_id(4);
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.NAME));
            String DESCRIPTION = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.DESCRIPTION));
            String LOCATION = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LOCATION));
            int PRICE = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.PRICE));
            String Date = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.Date));
            Cursor c_image = db.get_ACTIVITY_IMAGE(id);
            byte[] image = c_image.getBlob(1);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            Row row = new Row(name, Date, PRICE, LOCATION, DESCRIPTION, bitmap);
            items.add(row);

        }/*

        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.flam));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.party));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.music));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.tango));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.music2));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
        items.add(new Row("music","23 diciembre - 19h",10,"llieda","Christmas.catCor de l’Orfeó Lleidatà","12:00pm",R.drawable.dance));
*/
        recyclerView.setAdapter(new MyAdapter(items, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Row items) {
                Intent intent = new Intent(getActivity(), RecActivity.class);
                intent.putExtra("image", items.getImage());
                intent.putExtra("detail", items.getDetal());
                intent.putExtra("name", items.getName());
                intent.putExtra("Price", items.getPrice());
                intent.putExtra("date", items.getDate());
                intent.putExtra("place", items.getPlace());
                startActivity(intent);
            }
        }));


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //toolbar.setTitle("Music Event");
    }
}
