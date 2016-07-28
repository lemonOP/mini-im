package com.james.im.transport.task.manager;

import java.util.Timer;
import java.util.TimerTask;

import com.james.im.configurationmanager.HeartBeatTaskConfiguration;
import com.james.im.log.Log;
import com.james.im.transport.connection.manager.ConnectionManager;
import com.james.im.transport.task.HeartBeatReqTask;

/**
 * 心跳管理
 * 
 * @author james
 *
 */
public class HeartBeatTaskManager {

	
	private static final String TAG = HeartBeatTaskManager.class.getSimpleName();
	
	private static HeartBeatTaskManager heartBeatTaskManager;
	
	private static Timer timer;
	
	
	private static TimerTask timerTask;
	
	/**
	 * 心跳发送计数
	 */
	private static int hearbeatCount;
	
	/**
	 * 心跳任务
	 */
	private static HeartBeatReqTask heartBeatReqTask;
	
	/**
	 * 连接管理
	 */
	private  ConnectionManager connectionManager;
	
	
	
	
	
	private HeartBeatTaskManager() {
		// TODO Auto-generated constructor stub
	}

	public static HeartBeatTaskManager newInstance(){
		
		synchronized (HeartBeatTaskManager.class) {
			if(heartBeatTaskManager == null){
				// 初始化管理器和任务
				heartBeatTaskManager = new HeartBeatTaskManager();
			}
			return heartBeatTaskManager;
		}
		
	}
	
	/**
	 * 启动定时器
	 */
	public void startTimer(){
		if(timer != null && timerTask != null){
			stopTimer();
		}
		Log.d(TAG, "start heart beat timer");
		if(this.connectionManager != null){
			heartBeatReqTask = new HeartBeatReqTask(this.connectionManager);
			timer = new Timer();
			HeartBeatTaskConfiguration heartBeatTaskConfiguration = new HeartBeatTaskConfiguration();
			timerTask = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					heartBeatReqTask.sendPacket();
					hearbeatCount++;
					Log.d(TAG, "heartbeat count = "+hearbeatCount);
				}
			};
			timer.schedule(
					timerTask,
					heartBeatTaskConfiguration.getmKeepAliveConfiguredInterval(), 
					heartBeatTaskConfiguration.getmKeepAliveConfiguredInterval());
		}
	}

	
	/**
	 * 停止定时器
	 */
	public void stopTimer(){
		
		if(timer != null){
			timer.cancel();
			timer = null;
		}
		
		if(timerTask != null){
			timerTask.cancel();
			timerTask = null;
		}
		
	}

	
	/**
	 * 重置心跳定时
	 */
	public void resetTimer(){
		startTimer();
	}
	
	
	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	
	
	
	
	
}
