package com.dz.server.handler;

import com.dz.message.GroupQuitRequestMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupQuitRequestMessage groupQuitRequestMessage) throws Exception {

    }
}
