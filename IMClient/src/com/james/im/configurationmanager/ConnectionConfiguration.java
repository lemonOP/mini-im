package com.james.im.configurationmanager;

/**
 * 连接配置
 * 1: 心跳配置
 * 2: 心跳超时配置
 * 3: 连接重连时间间隔配置
 * 4: 连接重连最大次数配置
 * 5: 连接超时的时间
 * 
 * @author james
 *
 */
public class ConnectionConfiguration {

	
	/**
	 * 连接超时时间
	 */
	public static int connectTimeOut = 1000 * 60 * 3;
	
	
	public static boolean connectTcpNoDelay = true;
	
	/**
	 *  重连最大次数
	 */
	public static final int RESET_CONNECT_MAX = 30;
	
	
	/**
	 * 重连间隔时间
	 */
	public static final int REST_CONNECT_INTERVAL = 1000*30;
	
	
	/**
	 * 任务执行超时时间
	 */
	public static final int TASK_EXEC_TIMEOUT = 1000*30;
	
	
	public ConnectionConfiguration() {
		// TODO Auto-generated constructor stub
	}

	
	
}
