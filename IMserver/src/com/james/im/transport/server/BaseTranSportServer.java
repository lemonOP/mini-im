package com.james.im.transport.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

import com.james.im.execption.IMServerConnectionException;
import com.james.im.execption.IMServerException;
import com.james.im.info.ServerInfo;
import com.james.minilog.MiniLog;

/**
 * 基础协议服务
 * @author james
 *
 */
public class BaseTranSportServer implements IServer {

	
	
	private static volatile BaseTranSportServer INSTANCE;
	
	private ServerInfo serverInfo;
	
	private ServerSocket serverSocket;
	
	private static final String TAG = BaseTranSportServer.class.getSimpleName();
	
	private  BaseTranSportServer() {
		// TODO Auto-generated constructor stub
	}
	
	
	public synchronized static BaseTranSportServer newInstance(){
		
		if(INSTANCE == null){
			INSTANCE = new BaseTranSportServer();
		}
		return INSTANCE;
		
	}


	@Override
	public boolean checkServer() {
		// TODO Auto-generated method stub
		if(serverInfo != null){
			return serverInfo.port > 1024 && serverInfo.connNumber != 0;
		}
		return false;
	}


	@Override
	public void bindServer() {
		// TODO Auto-generated method stub
		try {
			serverSocket = ServerSocketFactory.getDefault().createServerSocket(serverInfo.port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MiniLog.e(TAG,e);
			try {
				throw new IMServerException("bindServer exception ",e);
			} catch (IMServerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


	@Override
	public void listenServer() {
		// TODO Auto-generated method stub
		Socket mSocket;
			do{
				try {
					mSocket = serverSocket.accept();
					init(mSocket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					MiniLog.e(TAG,e);
					try {
						throw new IMServerConnectionException("listenServer exception ",e);
					} catch (IMServerConnectionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}while(serverSocket != null);
	}


	@Override
	public void destroyServer() {
		// TODO Auto-generated method stub
		
	}
	
	private void init(Socket socket) {
		// TODO Auto-generated method stub
		
	}

	public ServerInfo getServerInfo() {
		return serverInfo;
	}


	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}





}
