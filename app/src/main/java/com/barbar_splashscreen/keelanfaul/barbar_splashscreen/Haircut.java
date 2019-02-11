package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

public class Haircut {
    private int haircut_id;
    private String haircutName;
    private String haircutDescription;
    private String haircutImageURL;

    public Haircut(int haircut_id, String haircutName, String haircutDescription, String haircutImageURL) {
        this.haircut_id = haircut_id;
        this.haircutName = haircutName;
        this.haircutDescription = haircutDescription;
        this.haircutImageURL = haircutImageURL;
    }

    public int getHaircut_id() {
        return haircut_id;
    }

    public void setHaircut_id(int haircut_id) {
        this.haircut_id = haircut_id;
    }

    public String getHaircutName() {
        return haircutName;
    }

    public void setHaircutName(String haircutName) {
        this.haircutName = haircutName;
    }

    public String getHaircutDescription() {
        return haircutDescription;
    }

    public void setHaircutDescription(String haircutDescription) {
        this.haircutDescription = haircutDescription;
    }

    public String getHaircutImageURL() {
        return haircutImageURL;
    }

    public void setHaircutImageURL(String haircutImageURL) {
        this.haircutImageURL = haircutImageURL;
    }
}
