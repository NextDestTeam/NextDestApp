package com.nextdest.nextdest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class PopularEventNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {







    ListView testview;


    MaterialSearchView searchView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_event_nav);

        //test


        final List<String> values1=new ArrayList<>();
        values1.add("jazz_party");
        values1.add("El Classico");
        values1.add("jazz_festival");
        values1.add("Natural Trip");
        values1.add("Diner");
        values1.add(" Tines match");
        values1.add("jazz_international_day");
        values1.add("Flamingo_party");
        values1.add("tango_party");
        values1.add("Boxing 'sports' ");
        values1.add("Art gallery");


        final   int images []= {R.drawable.festival,R.drawable.barca,R.drawable.mou,R.drawable.food,R.drawable.tennis,R.drawable.party, R.drawable.tango ,R.drawable.marathon ,R.drawable.cars ,R.drawable.flam ,R.drawable.theater , R.drawable.galler};


        final List<String> des=new ArrayList<>(); des.add(" you can attend this party now in ... "); des.add( "El Classico match Real Madrid (VS) Barcelona" );
                des.add("amazing trip to wild ");des.add("Good Plase to have a diner with friends"); des.add("For tines lovers her yo have a big match ");

        final   String cost[]= {"100$" , "35$" , "40$", "50$" ,"25$","30$","25$"};
        final List<String> location=new ArrayList<>(); location.add (" Taragona "); location.add("Barcelona Camp nou"); location.add(" Taragona "); location.add("Llieda ");location.add(" Barcelona"); location.add("Llieda");
        final  String date []= {"26/11/2018" , "3/1/2019" , " 12/12/2018" , "12/12/2018","2/1/2019","4/1/2019","27/12/2018"};



        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        getSupportActionBar().setTitle("search");

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        testview =(ListView) findViewById(R.id.testview);



        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {



            }

            @Override
            public void onSearchViewClosed() {


                ArrayAdapter adapter = new ArrayAdapter(PopularEventNav.this, android.R.layout.simple_list_item_1, values1);

                testview.setAdapter(adapter);



            }
        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {



                    toolbar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent= new Intent (getApplicationContext(),searchResult.class);
                            startActivity(intent);


                        }
                    });




                    return false;
                    }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText != null && !newText.isEmpty()) {
                        final List<String> nameFound = new ArrayList<String>();
                        for (String item : values1) {
                            if (item.contains(newText)) {
                                nameFound.add(item);
                            }
                        }

                        ArrayAdapter adapter = new ArrayAdapter(PopularEventNav.this, android.R.layout.simple_list_item_1, nameFound);
                        testview.setAdapter(adapter);




                        testview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent intent= new Intent (getApplicationContext(),SelectedEventActivity.class);
                                Intent intent1 = intent.putExtra("ivPhoto", images[i]);
                                intent.putExtra("tvName", nameFound.get(i));
                                intent.putExtra("tvShortDescription", des.get(i));
                                //intent.putExtra("tvCost",cost[i]);
                                //intent.putExtra("tvDate",date[i]);
                                intent.putExtra("tvLocation", location.get(i));
                                startActivity(intent);

                            }
                        });





                    }



                    return true;
                }


            });





        //end test

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.popular_event_nav, menu);

        //test


        MenuItem item =menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        //end test


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.music:
                fragment = new MusicFragment();
                break;
            case R.id.sport:
                fragment = new SportFragment();
                break;
            case R.id.natur:
                fragment = new NaturalFragment();
                break;
            case R.id.fun:
                fragment = new FunFragment();
                break;
            case R.id.like:
                fragment = new likeFragment();
                break;
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.filters:
                fragment = new Filters();
                break;
            case R.id.profile:
                fragment = new UserProfile();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());

        return true;
    }
}
