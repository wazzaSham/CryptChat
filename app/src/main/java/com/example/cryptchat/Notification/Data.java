package com.example.cryptchat.Notification;

public class Data {
    private String user;
    private int icon;
    private String body;
    private String title;
    private String sentend;

    public Data(String user, int icon, String body, String title, String sentend) {
        this.user = user;
        this.icon = icon;
        this.body = body;
        this.title = title;
        this.sentend = sentend;
    }

    public Data(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSentend() {
        return sentend;
    }

    public void setSentend(String sentend) {
        this.sentend = sentend;
    }
}
