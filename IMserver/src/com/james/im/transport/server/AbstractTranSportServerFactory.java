package com.james.im.transport.server;

import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.im.transport.server.impl.SSLTranSportServer;

public abstract class AbstractTranSportServerFactory {

	
	public abstract Server createServer(Class<?> clazz);
	abstract BaseTranSportServer baseServer();
	abstract SSLTranSportServer sslServer();
	
}
