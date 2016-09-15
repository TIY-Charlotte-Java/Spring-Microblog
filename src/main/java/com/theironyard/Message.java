package com.theironyard;

public class Message {

    private Integer id;
    String text;

    Message(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
