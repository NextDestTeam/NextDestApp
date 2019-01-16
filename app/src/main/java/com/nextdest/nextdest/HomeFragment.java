package com.nextdest.nextdest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.nextdest.model.Event;
import com.nextdest.service.EventService;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    MaterialSearchView searchView;
    Context context;
    GridAdapter myAdabter;
    ArrayAdapter<String> adapter;

    private ArrayList<String> valuestemp;
    private ArrayList<Integer> imagestemp;
    private List<Event> eventList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        EventService eventService = new EventService(getContext());

        /*if(EventService.getInstance().getAll().size()==0){
            //generateMockEvents();
        }*/

        Button btSearch;
        GridView gridView;


        eventList = eventService.getAll();


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        gridView= (GridView) v.findViewById(R.id.gridView);
        final GridAdapter gridAdapter = new GridAdapter(context , eventList);
        gridView.setAdapter(gridAdapter);

//test











//end test

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setEnabled(false);
                Event event = eventList.get(i);
                Intent intent = new Intent(getActivity() ,SelectedEventActivity.class);
                intent.putExtra("idEvent", event.getId());
                startActivity(intent);
            }
        });

        return v;

    }

    private void generateMockEvents() {
        final   int images []= {R.drawable.barca,R.drawable.festival,R.drawable.mou,R.drawable.food,R.drawable.tennis,R.drawable.party, R.drawable.tango ,R.drawable.marathon ,R.drawable.cars ,R.drawable.flam ,R.drawable.theater , R.drawable.galler};
        final String values [] = {
                "El Classico",
                "Jazz Party",
                "Natural Trip",
                "Diner ",
                "Tines Match",
                "Classic concert",
                "Tango",
                "Marathon",
                "Cars race",
                "Flamingo",
                "Theater play",
                "Art gallery"
        };

        final String des[] = {"El Classico match Real Madrid (VS) Barcelona" , " you can attend this party now in ... " , "amazing trip to wild "
                ,"Good Plase to have a diner with friends","For tines lovers her yo have a big match ","Classic Concert with (Mark) ", "Tango party with a free wine "};
        final float cost[]= {100f , 35f , 40f, 50f ,25f,30f,25f};
        final String location []= {"Barcelona Camp nou"," Llieda Main Center" , " Taragona " ,"Llieda "," Barcelona","Llieda"};
        final String date []= {"26/11/2018" , "3/1/2019" , " 12/12/2018" , "12/12/2018","2/1/2019","4/1/2019","27/12/2018"};
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");


        try {
            for (int i = 0; i < location.length; i++) {
                Event event = new Event();
                event.setCost(cost[i]);
                event.setDate(sdf.parse(date[i]));
                event.setLocation(location[i]);
                event.setDescription(des[i]);
                event.setShortDescription(values[i]);
                event.setName(values[i]);


                Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(images[i])).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                event.setPhoto(stream.toByteArray());

//                EventService.getInstance().save(event);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
