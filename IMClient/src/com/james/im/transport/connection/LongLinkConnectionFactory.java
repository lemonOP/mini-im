package com.james.im.transport.connection;

import com.james.im.info.LongLinkInfo;

/**
 * 
 * @author james
 *
 */
public class LongLinkConnectionFactory {

	private static volatile LongLinkConnectionFactory  longLinkConnectionFactory ;
	
	
	private LongLinkConnectionFactory() {
		// TODO Auto-generated constructor stub
	}

	
	public static synchronized LongLinkConnectionFactory newInstance(){
		if(longLinkConnectionFactory == null){
			longLinkConnectionFactory = new LongLinkConnectionFactory();
		}
		return longLinkConnectionFactory;
		
	}
	
	/**
	 * 获取连接通道
	 * @param longLinkInfo
	 * @return
	 */
	public Connection newConnection(LongLinkInfo longLinkInfo){
		
		if(longLinkInfo.isUsedSSL){
			
			return new LongLinkSSLConnection();
					
		}else{
			
			return new LongLinkConnection();
		}
		
	}
	
	
	
	
	
	
	
	
}
