package com.james.im.transport.server;

import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.im.transport.server.impl.SSLTranSportServer;


/**
 * server生成工厂
 * @author james
 *
 */
public class TranSportServerFactory extends AbstractTranSportServerFactory {

	@Override
	public Server createServer(Class<?> clazz){
		if(clazz.getName().equals(BaseTranSportServer.class.getName())){
			return baseServer();
		}else if(clazz.getName().equals(SSLTranSportServer.class.getName())){
			return sslServer();
		}
		return null;
	}
	
	@Override
	BaseTranSportServer baseServer() {
		// TODO Auto-generated method stub
		return BaseTranSportServer.newInstance();
	}

	@Override
	SSLTranSportServer sslServer() {
		// TODO Auto-generated method stub
		return SSLTranSportServer.newInstance();
	}

}
