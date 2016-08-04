package com.james.im.transport.server.impl.netty;

import com.james.im.transport.server.Server;

/**
 * 实现netty 服务通信
 * 
 * @author james
 * 
 */
public class NettyBaseTranSportServer extends Server {

	private static final String TAG = NettyBaseTranSportServer.class
			.getSimpleName();

	private static volatile NettyBaseTranSportServer INSTANCE;

	private NettyBaseTranSportServer() {

	}

	public static synchronized NettyBaseTranSportServer newInstance() {

		if (INSTANCE == null) {
			INSTANCE = new NettyBaseTranSportServer();
		}
		return INSTANCE;
	}

	@Override
	public boolean checkServer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bindServer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listenServer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyServer() {
		// TODO Auto-generated method stub

	}

}
