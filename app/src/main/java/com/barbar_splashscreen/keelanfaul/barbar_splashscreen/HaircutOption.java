package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

public class HaircutOption {

    private String url;
    private String haircutDescription;

    public HaircutOption(String url, String haircutDescription) {
        this.url = url;
        this.haircutDescription = haircutDescription;
    }

    public String getUrl() {
        return url;
    }

    public String getHaircutDescription() {
        return haircutDescription;
    }

    @Override
    public String toString() {
        return "HaircutOption{" +
                "url='" + url + '\'' +
                ", haircutDescription='" + haircutDescription + '\'' +
                '}';
    }
}
