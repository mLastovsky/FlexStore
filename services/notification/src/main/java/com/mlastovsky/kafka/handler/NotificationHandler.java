package com.mlastovsky.kafka.handler;

public interface NotificationHandler<T> {

    void handle(T notification);

}
