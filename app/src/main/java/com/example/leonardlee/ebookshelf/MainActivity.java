package com.example.leonardlee.ebookshelf;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;

import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_download:
                    fragment = new DownloadFragment();
                    break;
                case R.id.navigation_booklist:
                    fragment = new BooklistFragment();
                    break;
                case R.id.navigation_map:
//                    MapFragment mapFragment = new MapFragment();
//                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.content, mapFragment).commit();
//                    return true;
                    fragment = new MyMapFragment();
                    break;
                default:
                    fragment = new DownloadFragment();
                    break;
            }
            final FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragment = new DownloadFragment();
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment).commit();
    }

    // Button Click Event
    public void btnDownloadClicked(View v) {
        if (fragment instanceof DownloadFragment) {
            ((DownloadFragment) fragment).btnDownloadClicked(v);
        }
    }
}
