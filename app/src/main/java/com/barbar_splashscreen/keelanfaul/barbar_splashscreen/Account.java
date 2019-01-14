package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Account {

    private String userName;
    private String password;
    private String barberCode;

    public Account(String userName, String password, String barberCode) {
        this.userName = userName;
        this.password = password;
        this.barberCode = barberCode;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;

        return Objects.equals(getUserName(), account.getUserName()) &&
                Objects.equals(getPassword(), account.getPassword()) &&
                Objects.equals(getBarberCode(), account.getBarberCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword(), getBarberCode());
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBarberCode() {
        return barberCode;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", barberCode='" + barberCode + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonAccount = new JSONObject();
        try {
            jsonAccount.put("user_name", this.userName);
            jsonAccount.put("password", this.password);
            jsonAccount.put("barber_code", this.barberCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonAccount;
    }
}
