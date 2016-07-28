package com.james.im;

import com.james.tm.taskmanage.MtmManager;

/**
 * 全局 context 需在主服务做初始化 存储各种全局内容
 * 
 * @author James
 * 
 */
public class IMContext {

	private static volatile IMContext INSTANCE;
	
	
	private IMContext() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized IMContext newInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IMContext();
		}
		return INSTANCE;
	}

	/**
	 * 此方法必须newInstance之后使用
	 * 
	 * @return
	 */
	public static IMContext context() {
		
		return INSTANCE;
	}

	
	
	

}
