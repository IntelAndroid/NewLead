package com.example.android.newlead.bean;

/**
 * Created by Android on 2016/6/1.
 */
public class listBean {
    private int icon;
    private int praise;
    private String title;
    private String number;

    public listBean(int icon, int praise, String title, String number) {
        this.icon = icon;
        this.praise = praise;
        this.title = title;
        this.number = number;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
