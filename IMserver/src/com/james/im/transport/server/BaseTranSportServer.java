package com.james.im.transport.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

import com.james.im.execption.IMServerConnectionException;
import com.james.im.execption.IMServerException;
import com.james.im.info.ServerInfo;
import com.james.im.transport.connection.Connection;
import com.james.im.transport.connection.ConnectionPool;
import com.james.im.transport.connection.channel.BaseChannel;
import com.james.im.transport.connection.channel.Channel;
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
	
	private ConnectionPool pool;
	
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
		MiniLog.d(TAG, "check server");
		if(serverInfo != null){
			return serverInfo.port > 1024 && serverInfo.connNumber != 0 ;
		}
		return false;
	}


	@Override
	public void bindServer() {
		// TODO Auto-generated method stub
		MiniLog.d(TAG, "bind server");
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
		MiniLog.d(TAG, "listen server");
			do{
				try {
					Socket mSocket = serverSocket.accept();
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


	private void init(Socket socket) {
		// TODO Auto-generated method stub
		MiniLog.d(TAG, "socket init");
		
		Channel channel=  new BaseChannel();
		channel.createChannel(socket);
		Connection conn = new Connection();
		conn.setChannel(channel);
		
		this.pool.add(channel.createConnTempId(conn.getChannel().getSocket()), conn);
		
	}


	@Override
	public void destroyServer() {
		// TODO Auto-generated method stub
		MiniLog.d(TAG, "destroy server");
		if(serverSocket != null){
			try{
				if(!serverSocket.isClosed())
					serverSocket.close();
					serverSocket = null;
			}catch(Exception e){
				try {
					throw new IMServerException(e);
				} catch (IMServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		if(this.pool != null)
			this.pool.shutdown();
		//TODO 这里只是清除temp connection  和当前服务还需要清除temp 转向 Official的所有链接
		
	}
	
	
	public ServerInfo getServerInfo() {
		return serverInfo;
	}


	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}


	public ConnectionPool getPool() {
		return pool;
	}


	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}


	



}
