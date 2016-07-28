package com.james.im.transport.manager;

import com.james.im.transport.server.BaseTranSportServer;


/**
 * 基础协议服务管理
 * @author James
 *
 */
public class BaseTranSportServerManager extends ServerManager {
	
	
	private BaseTranSportServer baseTranSportServer;
	
	
	
	public BaseTranSportServerManager() {
		// TODO Auto-generated constructor stub
		baseTranSportServer = BaseTranSportServer.newInstance();
	}

	@Override
	public void sync() {
		// TODO Auto-generated method stub
		baseTranSportServer.setServerInfo(getServerInfo());
		if(baseTranSportServer.checkServer()){
			baseTranSportServer.bindServer();
			baseTranSportServer.listenServer();
		}
	}



	
	
}
