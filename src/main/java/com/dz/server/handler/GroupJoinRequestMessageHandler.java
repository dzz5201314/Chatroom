package com.dz.server.handler;

import com.dz.message.GroupJoinRequestMessage;
import com.dz.message.GroupJoinResponseMessage;
import com.dz.server.session.GroupSession;
import com.dz.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Set<String> members = groupSession.getMembers(msg.getGroupName());
        boolean joinFlag= false;
        //群聊存在且用户未及加入，才能加入
        if(!members.contains(msg.getUsername()) && groupSession.isCreated(msg.getGroupName())){
            joinFlag = true;
        }

        if(joinFlag){
            //加入群聊
            groupSession.joinMember(msg.getGroupName(),msg.getUsername());
            ctx.writeAndFlush(new GroupJoinResponseMessage(true,"加入"+msg.getGroupName()+"成功"));
        }else{
            ctx.writeAndFlush(new GroupJoinResponseMessage(false,"加入失败，群聊未存在或您已加入该群聊"));
        }

    }
}
