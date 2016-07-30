package com.james.im.transport.manager;

import com.james.im.transport.connection.ConnectionPool;
import com.james.im.transport.server.BaseTranSportServer;


/**
 * 基础协议服务管理
 * @author James
 *
 */
public class BaseTranSportServerManager extends ServerManager {
	
	
	private BaseTranSportServer baseTranSportServer;
	
	private ConnectionPool pool ;
	
	public BaseTranSportServerManager() {
		// TODO Auto-generated constructor stub
		baseTranSportServer = BaseTranSportServer.newInstance();
		pool = ConnectionPool.newInstance();
	}

	@Override
	public void sync() {
		// TODO Auto-generated method stub
		baseTranSportServer.setPool(pool);
		baseTranSportServer.setServerInfo(getServerInfo());
		if(baseTranSportServer.checkServer()){
			baseTranSportServer.bindServer();
			baseTranSportServer.listenServer();
		}
	}

	
	


	
	
}
