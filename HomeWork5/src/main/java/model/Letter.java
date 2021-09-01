package model;

import java.io.Serializable;

public class Letter implements Serializable {
    private String data;
    private String from;
    private String message;

    public Letter(String data, String user, String message) {
        this.data = data;
        this.from = user;
        this.message = message;
    }

    public Letter() {
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
