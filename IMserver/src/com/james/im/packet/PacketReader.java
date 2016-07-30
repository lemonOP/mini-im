package com.james.im.packet;

import com.james.im.transport.connection.channel.Channel;

/**
 * 包读处理
 * @author James
 *
 */
public class PacketReader implements IPacketHandler {

	
	private volatile Channel channel;
	
	
	public PacketReader(Channel channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
		
	}
	

	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub
		do{
			
			
		}while(this.channel.getSocket() != null && this.channel.getInputStream() != null);
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
