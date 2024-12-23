package com.des1gn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Http {

    private static final String POST = "POST";
    private static final String CHARSET = "utf-8";



    public static String HttpPost(String url,String body) throws IOException {
        HttpURLConnection urlConnection = null;
        try {
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "1080");
            System.setProperty("https.proxyHost", "127.0.0.1");
            System.setProperty("https.proxyPort", "1080");
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod(POST);
            urlConnection.setRequestProperty("Content-Type","application/json; utf-8");
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            OutputStream os = urlConnection.getOutputStream();
            byte[] input = body.getBytes(CHARSET);
            os.write(input, 0, input.length);
            os.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder resultBuilder = new StringBuilder();
            String responseLine = null;
            while ((responseLine = bufferedReader.readLine()) != null){
                resultBuilder.append(responseLine.trim());
            }
            return resultBuilder.toString();
        } catch (IOException e) {
            throw e;
        }
    }
}
