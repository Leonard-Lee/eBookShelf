package com.example.leonardlee.ebookshelf;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

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

        //get all books info from db
        DBHelper db = new DBHelper(getActivity());
        List<Book> books = db.getAllBooks();

        // Create the adapter to convert the array to views
        CustomBookAdapter adapter = new CustomBookAdapter(getActivity(), books);
        // Attach the adapter to a ListView
        listView.setAdapter(adapter);
        return view;
    }
}
