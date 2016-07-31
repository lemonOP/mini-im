package com.james.im.transport.server;

import com.james.im.info.ServerInfo;
import com.james.im.transport.connection.ConnectionPool;


/**
 * 
 * 服务
 * @author James
 *
 */
public abstract class  Server {

	
	protected ServerInfo serverInfo;
	

	protected ConnectionPool pool;
	
	/**
	 * 检查服务
	 */
	public abstract  boolean checkServer();
	
	
	/**
	 * 绑定服务
	 */
	public abstract void bindServer();
	
	/**
	 * 监听服务
	 */
	public abstract void listenServer();
	
	
	/**
	 * 销毁服务
	 */
	public abstract void destroyServer();
	


	public ServerInfo getServerInfo() {
		return serverInfo;
	}


	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}


	public ConnectionPool getPool() {
		return pool;
	}


	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}
	
}
