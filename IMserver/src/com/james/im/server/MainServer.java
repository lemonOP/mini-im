package com.james.im.server;

import com.james.im.IMContext;
import com.james.im.bootloader.BaseBootLoader;
import com.james.im.bootloader.IBootLoader;
import com.james.im.bootloader.MiniTaskBootLoader;
import com.james.im.info.ServerInfo;
import com.james.im.transport.manager.TranSportServerManager;
import com.james.im.transport.manager.ServerManager;
import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.minilog.MiniLog;

/**
 * 
 * 主服务
 * 
 * @author James
 * 
 */
public class MainServer implements IBootLoader {

	private static final String TAG = MainServer.class.getSimpleName();

	private static volatile MainServer INSTANCE;

	/**
	 * 基础Boot
	 */
	private BaseBootLoader baseBootLoader = null;

	/**
	 * 任务管理器Boot
	 */
	private MiniTaskBootLoader miniTaskBootLoader = null;

	/**
	 * 全局Context
	 */
	private IMContext mContext;
	
	/**
	 * 基础协议服务管理
	 */
	private ServerManager tranSportServerManager;
	
	private ServerInfo serverInfo;
	
	private MainServer() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized MainServer newInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MainServer();
		}
		return INSTANCE;
	}

	@Override
	public void boot() {
		// TODO Auto-generated method stub
		
		miniTaskBootLoader = new MiniTaskBootLoader();
		baseBootLoader = new BaseBootLoader();
		miniTaskBootLoader.boot();
		baseBootLoader.boot();
		bootProgress();

	}

	/**
	 * Boot 进度
	 * 这块代码 有点恶心 ，后面修改
	 * 
	 */
	public void bootProgress() {
		
		for (;;) {
			if (baseBootLoader.BASESTATE != null) {
				MiniLog.d(TAG, baseBootLoader.BASESTATE);
				if (baseBootLoader.BASESTATE.equals("true")) {
					MiniLog.d(TAG, "boot success");
					baseSync();
					break;

				} else if (baseBootLoader.BASESTATE.equals("false")) {
					MiniLog.d(TAG, "boot fail");
					break;
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	
	public final void settingServerInfo(ServerInfo serverInfo){
		this.serverInfo = serverInfo;
	}
	
	/**
	 * 主服务启动
	 * 
	 */
	public final void server() {
		mContext = IMContext.newInstance();
		boot();
	}
	
	
	public void baseSync(){
		tranSportServerManager = new TranSportServerManager();
		tranSportServerManager.setmContext(mContext);
		tranSportServerManager.setServerInfo(serverInfo);
		tranSportServerManager.sync(BaseTranSportServer.class);
	}
	
	

}
