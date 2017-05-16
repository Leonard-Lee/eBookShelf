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

import java.util.List;

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

        DBHelper db = new DBHelper(getActivity());
        List<Book> books = db.getAllBooks();
        LatLng position = null;

        for(int i=0; i<books.size(); i++) {
            Book book = books.get(i);
            position = new LatLng(book.getLatitude(), book.getLongitude());
            map.addMarker(new MarkerOptions().position(position).title(book.getBookname()));
        }

        int ZOOM_LEVEL = 14;
        // In your case, if you want to apply different zoom for different type, make a switch case based on type and set the zoom value
        if(position != null) {
            map.animateCamera(CameraUpdateFactory.newLatLng(position));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LEVEL));
        }
        map.getUiSettings().setZoomControlsEnabled(true);
    }
}
