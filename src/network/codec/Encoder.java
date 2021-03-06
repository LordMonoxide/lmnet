package network.codec;

import network.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.MessageBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class Encoder extends MessageToMessageEncoder<Packet> {
  protected void encode(ChannelHandlerContext ctx, Packet msg, MessageBuf<Object> out) throws Exception {
    ByteBuf data = msg.serialize();
    int length = data != null ? data.readableBytes() : 0;
    ByteBuf b = Unpooled.buffer(length + 1);
    b.writeByte(msg.getIndex());
    
    if(data != null) {
      b.writeBytes(data);
    }
    
    out.add(b);
  }
}