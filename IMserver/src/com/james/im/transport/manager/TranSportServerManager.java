package com.james.im.transport.manager;

import com.james.im.transport.connection.ConnectionPool;
import com.james.im.transport.server.AbstractTranSportServerFactory;
import com.james.im.transport.server.Server;
import com.james.im.transport.server.TranSportServerFactory;
import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.im.transport.server.impl.SSLTranSportServer;


/**
 * 基础协议服务管理
 * @author James
 *
 */
public class TranSportServerManager extends ServerManager {
	
	
	
	private Server server;
	
	private ConnectionPool pool ;
	
	private static final int BASE = 0x0001;
	
	private AbstractTranSportServerFactory serverFactory;
	
	public TranSportServerManager() {
		// TODO Auto-generated constructor stub
		serverFactory = new TranSportServerFactory();
		
	}
	
	/**
	 * TODO 2016-7-31 后面这里弄成动态代理
	 * 
	 * 
	 */
	@Override
	public final void sync(Class<?> clazz) {
		// TODO Auto-generated method stub
		
		server = serverFactory.createServer(clazz);
		
	}
	
	

	
	@Override
	public final void server() {
		// TODO Auto-generated method stub
		pool = ConnectionPool.newInstance();
		server.setPool(pool);
		server.setServerInfo(getServerInfo());
		if(server.checkServer()){
			server.bindServer();
			server.listenServer();
		}
	}


	
	


	
	
}
