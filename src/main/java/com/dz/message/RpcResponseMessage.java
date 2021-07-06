package com.dz.message;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class RpcResponseMessage extends Message{

    private Object returnValue;

    private Exception exceptionValue;


    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
