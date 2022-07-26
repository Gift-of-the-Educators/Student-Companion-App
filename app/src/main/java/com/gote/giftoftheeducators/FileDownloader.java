package com.gote.giftoftheeducators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FileDownloader {
    private static final int MegaByte = 1024 * 1024;
    public static void downloadFile(String fileUrl, File Directory){
        try{
            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(Directory);
            int totalSize = urlConnection.getContentLength();
            byte[] buffer = new byte[MegaByte];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer)) > 0 ){
                outputStream.write(buffer, 0, bufferLength);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}



