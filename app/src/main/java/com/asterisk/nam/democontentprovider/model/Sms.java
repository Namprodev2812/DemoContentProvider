package com.asterisk.nam.democontentprovider.model;

public class  Sms {

    private String mPhoneNumber;
    private String mTime;
    private String mBody;

    public Sms(String mPhoneNumber, String mTime, String mBody) {
        this.mPhoneNumber = mPhoneNumber;
        this.mTime = mTime;
        this.mBody = mBody;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmBody() {
        return mBody;
    }
}
