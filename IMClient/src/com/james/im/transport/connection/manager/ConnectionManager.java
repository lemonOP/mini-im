package com.james.im.transport.connection.manager;

import com.james.im.info.LongLinkInfo;
import com.james.im.packet.PacketReader;
import com.james.im.packet.PacketWrite;
import com.james.im.packet.manager.PacketListenerManager;
import com.james.im.server.LongLinkServer;
import com.james.im.transport.connection.Connection;
import com.james.im.transport.connection.LongLinkConnection;
import com.james.im.transport.connection.LongLinkConnectionFactory;
import com.james.im.transport.connection.listener.LongLinkConnectionListenerImpl;
import com.james.im.transport.task.manager.HeartBeatTaskManager;

/**
 * 连接管理 0:长连接链接 
 * 		  1:重置之前连接 
 *        2:关闭链接
 * engine class
 * @author James
 * 
 */
public class ConnectionManager {

	/**
	 * 长连接
	 */
	private static Connection connection;

	/**
	 * 连接回调
	 */
	private LongLinkConnectionListenerImpl longLinkConnectionListenerImpl;

	/**
	 * 包读
	 */
	private PacketReader packetReader;
	/**
	 * 包写
	 */
	private PacketWrite packetWrite;

	/**
	 * 长连接信息
	 */
	private LongLinkInfo longLinkInfo;

	/**
	 * 包监听管理
	 */
	private  PacketListenerManager packetListenerManager;

	/**
	 * 重连管理
	 */
	private ResetConnectionManger resetConnectionManger;
	
	
	/**
	 * 心跳管理
	 */
	private HeartBeatTaskManager heartBeatTaskManager;
	
	/**
	 * 连接状态
	 */
	private  volatile boolean isConnectionState;

	/**
	 * 是否注册
	 */
	private  volatile boolean isConnectionRegister;
	
	/**
	 * 面向主项目服务
	 * 需消息调度
	 */
	private LongLinkServer longLinkServer;

	
	public ConnectionManager() {
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * 链接管理初始化
	 */
	public void init() {
		
		longLinkConnectionListenerImpl = new LongLinkConnectionListenerImpl(this);
		packetListenerManager = new PacketListenerManager();
		heartBeatTaskManager = HeartBeatTaskManager.newInstance();
		heartBeatTaskManager.setConnectionManager(this);
		resetConnectionManger =  new ResetConnectionManger(this);
		
	}
	
	
	public void initGetConnection(){
		
		connection = LongLinkConnectionFactory.newInstance().newConnection(longLinkInfo);
		
	}

	/**
	 * 设置长连接信息
	 * 
	 * @param longLinkInfo
	 */
	public void settingLongLinkInfo(LongLinkInfo longLinkInfo) {

		this.longLinkInfo = longLinkInfo;
		
	}

	/**
	 * 连接
	 */
	public void connect() {
			
		initGetConnection();
		
		if(isConnectionState){
			shutdown();
		}
		isConnectionState = connection.connect(longLinkConnectionListenerImpl);
		if (isConnectionState) {
			packetReader = connection.packetReader;
			packetWrite = connection.packetWrite;
			packetReader.setConnectionManager(this);
			packetWrite.setConnectionManager(this);
			longLinkConnectionListenerImpl.connectionSuccess();
		} else {
			//longLinkConnectionListenerImpl.connectionFail(); testing
		}
	}
	
	/**
	 * 重新连接
	 */
	public void resetConnect(){
		if(!isConnectionState){
			shutdown();
			connect();
		}
	}
	
	
	/**
	 * 停止连接
	 */
	public void shutdown(){
		
		if(connection != null){
			connection.shutdownNow();
			connection = null;
		}
		
		this.isConnectionState = false;
		this.isConnectionRegister = false;
		this.packetReader = null;
		this.packetWrite = null;
		if(this.heartBeatTaskManager != null)
			heartBeatTaskManager.stopTimer();
		
	}

	public ResetConnectionManger getResetConnectionManger() {
		return resetConnectionManger;
	}

	public void setResetConnectionManger(ResetConnectionManger resetConnectionManger) {
		this.resetConnectionManger = resetConnectionManger;
	}

	public PacketListenerManager getPacketListenerManager() {
		return packetListenerManager;
	}

	public void setPacketListenerManager(PacketListenerManager packetListenerManager) {
		this.packetListenerManager = packetListenerManager;
	}

	public HeartBeatTaskManager getHeartBeatTaskManager() {
		return heartBeatTaskManager;
	}

	public void setHeartBeatTaskManager(HeartBeatTaskManager heartBeatTaskManager) {
		this.heartBeatTaskManager = heartBeatTaskManager;
	}

	public boolean isConnectionState() {
		return isConnectionState;
	}

	public void setConnectionState(boolean isConnectionState) {
		this.isConnectionState = isConnectionState;
	}

	public boolean isConnectionRegister() {
		return isConnectionRegister;
	}

	public void setConnectionRegister(boolean isConnectionRegister) {
		this.isConnectionRegister = isConnectionRegister;
	}

	public LongLinkInfo getLongLinkInfo() {
		return longLinkInfo;
	}

	public PacketWrite getPacketWrite() {
		return packetWrite;
	}

	public LongLinkServer getLongLinkServer() {
		return longLinkServer;
	}

	public void setLongLinkServer(LongLinkServer longLinkServer) {
		this.longLinkServer = longLinkServer;
	}

	
	
	

}
