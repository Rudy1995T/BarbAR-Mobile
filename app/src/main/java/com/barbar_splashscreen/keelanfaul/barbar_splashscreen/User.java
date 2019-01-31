package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String firstName;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String avatar;

    public User() {}

    public User(String firstName, String surname, String username, String email, String password, String avatar) {
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public User(String firstName, String surname, String username, String email, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String jsonFormat() {
        JSONObject userJSON = new JSONObject();

        try {
            userJSON.put("firstname", firstName);
            userJSON.put("surname", surname);
            userJSON.put("username", username);
            userJSON.put("email", email);
            userJSON.put("password", password);
            userJSON.put("avatar", avatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userJSON.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
