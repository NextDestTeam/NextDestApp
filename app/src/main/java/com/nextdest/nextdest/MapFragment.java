package com.nextdest.nextdest;


import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nextdest.nextdest.R;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final int PERMISSION_MAP = 1;
//    private static final String API_KEY = "AIzaSyAGQrj8FzLQsI1_HtONxGq4_LUg1JYUNu0";

    MapView mapView;
    GoogleMap map;
    private String address = "Carrer Bisbe Torres 2 Lleida";
    private String activityTitle = "Testing Title";
    private OnMapFragmentReadyCallback onMapFragmentReadyCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_map, container, false);


        mapView = v.findViewById(R.id.mapFragmentMapView);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);



        return v;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);


        if (ActivityCompat.checkSelfPermission(this.getActivity(),
                ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this.getActivity(), ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this.getActivity(),
                    new String[]{
                        ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                    PERMISSION_MAP
            );

            if(ActivityCompat.checkSelfPermission(this.getActivity(),
                    ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this.getActivity(), ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this.getActivity(),R.string.without_location_permission,Toast.LENGTH_LONG);
                return;
            }
        }
        map.setMyLocationEnabled(true);

        MapsInitializer.initialize(this.getActivity());



        if(onMapFragmentReadyCallback!=null)onMapFragmentReadyCallback.mapFragmentReady(this);
        else locate(address,activityTitle);
    }

    public void locate(String address, String activityTitle) {
        LatLng latLng = getLatLongFromPlace(address);
        if(latLng!=null) {

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(activityTitle);
            map.addMarker(markerOptions);
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        map.moveCamera(cameraUpdate);
    }


    public LatLng getLatLongFromPlace(String place) {

        Geocoder geocoder = new Geocoder(this.getActivity().getApplicationContext());
        List<Address> address = null;

        try {
            address = geocoder.getFromLocationName(place, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (address == null) {
            return null;
        } else {
            Address location = address.get(0);
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            return latLng;
        }


    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public OnMapFragmentReadyCallback getOnMapFragmentReadyCallback() {
        return onMapFragmentReadyCallback;
    }

    public void setOnMapFragmentReadyCallback(OnMapFragmentReadyCallback onMapFragmentReadyCallback) {
        this.onMapFragmentReadyCallback = onMapFragmentReadyCallback;
    }
}
