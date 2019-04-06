package com.example.newapistackexchange;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private QueryUtils() {
    }


    public static List<Overflow> fetchData(String requestUrl) {

        URL url = createUrl(requestUrl);
        List<Overflow> overflows = null;

        try {
            String jsonResponse = makeHttpRequest(url);
            overflows = extractFeaturesFromJson(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return overflows;
    }

    public static ArrayList<Overflow> extractFeaturesFromJson(String jsonResponse) {

        if(jsonResponse == null) {
            return null;
        }

        ArrayList<Overflow> overflows = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(jsonResponse);
            JSONArray items = object.getJSONArray("items");

            for(int i = 0; i < items.length(); i++) {
                JSONObject owner = items.getJSONObject(i).getJSONObject("owner");
                int ans_count=items.getJSONObject(i).getInt("answer_count");
                int score=items.getJSONObject(i).getInt("score");
                String display_name = owner.getString("display_name");
                String image = owner.getString("profile_image");
                String ques_link=items.getJSONObject(i).getString("link");
                long c_date=items.getJSONObject(i).getLong("creation_date");
                long l_a_date=items.getJSONObject(i).getLong("last_activity_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int view_count=items.getJSONObject(i).getInt("view_count");

                Overflow overflow = new Overflow(ans_count,score,display_name,ques_link,view_count,image,c_date,l_a_date);

                overflows.add(overflow);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return overflows;
    }

    private static URL createUrl(String stringUrl) {

        URL url = null;

        if(stringUrl == null) {
            return url;
        }
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;


        if(url == null) {
            return jsonResponse;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream (InputStream inputStream) {
        InputStreamReader streamReader = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();

        if(inputStream == null) {
            return null;
        }

        streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        reader = new BufferedReader(streamReader);
        try {
            String line = reader.readLine();
            while (line != null) {

                result.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
