package com.james.im.transport.server;

import java.net.Socket;

/**
 * 
 * 服务
 * @author James
 *
 */
public interface IServer {

	
	/**
	 * 检查服务
	 */
	boolean checkServer();
	
	
	/**
	 * 绑定服务
	 */
	void bindServer();
	
	/**
	 * 监听服务
	 */
	void listenServer();
	
	
	/**
	 * 销毁服务
	 */
	void destroyServer();
	
	
}
