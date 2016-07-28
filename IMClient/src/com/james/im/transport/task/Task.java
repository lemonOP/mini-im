package com.james.im.transport.task;

import com.james.im.packet.Packet;

/**
 * 
 * @author James
 *
 */
public abstract  class Task  {
	
	
	/**
	 * 处理包
	 */
	public abstract Packet handlerPacket();
	
	/**
	 * 发送包
	 */
	public abstract void sendPacket();

	public Task(){
		
	}
	
	

}
