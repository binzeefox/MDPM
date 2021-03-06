package com.binzeefox.mdpm.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by tong.xiwen on 2017/6/13.
 */
public class User extends DataSupport implements Serializable{

    private int id;
    private String userName;
    private String md5Psd;
    private String email;
    private String phone;
    private String time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMd5Psd() {
        return md5Psd;
    }

    public void setMd5Psd(String md5Psd) {
        this.md5Psd = md5Psd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
