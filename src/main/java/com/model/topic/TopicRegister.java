package com.model.topic;

import com.exceptions.TopicDoesNotExistException;
import com.model.User.User;
import com.model.alert.Alert;

import java.util.HashMap;

public class TopicRegister {
    private HashMap<String, Topic> topics;

    TopicRegister() {
        topics = new HashMap<>();
    }

    public void createTopic(String topicName) {
        if (topics.containsKey(topicName)) {
            throw new TopicDoesNotExistException();
        }
        this.topics.put(topicName, new Topic());
    }

    public void registerUserToTopic(String topicName, User user) {
        if (!topics.containsKey(topicName)) {
            throw new TopicDoesNotExistException();
        }
        this.topics.get(topicName).registerUser(user);
    }

    public void sendAlertToTopic(String topicName, Alert alert) {
        if (!topics.containsKey(topicName)) {
            throw new TopicDoesNotExistException();
        }
        this.topics.get(topicName).receiveAlert(alert);
    }

    public void sendAlertToUserInTopic(String topicName, Alert alert, String userName) {
        if (!topics.containsKey(topicName)) {
            throw new TopicDoesNotExistException();
        }
        this.topics.get(topicName).receiveAlert(alert, userName);
    }
}
