package com.example.leonardlee.ebookshelf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by leonardlee on 10/05/2017.
 */

public class CustomBookAdapter extends ArrayAdapter<Book> {
    private Context context;

    public CustomBookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        // Text View
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        // Populate the data into the template view using the data object
        tvName.setText(book.getBookname());

        // Button
        Button btButton = (Button) convertView.findViewById(R.id.btnPDF);
        // Cache row position inside the button using `setTag`
        btButton.setTag(book.getPath());
        // Attach the click event handler
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = (String) view.getTag();
                Log.d("Path: ", path);

                // Open PDF
//                File file = new File(path);
//                Uri uri = Uri.fromFile(file);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

//                Please see the Javadoc of the method setType(String type) in the class Intent:
//                This method automatically clears any data that was previously set (for example by setData(Uri)).
//                intent.setType("application/pdf");

//                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                context.startActivity(intent);

                File pdfFile = new File(path);//File path
                if (pdfFile.exists()) //Checking for the file is exist or not
                {
                    Uri uri = Uri.fromFile(pdfFile);
                    Intent objIntent = new Intent(Intent.ACTION_VIEW);
                    objIntent.setDataAndType(uri, "application/pdf");
                    objIntent.setFlags(Intent. FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(objIntent);//Staring the pdf viewer
                } else {

//                    Toast.makeText(getActivity(), "The file not exists! ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
