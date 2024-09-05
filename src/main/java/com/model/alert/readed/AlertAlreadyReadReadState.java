package com.model.alert.readed;

public class AlertAlreadyReadReadState implements AlertReadState {

    @Override
    public AlertReadState markAsRead() {
        return this;
    }

    @Override
    public boolean isRead() {
        return true;
    }
}
