package com.james.im.transport.connection.listener;

/**
 * 长链接链接监听
 * @author james
 *
 */
public interface ILongLinkConnectionListener {

	
	
	/**
	 * 链接成功
	 */
	 void connectionSuccess();
	
	/**
	 * 链接失败
	 */
	 void connectionFail();
	

}
