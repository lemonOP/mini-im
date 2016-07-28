package com.james.im.configurationmanager;

/**
 * 心跳配置
 * 
 * @author James
 * 
 */
public class HeartBeatTaskConfiguration {

	/**
	 * 心跳超时时间
	 * 
	 */
	private  int mHeartBeatTimeout = 1000*60*3 ; 

	/**
	 * 心跳间隔时间
	 */
	private  int mKeepAliveConfiguredInterval = 1000*60*2; 

	public HeartBeatTaskConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public int getmHeartBeatTimeout() {
		return mHeartBeatTimeout;
	}

	public void setmHeartBeatTimeout(int mHeartBeatTimeout) {
		this.mHeartBeatTimeout = mHeartBeatTimeout;
	}

	public int getmKeepAliveConfiguredInterval() {
		return mKeepAliveConfiguredInterval;
	}

	public void setmKeepAliveConfiguredInterval(int mKeepAliveConfiguredInterval) {
		this.mKeepAliveConfiguredInterval = mKeepAliveConfiguredInterval;
	}

	
	
	
	
	
	
}
