package com.james.im.transport.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;

import com.james.im.configurationmanager.ConnectionConfiguration;
import com.james.im.configurationmanager.LongLinkConfiguration;
import com.james.im.exception.LongLinkException;
import com.james.im.packet.PacketReader;
import com.james.im.packet.PacketWrite;
import com.james.im.transport.connection.listener.ILongLinkConnectionListener;

/**
 * 
 * SSL 加密 长连接
 * 
 * @author James
 * 
 */
public class LongLinkSSLConnection extends Connection {

	public LongLinkSSLConnection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean connect(
			ILongLinkConnectionListener longLinkConnectionListener) {
		// TODO Auto-generated method stub

		try {
			SSLContext context = SSLContext.getInstance("SSL");
			SocketFactory socketFactory = context.getSocketFactory();
			mSocket = socketFactory.createSocket(
					LongLinkConfiguration.longlink_host,
					LongLinkConfiguration.longlink_port);

			mSocket.setTcpNoDelay(ConnectionConfiguration.connectTcpNoDelay);
			mSocket.setSoTimeout(ConnectionConfiguration.connectTimeOut);

			if (mSocket != null) {
				init(mSocket);
				return LongLinkConfiguration.CONNECTION_SUCCESS;
			} else {
				longLinkConnectionListener.connectionFail();
			}

		} catch (NoSuchAlgorithmException e) {
			new LongLinkException("NoSuchAlgorithmException ", e,this.longLinkConnectionListener);
		}catch (UnknownHostException e) {
			new LongLinkException("UnknownHostException ", e,this.longLinkConnectionListener);
		}catch(ConnectException  e){
			new LongLinkException("ConnectException  ", e,this.longLinkConnectionListener);
		}catch(SocketTimeoutException e){
			new LongLinkException("SocketTimeoutException  ", e,this.longLinkConnectionListener);
		}catch (IOException e) {
			new LongLinkException("IOException  ", e,this.longLinkConnectionListener);
		}catch(Exception e){
			new LongLinkException("Exception  ", e,this.longLinkConnectionListener);
		}
		
		return LongLinkConfiguration.CONNECTION_FAIL;
	}
	
	
	@Override
	public void init(Socket socket) {
		// TODO Auto-generated method stub
		
		packetReader = new PacketReader(socket,getLongLinkConnectionListener());
		packetWrite = new PacketWrite(socket,getLongLinkConnectionListener());
		
	}

	@Override
	public void shutdownNow() {
		// TODO Auto-generated method stub

		if (packetReader != null)
			packetReader.shutdown();
		if (packetWrite != null)
			packetWrite.shutdown();
		if (mSocket != null) {
			try {
				mSocket.close();
				mSocket = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
