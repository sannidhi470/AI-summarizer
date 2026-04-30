package com.example.AI_summarizer.Model;


public class Request {
    private String text;
    public Request(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String input) {
        this.text = input;
    }
}
