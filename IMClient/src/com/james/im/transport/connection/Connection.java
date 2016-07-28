package com.james.im.transport.connection;

import java.net.Socket;

import com.james.im.packet.PacketReader;
import com.james.im.packet.PacketWrite;
import com.james.im.transport.connection.listener.ILongLinkConnectionListener;

/**
 * 抽象连接
 * @author james
 *
 */
public abstract class Connection {

	
	/**
	 * 套接字
	 */
	public Socket mSocket;
	
	
	/**
	 * 包读
	 */
	public PacketReader packetReader;

	/**
	 * 包写
	 */
	public PacketWrite packetWrite;
	
	
	
	/**
	 * 
	 */
	protected ILongLinkConnectionListener longLinkConnectionListener;
	
	/**
	 *  连接
	 * @param longLinkConnectionListenerImpl
	 * @return
	 */
	public abstract boolean  connect(ILongLinkConnectionListener longLinkConnectionListener);
	
	
	
	/**
	 * 连接初始化
	 * @param socket
	 */
	public abstract void  init(Socket socket);
	
	
	
	/**
	 * 关闭连接
	 */
	public abstract void  shutdownNow();
	
	
	public Connection() {
		// TODO Auto-generated constructor stub
		
	}

	
	public ILongLinkConnectionListener getLongLinkConnectionListener() {
		return longLinkConnectionListener;
	}

	public void setLongLinkConnectionListener(
			ILongLinkConnectionListener longLinkConnectionListener) {
		this.longLinkConnectionListener = longLinkConnectionListener;
	}
	
}
