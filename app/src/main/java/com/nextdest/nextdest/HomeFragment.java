package com.nextdest.nextdest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;


public class HomeFragment extends Fragment {


    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      final   int images []= {R.drawable.barca,R.drawable.festival,R.drawable.mou,R.drawable.food,R.drawable.tennis,R.drawable.party, R.drawable.tango ,R.drawable.marathon ,R.drawable.cars ,R.drawable.flam ,R.drawable.theater , R.drawable.galler};
        final  String values [] = {
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

      final   String des[] = {"El Classico match Real Madrid (VS) Barcelona" , " you can attend this party now in ... " , "amazing trip to wild "
                                ,"Good Plase to have a diner with friends","For tines lovers her yo have a big match ","Classic Concert with (Mark) ", "Tango party with a free wine "};
      final   String cost[]= {"100$" , "35$" , "40$", "50$" ,"25$","30$","25$"};
        final String location []= {"Barcelona Camp nou"," Llieda Main Center" , " Taragona " ,"Llieda "," Barcelona","Llieda"};
        final  String date []= {"26/11/2018" , "3/1/2019" , " 12/12/2018" , "12/12/2018","2/1/2019","4/1/2019","27/12/2018"};

        Button btSearch;
        GridView gridView;



        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        gridView= (GridView) v.findViewById(R.id.gridView);
        GridAdabter gridAdabter = new GridAdabter (context , images ,values);
        gridView.setAdapter(gridAdabter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity() ,SelectedEventActivity.class);
                intent.putExtra("ivPhoto", images[i]);
                intent.putExtra("tvName", values[i]);
                intent.putExtra("tvShortDescription", des[i]);
                intent.putExtra("tvCost",cost[i]);
                intent.putExtra("tvDate",date[i]);
                intent.putExtra("tvLocation",location[i]);
                startActivity(intent);
            }
        });

        return v;

    }



}
