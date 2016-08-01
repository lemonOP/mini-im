package com.james.im.packet;


/**
 * 包处理
 * @author james
 *
 */
public interface IPacket {
	
	/**
	 * 包处理
	 * 主要用于读取和写入
	 */
	void handlerPacket();
	
	
	/**
	 * 处理包异常
	 * @param info
	 * @param t
	 */
	void handlerPacketException(String info, Throwable t);
	
	/**
	 * 关闭shutdown
	 */
	void shutdown();
	
	
}
