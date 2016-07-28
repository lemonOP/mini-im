package com.james.im.bootloader;

/**
 * server 启动需要检测的环境
 * 1:网络环境
 * 2:服务端口号
 * @author James
 *
 */
public interface INetworkBootLoader {

	/**
	 * 检测服务网络环境
	 * 1:检测网络是否可行
	 * 2:检测网络外网是否可行
	 * @return
	 */
	 void  checkServerNetworkEnvironment();
	 
	 /**
	  * 检测当前绑定监听PORT 是否可行
	  * 
	  * @return
	  */
	 boolean  checkServerPortBindOrListener();
	 
}
