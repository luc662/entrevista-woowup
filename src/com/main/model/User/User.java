package com.main.model.User;

import com.main.model.topic.Topic;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Topic> subscribedTopics;

    public User(String name) {
        this.name = name;
        this.subscribedTopics = new ArrayList<Topic>();
    }

    public String getName() {
        return name;
    }
}
