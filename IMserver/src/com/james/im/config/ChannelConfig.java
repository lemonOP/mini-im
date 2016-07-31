package com.james.im.config;

/**
 * 管道配置信息
 * @author james
 *
 */
public class ChannelConfig {

	private static final String TAG = ChannelConfig.class.getSimpleName();
	
	/**
	 * 
	 */
	public static final boolean KEEPALIVE = true;
	
	/**
	 * 
	 */
	public static final int TIMEOUT = 1000*60*2;
	
	/**
	 * 
	 */
	public static final int RECEIVEBUFFERSIZE = 1024*32;
	
	/**
	 * 
	 */
	public static final int SENDBUFFERSIZE = 1024*32;
	
	/**
	 * 
	 */
	public static final boolean TCPNODELY = true;
	
	public ChannelConfig(){
		
	}
	
	
}
