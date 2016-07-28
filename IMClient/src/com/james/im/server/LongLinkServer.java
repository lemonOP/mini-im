package com.james.im.server;

import com.james.im.configurationmanager.LongLinkConfiguration;
import com.james.im.info.LongLinkInfo;
import com.james.im.log.Log;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 长链接服务 此服务面向于主工程 
 * 				1: 连接 
 * 					1.1:连接地址设置 
 * 					1.2:连接成功与否
 * @author james
 * 
 */
public class LongLinkServer {

	
	private static final String TAG = LongLinkServer.class.getSimpleName();
	/**
	 * 长链接服务 地址
	 */
	public String longlink_host;

	/**
	 * 长链接服务 端口
	 */
	public int longlink_port;
	
	/**
	 * 长连接所需要的信息
	 */
	public LongLinkInfo longLinkInfo;
	

	public ConnectionManager connectionManager;
	
	private PacketServer packetServer;
	
	/**
	 * 设置 消息回调监听
	 */
	public IPacketHandlerReceiver packetHandlerReceiver;
	
	public LongLinkServer() {
		// TODO Auto-generated constructor stub
		
	}

	public LongLinkServer(Builder builder) {
		this.longlink_host = builder.longlink_host;
		this.longlink_port = builder.longlink_port;
		this.packetHandlerReceiver = builder.packetHandlerReceiver;
		packetServer = new PacketServer(this);
	}

	/**
	 * 设置地址
	 */
	public void settingAddress() {
		if (this.longlink_host == null || this.longlink_port <= 0)
			throw new NullPointerException(
					"host and port not null ,please setting port or host");
		LongLinkConfiguration.setting(this.longlink_host, this.longlink_port);
	}

	
	/**
	 * 设置长连接信息
	 * @param userId 用户ID 
	 * @param deviceId 设备ID
	 * @param sessionId 会话ID
	 */
	public void settingLongLinkInfo(String userId,String deviceId,String sessionId){
		
		longLinkInfo = new LongLinkInfo
				.Builder()
				.userId(userId)
				.deviceId(deviceId)
				.sessionId(sessionId)
		.build();
		
	}
	
	
	
	
	/**
	 * 启动链接
	 */
	public void startLink() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (LongLinkConfiguration.longlink_host == null|| LongLinkConfiguration.longlink_port <= 0)
					throw new NullPointerException("host and port not null ,please settingAddress");
				connectionManager = new ConnectionManager();
				
				if(longLinkInfo != null){
					if(longLinkInfo.checkLongLink()){
						connectionManager.settingLongLinkInfo(longLinkInfo);
						connectionManager.setLongLinkServer(LongLinkServer.this);
						connectionManager.connect();
					}else{
						Log.d(TAG, "longlinkInfo checklongLinkinfo false");
					}
					
				}else{
					Log.d(TAG, "connection fail longlinkInfo is null please settingLongLinkInfo");
				}
			}
		}).start();
		
		
	}
	
	/**
	 * 重连 
	 * 这个主要用于网络环境切换
	 * 以供主工程调用
	 */
	public void resetStartLongLink(){
		
		if (LongLinkConfiguration.longlink_host == null|| LongLinkConfiguration.longlink_port <= 0)
			throw new NullPointerException("host and port not null ,please settingAddress");
		if(this.connectionManager== null){

			startLink();// 启动连接
			
		}else{
			//网络连接 重置连接数量
			this.connectionManager.getResetConnectionManger().resetConnectionCount();
			this.connectionManager.resetConnect();//重置连接
			
		}
		
		
	}
	
	
	
	public PacketServer getPacketServer() {
		return packetServer;
	}


	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}
	
	



	public static class Builder {
		/**
		 * 长链接服务 地址
		 */
		private String longlink_host;

		/**
		 * 长链接服务 端口
		 */
		private int longlink_port;

		/**
		 * 设置 消息回调监听
		 */
		private IPacketHandlerReceiver packetHandlerReceiver;
		
		
		public Builder packetHandlerReceiver(IPacketHandlerReceiver packetHandlerReceiver){
			this.packetHandlerReceiver = packetHandlerReceiver;
			return this;
		}
		
		public Builder host(String longlink_host) {
			this.longlink_host = longlink_host;
			return this;
		}

		public Builder port(int longlink_port) {
			this.longlink_port = longlink_port;

			return this;
		}

		public LongLinkServer build() {

			return new LongLinkServer(this);
		}
	}

}
