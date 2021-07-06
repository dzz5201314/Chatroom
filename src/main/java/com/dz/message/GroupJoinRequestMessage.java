package com.dz.message;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class GroupJoinRequestMessage extends Message {
    private String groupName;

    private String username;

    public GroupJoinRequestMessage(String username, String groupName) {
        this.groupName = groupName;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int getMessageType() {
        return GroupJoinRequestMessage;
    }
}
