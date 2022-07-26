package com.gote.giftoftheeducators;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class PDFViewerActivity extends AppCompatActivity {
    public SharedPreferences preferences;
    private static final int REQUEST_STORAGE = 100;
    int visible = 1;
    PDFView pdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        preferences = getSharedPreferences("UserPrefs", this.MODE_PRIVATE);
        changeTheme(preferences.getString("mode", "light"));
        Intent i = getIntent();
        String file = i.getStringExtra("PDFUrl");
        ImageButton download = findViewById(R.id.btnDownload);
        pdfView = findViewById(R.id.pdfView);
        new GetPDFFromURL().execute(file);

        pdfView.setOnClickListener(new View.OnClickListener() {
            ImageButton download = findViewById(R.id.btnDownload);
            @Override
            public void onClick(View v) {
                if(visible == 0){
                    download.setVisibility(View.VISIBLE);
                    visible = 1;
                }
                else{
                    download.setVisibility(View.INVISIBLE);
                    visible = 0;
                }
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    return;
                }
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(file));
                String title = file.substring(file.lastIndexOf("/")+1, file.length());
                request.setTitle(title);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                request.setMimeType("application/pdf");
                request.allowScanningByMediaScanner();
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                downloadManager.enqueue(request);
            }
        });
    }

    private void changeTheme(String mode) {
        ConstraintLayout pdf = findViewById(R.id.PDFLayout);
        LinearLayout loading = findViewById(R.id.PDFLoadingLayout);
        CircleImageView logo = findViewById(R.id.imgPDFLoading);
        ImageButton download = findViewById(R.id.btnDownload);
        if(mode.equals("light")){
            pdf.setBackgroundColor(getResources().getColor(R.color.white));
            loading.setBackgroundColor(getResources().getColor(R.color.white));
            //download.setBackgroundColor(getResources().getColor(R.color.smokey_white));
        }
        else {
            pdf.setBackgroundColor(getResources().getColor(R.color.background_black));
            loading.setBackgroundColor(getResources().getColor(R.color.background_black));
            //download.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private boolean checkPermission(String permission){
        boolean answer = false;
        if(ContextCompat.checkSelfPermission(PDFViewerActivity.this, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PDFViewerActivity.this, new String[]{permission}, REQUEST_STORAGE);
        }
        else if(ContextCompat.checkSelfPermission(PDFViewerActivity.this, permission) == PackageManager.PERMISSION_GRANTED){
            answer = true;
        }
        return answer;
    }

    public class GetPDFFromURL extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            LinearLayout loading = findViewById(R.id.PDFLoadingLayout);
            pdfView.fromStream(inputStream).load();
            ImageButton download = findViewById(R.id.btnDownload);
            loading.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.INVISIBLE);
                    download.setVisibility(View.VISIBLE);
                    pdfView.setVisibility(View.VISIBLE);
                }
            }, 7000);
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];
            String fileName = strings[1];
            String storageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(storageDirectory, "Test");
            folder.mkdir();
            File pdfFile = new File(folder, fileName);
            try{
                pdfFile.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            return;
        }
        else{
            Toast.makeText(PDFViewerActivity.this, "Please Allow Storage Access to Download Files", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", this.getPackageName(), null);
            intent.setData(uri);
            PDFViewerActivity.this.startActivity(intent);
        }
    }
}
