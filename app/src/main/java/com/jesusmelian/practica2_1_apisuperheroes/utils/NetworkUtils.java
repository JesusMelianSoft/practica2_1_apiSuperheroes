package com.jesusmelian.practica2_1_apisuperheroes.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String GITHUB_BASE_URL="https://akabab.github.io/superhero-api/api/";
    final static String ALL= "all.json";
    final static String FILTRO= "id";

    public static URL buildUrl(String githubSearchQuery){
        Uri builturi = Uri.parse(GITHUB_BASE_URL+ALL).buildUpon()
                .appendQueryParameter(ALL, githubSearchQuery)
                .build();
        URL url = null;
        try {
            url = new URL(builturi.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = urlConnection.getInputStream();
        Scanner sc = new Scanner(in);
        sc.useDelimiter("\\A");

        try {
            if(sc.hasNext()){
                return sc.next();
            }else{
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
