package com.james.im.transport.connection.manager;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.james.im.configurationmanager.ConnectionConfiguration;
import com.james.im.exception.LongLinkException;
import com.james.im.log.Log;
import com.james.im.util.ThreadPool;

/**
 * 重连 管理 先不加定时器
 * 
 * @author James
 * 
 */
public class ResetConnectionManger {

	private static final String TAG = ResetConnectionManger.class.getSimpleName();
	
	private ConnectionManager connectionManager;

	/**
	 * 重新连接计数
	 */
	private int resetConnectionCount;

	/**
	 * 是否重连
	 */
	public boolean isResetConnect;

	private FutureTask<Boolean> futureTask;
	
	private ExecutorService executorService;
	
	private ResetConnection resetConnection;
	
	public ResetConnectionManger(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
		this.connectionManager = connectionManager;
		executorService = ThreadPool.getSingleThreadPool();
		
	}
	
	

	/**
	 * 启动重连
	 */
	public void startResetConnection() {
		if(futureTask == null){
			resetConnection();
		}else{
			if(futureTask.isDone()){
				if(this.connectionManager != null){
					if(this.connectionManager.isConnectionState()){// 连接成功
						Log.d(TAG, "reset connection success");
						return;
					}else{
						Log.d(TAG, "reset connection fail ");
						resetConnection();
					}
				}
			}else{
				Log.d(TAG, "reset connection ing  ");
				try {
					futureTask.get(ConnectionConfiguration.TASK_EXEC_TIMEOUT, TimeUnit.MILLISECONDS);
					if(this.connectionManager.isConnectionState()){
						Log.d(TAG, "reset connection success");
						return;
					}
				} catch (InterruptedException | ExecutionException
						| TimeoutException e) {
					// TODO Auto-generated catch block
					 new LongLinkException("InterruptedException | ExecutionException | TimeoutException ", e);
					if(!futureTask.isCancelled())
						 futureTask.cancel(true);
				}
				if(!futureTask.isCancelled())
				 futureTask.cancel(true);
				 resetConnection();
			}
			
		}
		
	}	
	
	/**
	 * 重连
	 */
	public void resetConnection(){
		if(resetConnection != null)resetConnection = null;
		if(futureTask != null) futureTask = null;
		resetConnection = new ResetConnection();
		futureTask = new FutureTask<>(resetConnection);
		executorService.submit(futureTask);
	}

	/**
	 * 停止重连
	 */
	public void stopResetConnection() {
		if(futureTask != null) 
			if(!futureTask.isCancelled())
				futureTask.cancel(true);
				futureTask = null;
		
	}
	
	/**
	 * 重置连接数量 
	 */
	public void resetConnectionCount(){
		
		resetConnectionCount = 0;
		
	}

	public class ResetConnection implements Callable<Boolean> {

		public ResetConnection(){
			
		}
		@Override
		public Boolean call() throws Exception {
			// TODO Auto-generated method stub
			if (connectionManager != null) {
				if (resetConnectionCount < ConnectionConfiguration.RESET_CONNECT_MAX) {
					resetConnectionCount++;
					try {
						//Thread.sleep(ConnectionConfiguration.REST_CONNECT_INTERVAL);
						synchronized (this) {
							isResetConnect = false;
						}
						connectionManager.resetConnect();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						new LongLinkException("InterruptedException",e);
					}
				}
			}
			return connectionManager.isConnectionState();
		}
	}

}
