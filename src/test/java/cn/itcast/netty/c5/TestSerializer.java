package cn.itcast.netty.c5;

import com.dz.config.Config;
import com.dz.message.LoginRequestMessage;
import com.dz.message.Message;
import com.dz.protocol.MessageCodecSharable;
import com.dz.protocol.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;

public class TestSerializer {
    public static void main(String[] args) {
        MessageCodecSharable CODEC = new MessageCodecSharable();
        LoggingHandler LOGGING = new LoggingHandler();
        EmbeddedChannel channel = new EmbeddedChannel(LOGGING,CODEC,LOGGING);

        LoginRequestMessage zhangsan = new LoginRequestMessage("zhangsan", "123");
        //channel.writeOutbound(zhangsan);
        ByteBuf byteBuf = messageToByteBuf(zhangsan);
        channel.writeInbound(byteBuf);
    }

    public static ByteBuf messageToByteBuf(Message msg){
        int algorithm = Config.getSerializerAlgorithm().ordinal();
        ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
        out.writeBytes(new byte[]{1, 2, 3, 4});
        out.writeByte(1);
        out.writeByte(algorithm);
        out.writeByte(msg.getMessageType());
        out.writeInt(msg.getSequenceId());
        out.writeByte(0xff);
        byte[] bytes = Serializer.Algorithm.values()[algorithm].serialize(msg);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
        return out;
    }
}

