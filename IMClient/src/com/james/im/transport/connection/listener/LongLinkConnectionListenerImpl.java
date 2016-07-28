package com.james.im.transport.connection.listener;

import com.james.im.log.Log;
import com.james.im.transport.connection.manager.ConnectionManager;
import com.james.im.transport.task.ConnectInitReqTask;

/**
 * 长连接链接监听实现
 * 
 * @author james
 * 
 */
public class LongLinkConnectionListenerImpl implements ILongLinkConnectionListener {

	
	private static final String TAG =  LongLinkConnectionListenerImpl.class.getSimpleName();
	
	private ConnectionManager connectionManager;
	
	public LongLinkConnectionListenerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public LongLinkConnectionListenerImpl(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}

	@Override
	public void connectionSuccess() {
		// TODO Auto-generated method stub
		Log.d(TAG, " connection success");
		synchronized (this) {
			
			this.connectionManager.getResetConnectionManger().isResetConnect = true;
			
		}
		// 注册所有消息回调
		this.connectionManager.getPacketListenerManager().initListener();
		// 启动消息初始化
		new ConnectInitReqTask(this.connectionManager).sendPacket();
		
		
	}

	@Override
	public void connectionFail() {
		// TODO Auto-generated method stub
		Log.d(TAG, " connection fail");
		synchronized (this) {
			
			this.connectionManager.setConnectionState(false);
			
		}
		Log.d(TAG, " connection reset ");
		// 启动重连
		this.connectionManager.getResetConnectionManger().startResetConnection();
	}

}
