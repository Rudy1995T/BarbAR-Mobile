package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String FIRSTNAME = "firstname";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String fName, String sName, String uEmail){
        editor.putBoolean(LOGIN,true);
        editor.putString(FIRSTNAME, fName);
        editor.putString(SURNAME,sName);
        editor.putString(EMAIL,uEmail);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }
    public void checkLogin(){
        if(!isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
        }
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> user = new HashMap<>();
        user.put(FIRSTNAME, sharedPreferences.getString(FIRSTNAME,null));
        user.put(SURNAME, sharedPreferences.getString(SURNAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL,null));

        return user;


    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);

    }
}
