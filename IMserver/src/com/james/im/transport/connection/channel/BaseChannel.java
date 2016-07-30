package com.james.im.transport.connection.channel;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基本管道
 * 
 * @author James
 * 
 */
public class BaseChannel extends Channel {

	private static final String TAG  = BaseChannel.class.getSimpleName();
	
	public BaseChannel() {

	}
	
	public BaseChannel(Socket socket, InputStream inputStream,OutputStream outputStream) {
		super(socket, inputStream, outputStream);
	}
	
	
	

}
