package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

public class History {
    private int session_id;
    private String haircut_name;
    private String haircut_image_URL;
    private String time_taken;


    public History(int session_id, String haircut_name, String haircut_image_URL, String time_taken) {
        this.session_id = session_id;
        this.haircut_name = haircut_name;
        this.haircut_image_URL = haircut_image_URL;
        this.time_taken = time_taken;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getHaircut_name() {
        return haircut_name;
    }

    public void setHaircut_name(String haircut_name) {
        this.haircut_name = haircut_name;
    }

    public String getHaircut_image_URL() {
        return haircut_image_URL;
    }

    public void setHaircut_image_URL(String haircut_image_URL) {
        this.haircut_image_URL = haircut_image_URL;
    }

    public String getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }
}
