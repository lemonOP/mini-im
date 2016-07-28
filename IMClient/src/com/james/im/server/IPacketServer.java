package com.james.im.server;

import com.james.im.packet.Packet;

/**
 * 包服务接口
 * @author james
 *
 */
public interface IPacketServer {

	
	/**
	 * 
	 * @param packet
	 */
	 void pushPacket(Packet packet);
	
	/**
	 * 
	 * @param packet
	 */
	 void pullPacket(Packet packet);
	
	
	
}
