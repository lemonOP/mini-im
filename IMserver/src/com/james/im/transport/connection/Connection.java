package com.james.im.transport.connection;

import com.james.im.packet.PacketReader;
import com.james.im.packet.PacketWrite;
import com.james.im.transport.connection.channel.Channel;


/**
 * 处理端到端网络点
 * @author James
 *
 */
public class Connection  {

	
	@SuppressWarnings("unused")
	private static final String TAG = Connection.class.getSimpleName();
	
	private PacketReader packetReader;
	private PacketWrite packetWrite;
	
	private Channel channel;
	
	public Connection() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
	public void init(){
		packetReader = new PacketReader(this.getChannel());
		packetWrite = new PacketWrite(this.getChannel());
	}
	
	
	public void shutdown(){
		if(packetReader != null) packetReader.shutdown();
		if(packetWrite != null) packetWrite.shutdown();
		if(channel != null) channel.shutdown();
	}
	
	
	
	
	
	
	
	

}
