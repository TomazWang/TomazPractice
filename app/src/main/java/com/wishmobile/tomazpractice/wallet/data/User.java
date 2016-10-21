package com.wishmobile.tomazpractice.wallet.data;

/**
 * Created by TomazWang on 2016/10/21.
 */

public class User {

    String name;
    String mail;
    String phone;
    int gender = -1;

    public User(String name, String mail, String phone, int gender) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
