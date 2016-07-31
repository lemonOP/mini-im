package com.james.im.transport.connection.channel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.beust.jcommander.internal.Nullable;
import com.james.im.config.ChannelConfig;
import com.james.im.execption.IMConnectionException;
import com.james.minilog.MiniLog;

/**
 * 
 * base channel Stream
 * 
 * @author James
 * 
 */
public abstract class Channel {

	
	private static final String TAG = Channel.class.getSimpleName();
	
	private volatile Socket socket;

	/**
	 * 读
	 */
	private volatile InputStream inputStream;
	/**
	 * 写
	 */
	private volatile OutputStream outputStream;

	public Channel() {

	}

	public Channel(Socket socket, InputStream inputStream,
			OutputStream outputStream) {
		this.socket = socket;
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		
		this.socket = socket;
		
	}

	/**
	 * 
	 * @param socket
	 */
	public void createChannel(Socket socket) {

		try {
			setInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			setOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 创建链接tempId
	 */
	public String createConnTempId(Socket socket) {
		return socket != null ? Integer.toHexString(hashCode()) + "" : null;
		
	}
	
	/**
	 * 设置socket 参数
	 * 
	 * @param socket
	 */
	public void settingChannel(Socket socket){
		try {
			
			socket.setKeepAlive(ChannelConfig.KEEPALIVE);
			socket.setSoTimeout(ChannelConfig.TIMEOUT);//TODO 先定义到2分钟 ，后面需要根据客户端的网络环境来进行链接超时时间
			socket.setReceiveBufferSize(ChannelConfig.RECEIVEBUFFERSIZE);
			socket.setSendBufferSize(ChannelConfig.SENDBUFFERSIZE);
			socket.setTcpNoDelay(ChannelConfig.TCPNODELY);//Nagle算法关闭 false 开启
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			MiniLog.e(TAG, e);
			new IMConnectionException(e);
		}
		
	}
	
	public void shutdown(){
		if(inputStream != null){
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(outputStream != null){
			try {
				outputStream.close();
				outputStream = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(socket != null){
			if(!socket.isClosed()){
				try {
					socket.close();
					socket = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
