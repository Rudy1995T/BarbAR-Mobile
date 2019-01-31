package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostUser extends AsyncTask <String, Void, JSONObject> {

    private Context context;
    private Class path;

    public PostUser(Context context, boolean isTrainee) {
        Log.d("TEST", "LOG");
        this.context = context.getApplicationContext();
        this.path = isTrainee ? ApprenticeHomeScreenActivity.class : LoginActivity.class;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject json = null;

        try {
            String message = getStream(strings[0], strings[1]);
            json = new JSONObject(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject json) {

        try {
            if(json.getString("message").equals("failed")) {
                Toast.makeText(context, json.getString("error"), Toast.LENGTH_LONG).show();
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putString("sign_up_details", json.toString());
        Intent intent = new Intent(context, path);
        intent.putExtras(bundle);
        Log.d("USER DETAILS SIGN-UP", bundle.getString("sign_up_details"));
        context.startActivity(intent);
    }

    private String getStream(final String LOGIN_URL, String json) {

        String response = null;
        try {
            URL url = new URL(LOGIN_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            Log.d("INPUT STREAM", inputStream.toString());

            if(inputStream == null) return null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();

            String line;
            while((line = reader.readLine()) != null) {
                Log.d("BUFFER", line);
                stringBuffer.append(line);
            }

            response = stringBuffer.toString();

        } catch (MalformedURLException e) {
            Log.d("getStream", e.toString());
        } catch (IOException e) {
            Log.d("getStream", e.toString());
        }

        return response;
    }

}

