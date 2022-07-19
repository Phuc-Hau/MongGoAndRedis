package com.smartpay.model;

public class User {

    private Object _id;
    private String username;
    private String password;
    private String fullname;

    public Object getId() {
        return _id;
    }

    public void setId(Object id) {
        this._id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
