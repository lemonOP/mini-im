package com.james.im.execption;

/***
 * 
 * 端到端链接异常
 * @author James
 *
 */
public class IMServerConnectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8906811350333060986L;

	public IMServerConnectionException() {
		// TODO Auto-generated constructor stub
	}

	public IMServerConnectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IMServerConnectionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IMServerConnectionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IMServerConnectionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
