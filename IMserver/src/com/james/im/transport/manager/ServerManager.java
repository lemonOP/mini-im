package com.james.im.transport.manager;

import com.james.im.IMContext;
import com.james.im.info.ServerInfo;

/**
 *  
 * @author James
 *
 */
public abstract class ServerManager {

	private IMContext mContext;
	
	private ServerInfo serverInfo;
	
	public abstract void sync();

	ServerManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	public IMContext getmContext() {
		return mContext;
	}

	public void setmContext(IMContext mContext) {
		this.mContext = mContext;
	}

	public ServerInfo getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}

	
	
}
