package com.example.leonardlee.ebookshelf;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardlee on 23/04/2017.
 */

public class DownloadFragment extends Fragment {
    EditText txtURL1, txtURL2, txtURL3, txtURL4, txtURL5;
    IntentFilter intentFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_download, container, false);
        txtURL1 = (EditText) rootView.findViewById(R.id.txtURL1);
        txtURL2 = (EditText) rootView.findViewById(R.id.txtURL2);
        txtURL3 = (EditText) rootView.findViewById(R.id.txtURL3);
        txtURL4 = (EditText) rootView.findViewById(R.id.txtURL4);
        txtURL5 = (EditText) rootView.findViewById(R.id.txtURL5);

        intentFilter = new IntentFilter();
        intentFilter.addAction("FILE_DOWNLOADED_ACTION");
        getActivity().registerReceiver(intentReceiver, intentFilter);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();

        // set up the URLs
        txtURL5.setText("http://www.cisco.com/web/offer/emear/38586/images/Presentations/P3.pdf");
        txtURL2.setText("http://www.cisco.com/web/about/ac79/docs/innov/IoE_Economy.pdf");
        txtURL3.setText("http://www.cisco.com/c/dam/en_us/solutions/industries/docs/gov/everything-for-cities.pdf");
        txtURL4.setText("http://www.cisco.com/web/offer/gist_ty2_asset/Cisco_2014_ASR.pdf");
//        txtURL1.setText("http://www.cisco.com/c/dam/en_us/about/annual-report/2016-annual-report-full.pdf");
        txtURL1.setText("http://www.cisco.com/web/offer/gist_ty2_asset/Cisco_2014_ASR.pdf");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void btnDownloadClicked(View v) {
        String url1 = txtURL1.getText().toString();
        String url2 = txtURL2.getText().toString();
        String url3 = txtURL3.getText().toString();
        String url4 = txtURL4.getText().toString();
        String url5 = txtURL5.getText().toString();

        // MyDownloadService extends Service
        Intent intent = new Intent(getActivity(), MyDownloadService.class);

        try {
            List<URL> urls = new ArrayList();

            if (url1 != "") {
                urls.add(new URL(url1));
            }

            if (url2 != "") {
                urls.add(new URL(url2));
            }

            if (url3 != "") {
                urls.add(new URL(url3));
            }

            if (url4 != "") {
                urls.add(new URL(url4));
            }

            if (url5 != "") {
                urls.add(new URL(url5));
            }

            intent.putExtra("URLs", urls.toArray());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Unbounded Service
        getActivity().startService(intent);
    }

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getActivity(), "File downloaded!",
                    Toast.LENGTH_LONG).show();
        }
    };
}
