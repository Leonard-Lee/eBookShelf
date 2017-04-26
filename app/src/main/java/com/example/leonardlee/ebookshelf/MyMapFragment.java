package com.example.leonardlee.ebookshelf;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by leonardlee on 23/04/2017.
 */

public class MyMapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap map;
    private MapView mMapView;
    private String[] bookArray = {"Android Studio Cookbook",
            "Android Programming for Beginners",
            "Head First Android Development",
            "Head First Design Patterns"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Call getMapAsync() on the fragment to register the callback
        mMapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(37.4473, -122.12179);
        map.addMarker(new MarkerOptions().position(position).title(bookArray[0]));
        LatLng position2 = new LatLng(37.4473, -122.12379);
        map.addMarker(new MarkerOptions().position(position2).title(bookArray[1]));
        LatLng position3 = new LatLng(37.4573, -122.12179);
        map.addMarker(new MarkerOptions().position(position3).title(bookArray[2]));
        LatLng position4 = new LatLng(37.4573, -122.12479);
        map.addMarker(new MarkerOptions().position(position4).title(bookArray[3]));

        int ZOOM_LEVEL = 14;
        // In your case, if you want to apply different zoom for different type, make a switch case based on type and set the zoom value
        map.animateCamera(CameraUpdateFactory.newLatLng(position));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LEVEL));
        map.getUiSettings().setZoomControlsEnabled(true);
    }
}
