package com.asterisk.nam.democontentprovider.model;

public class Contact {
    private String mName;
    private String mPhone;

    public Contact(String mName, String mPhone) {
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }
}
