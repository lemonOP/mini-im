package com.james.im.packet.listener;

import com.james.im.packet.Packet;

/**
 * 消息监听处理回调
 * @author james
 *
 */
public interface IMessagePacketListener {

	/**
	 * 处理包信息
	 * @param packet
	 */
	 void handlerPacket(Packet packet);
	
	/**
	 * 执行客户端ACK
	 * @param figureId
	 * @param messageId
	 */
	 void execClientAck(String figureId,String messageId);
	
	/**
	 * 处理心跳定时器
	 * 
	 */
	void handlerHeartBeatTimer();
	
}
