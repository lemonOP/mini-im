package com.james.im.packet.listener;

import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 包监听
 * @author james
 *
 */
public abstract class PacketListener {

	
	/**
	 * 处理包监听
	 * @param packet
	 */
	public  abstract void processPacket(Packet packet,ConnectionManager connectionManager);

	
	public PacketListener() {
		// TODO Auto-generated constructor stub
	}

}
