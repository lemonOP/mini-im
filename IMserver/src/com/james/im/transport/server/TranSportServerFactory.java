package com.james.im.transport.server;

import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.im.transport.server.impl.SSLTranSportServer;
import com.james.im.transport.server.impl.netty.NettyBaseTranSportServer;

/**
 * server生成工厂
 * 
 * @author james
 * 
 */
public class TranSportServerFactory extends AbstractTranSportServerFactory {

	@Override
	public Server createServer(Class<?> clazz) {
		if (clazz.getName().equals(BaseTranSportServer.class.getName())) {
			return baseServer();
		} else if (clazz.getName().equals(SSLTranSportServer.class.getName())) {
			return sslServer();
		} else if (clazz.getName().equals(
				NettyBaseTranSportServer.class.getName())) {
			return nettyServer();
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

	@Override
	NettyBaseTranSportServer nettyServer() {
		// TODO Auto-generated method stub
		return NettyBaseTranSportServer.newInstance();
	}

}
