package com.james.im.exception;

import com.james.im.log.Log;
import com.james.im.transport.connection.listener.ILongLinkConnectionListener;

/**
 * 
 * 长连接异常
 * 
 * 连接异常导致 连接重连
 * @author James
 * 
 */
public class LongLinkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5114043286629085733L;

	private static final String TAG = LongLinkException.class.getSimpleName();
	
	public LongLinkException() {
		// TODO Auto-generated constructor stub
	}

	public LongLinkException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		Log.e(TAG, message);
	}

	public LongLinkException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public LongLinkException(String message, Throwable cause) {
		super(message, cause);
		PrintErrorInfo.printError(cause);
		// TODO Auto-generated constructor stub
	}
	
	public LongLinkException(String message, Throwable cause,ILongLinkConnectionListener longLinkConnectionListener) {
		super(message, cause);
		Log.e(TAG, message,cause);
		PrintErrorInfo.printError(cause);
		// TODO Auto-generated constructor stub
		longLinkConnectionListener.connectionFail();
		
	}

	public LongLinkException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
