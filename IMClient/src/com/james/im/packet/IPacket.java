package com.james.im.packet;

import com.james.im.transport.connection.listener.ILongLinkConnectionListener;

/**
 * 包处理 接口
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
	 * @param longLinkConnectionListener
	 */
	void handlerPacketException(String info, Throwable t, ILongLinkConnectionListener longLinkConnectionListener);
	
	/**
	 * 关闭shutdown
	 */
	void shutdown();
	
	
}
