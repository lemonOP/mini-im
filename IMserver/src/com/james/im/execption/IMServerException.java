package com.james.im.execption;


/**
 * 服务异常
 * @author james
 *
 */
public class IMServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4111529580597276337L;

	public IMServerException() {
		// TODO Auto-generated constructor stub
	}

	public IMServerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IMServerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IMServerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IMServerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
