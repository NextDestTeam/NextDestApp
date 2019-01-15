package com.nextdest.nextdest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class Filters extends Fragment {

    private String city;
    private String ActivityType;
    private Double price;
    ListView listView;
    Cursor cursor;
    DB db;
    public String test1;
    public int test2;
    int My_cost = 0;
    int Activity_Type;
    Context context;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
    ArrayList<Row> items = new ArrayList<>();
    //     DB db;
    int id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinnerCost);
        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinnerActivity);
        Button btn = (Button) view.findViewById(R.id.btn_search);
        final List<String> searchResult=new ArrayList<>();
        db=new DB(getActivity().getApplicationContext());
        Integer[] cost = new Integer[]{10,20,30,40,50};
        //cursor=db.get_ACTIVITY();

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_spinner_item, cost);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(view.getContext(), R.array.activity_array,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner3.setAdapter(adapter3);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Object item = parentView.getItemAtPosition(position);
                My_cost=Integer.parseInt(item .toString());

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });





        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Object item = parentView.getItemAtPosition(position);
                String type=item.toString();
                if(type.equals("Music")){
                    Activity_Type=1;
                }
                else if(type.equals("Sports")){
                    Activity_Type=2;
                }
                else if(type.equals("Fun")){
                    Activity_Type=3;
                }
                else Activity_Type=4;

            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                db=new DB(getActivity().getApplicationContext());

                Cursor cursor = db.get_ACTIVITY_filter(My_cost,Activity_Type);
                while(cursor.moveToNext()) {
                    id = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.KEY_ID));
                    String name = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.NAME));
                    String DESCRIPTION = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.DESCRIPTION));
                    String LOCATION = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LOCATION));
                    int PRICE = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.PRICE));
                    String Date = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.Date));
                    Cursor c_image=db.get_ACTIVITY_IMAGE(id);
                    byte[] image = c_image.getBlob(1);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    //Row row=new Row(name,Date,PRICE,LOCATION,DESCRIPTION,bitmap);
                    //   items.add(row);
                    recyclerView.setAdapter(new MyAdapter(items, new MyAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Row items) {
                            Intent intent = new Intent(getActivity(),RecActivity.class);
                            intent.putExtra("image",items.getImage());
                            intent.putExtra("detail", items.getDetal());
                            intent.putExtra("name", items.getName());
                            intent.putExtra("Price", items.getPrice());
                            intent.putExtra("date", items.getDate());
                            intent.putExtra("place", items.getPlace());
                            startActivity(intent);}
                    }));

                }


            }
        });






        return view;
    }


}