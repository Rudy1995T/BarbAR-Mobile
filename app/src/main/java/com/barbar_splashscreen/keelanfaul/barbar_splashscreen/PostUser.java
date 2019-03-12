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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PostUser extends AsyncTask <String, Void, JSONObject> {

    private Context context;
    private Class path;

    public PostUser(Context context, boolean isTrainee) {
        Log.d("TEST", "LOG");
        this.context = context.getApplicationContext();
        this.path = LoginActivity.class;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        return readStream(strings[0], strings[1]);
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

    private JSONObject readStream(final String LOGIN_URL, String json) {

        JSONObject response = null;
        try {

            InputStream inputStream = getStream(LOGIN_URL, json);

//            Log.d("INPUT STREAM", inputStream.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            response = new JSONObject(reader.readLine());
        } catch (MalformedURLException e) {
            Log.e("getStream", e.toString(), e);
        } catch (IOException e) {
            Log.e("getStream", e.toString(), e);
        } catch (JSONException e) {
            Log.e("getStream", e.toString(), e);
        }

        return response;
    }

    private InputStream getStream(final String LOGIN_URL, String json) {

        InputStream inputStream = null;
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

            Log.d("RESPONSE", "" + urlConnection.getResponseCode());

            inputStream = urlConnection.getInputStream();
        } catch (UnsupportedEncodingException e) {
            Log.e("getStream", e.toString(), e);
        } catch (MalformedURLException e) {
            Log.e("getStream", e.toString(), e);
        } catch (ProtocolException e) {
            Log.e("getStream", e.toString(), e);
        } catch (IOException e) {
            Log.e("getStream", e.toString(), e);
        }

        return inputStream;
    }

}

