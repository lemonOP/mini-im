package com.james.im.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author james
 *
 */
public class ThreadPool {

	
	static ExecutorService singleService = null;
	private ThreadPool() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public static ExecutorService getSingleThreadPool() {
		if (singleService == null) {
			synchronized (ThreadPool.class) {
				if (singleService == null)
					singleService = Executors.newSingleThreadExecutor();
			}
		}
		return singleService;
	}
	
}
