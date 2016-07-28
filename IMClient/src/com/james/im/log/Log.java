package com.james.im.log;

/**
 * 日志对象
 * 
 * @author james
 * 
 */
public class Log {

	public Log() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param tag
	 * @param e
	 */
	public static  void e(String tag, Exception e) {
		System.err.println("TAG = " + tag + " info  = "
				+ e.getLocalizedMessage());
	}

	
	/**
	 * 
	 * @param tag
	 * @param info
	 * @param e
	 */
	public static  void e(String tag, String info, Exception e) {
		System.err.println("TAG = " + tag +"info = "+info+ " e  = "
				+ e.getLocalizedMessage());
	}
	
	/**
	 * 
	 * @param tag
	 * @param info
	 * @param e
	 */
	public static  void e(String tag, String info, Throwable e) {
		System.err.println("TAG = " + tag +"  "+info);
	}
	
	/**
	 * 
	 * @param tag
	 * @param info
	 * @param e
	 */
	public static  void e(String tag, String info) {
		System.err.println("TAG = " + tag +"info = "+info);
	}
	
	
	
	
	/**
	 * 
	 * @param tag
	 * @param infoMessage
	 */
	public static  void d(String tag, String infoMessage) {
		System.out.println("TAG = " + tag + " info  = " + infoMessage);
	}

	/**
	 * 
	 * @param tag
	 * @param infoMessage
	 */
	public static void i(String tag, String infoMessage) {
		System.out.println("TAG = " + tag + " info  = " + infoMessage);
	}

}
