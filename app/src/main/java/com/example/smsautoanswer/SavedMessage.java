package com.example.smsautoanswer;

import java.util.Date;

public class SavedMessage {
    private String phoneNum;
    private Date date;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneText() {
        return phoneText;
    }

    public void setPhoneText(String phoneText) {
        this.phoneText = phoneText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SavedMessage(String phoneNum, String phoneText, Date date){
        this.phoneNum = phoneNum;
        this.phoneText = phoneText;
        this.date = date;
    }
}
