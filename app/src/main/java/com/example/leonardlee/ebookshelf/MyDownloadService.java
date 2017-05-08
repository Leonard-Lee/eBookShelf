package com.example.leonardlee.ebookshelf;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by leonardlee on 02/04/2017.
 */

public class MyDownloadService extends Service {
    public URL[] urls;
    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MyDownloadService getService() {
            return MyDownloadService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
        URL[] urls = new URL[objUrls.length];

        for (int i=0; i < objUrls.length; i++) {
            urls[i] = (URL) objUrls[i];
        }
        new BackgroundAsyncTask().execute(urls);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    private long DownloadbyURL(URL url) throws IOException {
        InputStream inputStream = null;
        HttpURLConnection connection = null;

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Content-Type", "application/pdf");
        connection.setRequestProperty("Accept", "application/pdf");
        connection.setRequestMethod("GET");
        int statusCode = connection.getResponseCode();

        if(statusCode == 200) {
//            File sdcard = Environment.getExternalStorageDirectory();
            String fileName = url.toString().substring(url.toString().lastIndexOf('/') + 1);
            File file = new File(getCacheDir(), fileName);

            FileOutputStream fileOutput = new FileOutputStream(file);
            inputStream = connection.getInputStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();
            return file.length();
        }
        else {
            return 0;
        }

    }

    private class BackgroundAsyncTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {
                try {
                    totalBytesDownloaded += DownloadbyURL(urls[i]);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                publishProgress((int) (((i+1) / (float) count) * 100));
            }
            return totalBytesDownloaded;
        }

        protected void onProgressUpdate(Integer... progress) {
            Log.d("Downloading files",
                    String.valueOf(progress[0]) + "% downloaded");
//            Toast.makeText(getBaseContext(),
//                    String.valueOf(progress[0]) + "% downloaded",
//                    Toast.LENGTH_LONG).show();
        }

        protected void onPostExecute(Long result) {
//            Toast.makeText(getActivity(),
//                    "Downloaded " + result + " bytes",
//                    Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }
}
