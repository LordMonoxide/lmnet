package network.packet;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class Packets {
  private static ArrayList<Class<? extends Packet>> _packet = new ArrayList<Class<? extends Packet>>();
  
  public static void add(Class<? extends Packet> packet) {
    _packet.add(packet);
  }
  
  public static Packet create(int index, ByteBuf data) throws IndexOutOfBoundsException, Packet.NotEnoughDataException {
    Class<? extends Packet> packet = _packet.get(index);
    
    try {
      Packet p = packet.newInstance();
      p.deserialize(data);
      return p;
    } catch(InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    
    return null;
  }
}