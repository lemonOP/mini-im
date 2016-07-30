package com.james.im.packet;

import com.james.im.transport.connection.channel.Channel;

/**
 * 包写处理
 * @author james
 *
 */
public class PacketWrite implements IPacketHandler {

	private Channel channel;
	
	
	
	
	public PacketWrite(Channel channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
	}

	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void handlerPacketException(String info, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}
	
	
}
