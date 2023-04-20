package com.uco.stloan.utils;

public interface MessageSender<T> {
    void execute(T message, String idMessage);
}
