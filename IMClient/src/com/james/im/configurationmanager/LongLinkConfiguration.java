package com.james.im.configurationmanager;

/**
 * 长连接初始化配置 1: 地址配置
 * 
 * @author james
 * 
 */
public class LongLinkConfiguration {

	/**
	 * 地址
	 */
	public static String longlink_host = "appgwdev.w2here.com";

	/**
	 * 端口
	 */
	public static int longlink_port = 8001;

	
	/**
	 * 连接成功
	 */
	public static boolean CONNECTION_SUCCESS = true;
	
	/**
	 * 连接失败
	 */
	public static boolean CONNECTION_FAIL  = false;
	
	public LongLinkConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public static void setting(String host, int port) {

		longlink_host = host;
		longlink_port = port;

	}

}
