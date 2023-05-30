package com.learning.sendretrivedatausingvolley;

public class SampleData {
    int id;
    String title;
    String body;

    public SampleData(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
