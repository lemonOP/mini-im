package com.james.im.connection.channel;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * base channel Stream
 * 
 * @author James
 * 
 */
public abstract class Channel {

	/**
	 * 读流
	 */
	private InputStream inputStream;
	/**
	 * 写流
	 */
	private OutputStream outputStream;

	public Channel(InputStream inputStream, OutputStream outputStream) {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

}
