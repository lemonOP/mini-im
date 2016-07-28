package com.james.im.util;

import java.util.ArrayList;
import java.util.List;

import com.james.im.tasks.NetWorkConnectionStatesTask;
import com.james.minilog.MiniLog;
import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.task.Task;
import com.james.tm.taskmanage.TaskManager;

/**
 * 
 * 当前环境检测
 * 1:检测当前网络是否可用
 * @author James
 *
 */
public class ThisNetWorkEnvironmentUtil implements IUtil {

	
	private static final String TAG = ThisNetWorkEnvironmentUtil.class.getSimpleName();
	
	private List<Boolean> netWorkData = new ArrayList<>();
	
	public ThisNetWorkEnvironmentUtil() {
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public void util(UtilCallBack utilCallBack) {
		// TODO Auto-generated method stub
		netWorkConnectionStates(utilCallBack);
	}
	
	
	
	
	/**
	 * 网络链接状况
	 */
	public void netWorkConnectionStates(final UtilCallBack utilCallBack){
		
		NetWorkConnectionStatesTask newWorkConnectionStatesTask = new NetWorkConnectionStatesTask.Builder().handler(new IHandler() {

			@Override
			public <T> void back(T t) {
				// TODO Auto-generated method stub
				netWorkData.add((Boolean)t);
				if(netWorkData.size()==9){
					Boolean isConnection = netWorkData.get(8);
					if(isConnection){
						
						MiniLog.d(TAG, "网络通讯SUCCESS");
						utilCallBack.utilSuccess("true");
						
					}else{
						
						MiniLog.d(TAG, "网络通讯FAIL");
						//TODO	诊断网络
						utilCallBack.utilFail("false");
						
					}
				}
			}
		}).build();
		TaskManager.addTask(newWorkConnectionStatesTask);
		
	}


	
	
	
	

}
