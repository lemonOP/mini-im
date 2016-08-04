package com.james.im.transport.server;

import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.im.transport.server.impl.SSLTranSportServer;
import com.james.im.transport.server.impl.netty.NettyBaseTranSportServer;

public abstract class AbstractTranSportServerFactory {

	
	public abstract Server createServer(Class<?> clazz);
	abstract BaseTranSportServer baseServer();
	abstract SSLTranSportServer sslServer();
	abstract NettyBaseTranSportServer nettyServer();
	
}
