package com.barbar_splashscreen.keelanfaul.barbar_splashscreen;

public class Students {
    private int student_id;
    private int barber_id;
    private String first_name;
    private String surname;
    private String profilePicImgURL;

    public Students(int student_id, int barber_id, String first_name, String surname, String profilePicImgURL) {
        this.student_id = student_id;
        this.barber_id = barber_id;
        this.first_name = first_name;
        this.surname = surname;
        this.profilePicImgURL = profilePicImgURL;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(int barber_id) {
        this.barber_id = barber_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfilePicImgURL() {
        return profilePicImgURL;
    }

    public void setProfilePicImgURL(String profilePicImgURL) {
        this.profilePicImgURL = profilePicImgURL;
    }
}
