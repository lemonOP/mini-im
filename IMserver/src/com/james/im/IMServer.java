package com.james.im;

import com.james.im.info.ServerInfo;
import com.james.im.server.MainServer;
import com.james.minilog.MiniLog;

/**
 * 即时通讯 服务
 * 
 * @author James
 * 
 */
public class IMServer {

	private static final String TAG = IMServer.class.getSimpleName();

	private MainServer mainServer;
	public IMServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 启动服务
	 * 
	 * @return
	 */
	public boolean startIMServer() {
		MiniLog.d(TAG, "start imserver");
		
		mainServer = MainServer.newInstance();
		ServerInfo serverInfo = new ServerInfo.Builder().port(9001)
				.connNumber(5).build();
		mainServer.settingServerInfo(serverInfo);
		mainServer.server();
		
		return false;

	}

	/**
	 * 停止服务
	 * 
	 * @return
	 */
	public boolean stopIMServer() {
		MiniLog.d(TAG, "stop imserver");
		return false;
	}

}
