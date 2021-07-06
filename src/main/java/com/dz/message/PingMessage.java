package com.dz.message;

public class PingMessage extends Message{
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
