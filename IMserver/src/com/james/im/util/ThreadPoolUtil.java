package com.james.im.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池 工具类
 * @author james
 *
 */
public class ThreadPoolUtil implements IUtil {

	private static volatile ExecutorService cacheService = null;
	private static volatile ExecutorService singleService =  null;
	
	
	@Override
	public void util(UtilCallBack utilCallBack) {
		// TODO Auto-generated method stub

	}
	
	public static ExecutorService getCacheThreadPool(){
		if(cacheService == null){
			synchronized (ThreadPoolUtil.class) {
				cacheService = Executors.newCachedThreadPool();
			}
		}
		
		return cacheService;
		
	}
	
	public static ExecutorService getSingleThreadPool(){
		if(singleService == null){
			synchronized (ThreadPoolUtil.class) {
				singleService = Executors.newSingleThreadExecutor();
			}
		}
		return singleService;
	}
	

}
