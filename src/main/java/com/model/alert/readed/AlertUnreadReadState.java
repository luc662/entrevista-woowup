package com.model.alert.readed;

public class AlertUnreadReadState implements AlertReadState {

    @Override
    public AlertReadState markAsRead() {
        return new AlertAlreadyReadReadState();
    }

    @Override
    public boolean isRead() {
        return false;
    }
}
