package com.main.model.User;

import com.main.model.alert.Alert;
import com.main.model.topic.Topic;

import java.util.ArrayList;

public class User {
    private String name;
    //private ArrayList<Topic> subscribedTopics;
    private ArrayList<Alert> alertsInbox;

    public User(String name) {
        this.name = name;
        //this.subscribedTopics = new ArrayList<>();
        this.alertsInbox = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void receiveAlert(Alert alert) {
        alert.apendMessage(this.alertsInbox);
    }

    public ArrayList<Alert> getAllAlerts() {
        return new ArrayList<>(this.alertsInbox);
    }
}
