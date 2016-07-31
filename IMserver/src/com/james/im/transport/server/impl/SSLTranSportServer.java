package com.james.im.transport.server.impl;

import com.james.im.transport.server.Server;

/**
 * SSL 加密链接
 * 
 * @author james
 *
 */
public class SSLTranSportServer extends Server {

	private static volatile SSLTranSportServer INSTANCE;
	
	
	private SSLTranSportServer(){
		
	}
	
	public static SSLTranSportServer newInstance(){
		
		if(INSTANCE == null){
			INSTANCE = new SSLTranSportServer();
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
