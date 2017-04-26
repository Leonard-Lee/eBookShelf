package com.example.leonardlee.ebookshelf;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by leonardlee on 23/04/2017.
 */

public class BooklistFragment extends Fragment {
    private String[] mobileArray = {"Android Studio Cookbook",
                                    "Android Programming for Beginners",
                                    "Head First Android Development",
                                    "Head First Design Patterns"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booklist, container, false);
        ListView listView = (ListView) view.findViewById(R.id.book_list);

//        simple_list_item_1, simple_list_item_2
//        built-in XML layout document that is part of the Android OS, rather than one of your own XML layouts.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                                android.R.layout.simple_list_item_1,
                                                                mobileArray);
        listView.setAdapter(adapter);
        return view;
    }
}
