package com.james.im.packet;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.james.im.exception.LongLinkException;
import com.james.im.log.Log;
import com.james.im.transport.connection.listener.ILongLinkConnectionListener;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 包读取
 * 
 * @author james
 * 
 */
public class PacketReader implements IPacket {

	private static final String TAG = PacketReader.class.getSimpleName();
	private Socket mSocket;

	private InputStream inputStream;

	private ConnectionManager connectionManager;
	
	private ILongLinkConnectionListener longLinkConnectionListener;
	
	private byte[] headBuffer = new byte[5];

	
	public PacketReader() {
		// TODO Auto-generated constructor stub
	}

	public PacketReader(Socket mSocket) {
		this.mSocket = mSocket;
		if(this.mSocket != null){
			handlerPacket();
		}
	}
	
	public PacketReader(Socket mSocket,ILongLinkConnectionListener longLinkConnectionListener){
		this.mSocket = mSocket;
		this.longLinkConnectionListener = longLinkConnectionListener;
		if(this.mSocket != null){
			handlerPacket();
		}
	}


	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Packet packet = null;
					inputStream = mSocket.getInputStream();
					do {
						
						int tempHeadCount = inputStream.read(headBuffer, 0,headBuffer.length);
						if(tempHeadCount <=0){
							handlerPacketException("SocketException", new SocketException(), longLinkConnectionListener);
							break;
						}
						
						if (tempHeadCount == headBuffer.length) {//
							packet = new Packet();
							packet.setInHead(headBuffer);
							if (packet.getMessageLen() > 0) {
								byte[] messageBodyArray = new byte[packet.getMessageLen()];
								int tempMessageBodyCount = inputStream.read(
										messageBodyArray, 0,
										messageBodyArray.length);
								if(tempMessageBodyCount <=0){
									handlerPacketException("SocketException", new SocketException(), longLinkConnectionListener);
									break;
								}
								Log.d(TAG, "tempMessageBodyCount"+tempMessageBodyCount);
								if (tempMessageBodyCount == messageBodyArray.length) {
									packet.setInBody(messageBodyArray);
									// 回调消息
									backToListener(packet);
								}else{
									Log.e(TAG, " body message take fail ,exception message");
								}
							}else{
								
								if(packet.getMessageType()>=0){
									backToListener(packet);
								}else{
									Log.e(TAG, " head message take fail ,exception message");
								}
							}
							
						}

					} while ( inputStream != null );

				}catch(SocketTimeoutException e){
					handlerPacketException("SocketTimeoutException", e, longLinkConnectionListener);
				}catch(SocketException e){
					handlerPacketException("SocketException", e, longLinkConnectionListener);
				}catch (IOException e) {
					handlerPacketException("IOException", e, longLinkConnectionListener);
				}catch(Exception  e){
					handlerPacketException("Exception", e, longLinkConnectionListener);
				}
			}

		}).start();

	}
	
	
	
	/**
	 * 回调pack 到 各个监听
	 * @param packet
	 */
	public void backToListener(Packet packet){
		if(connectionManager != null)
			connectionManager.getPacketListenerManager().switchListener(packet,connectionManager);
		
	}


	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	
	
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

		if (inputStream != null) {
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handlerPacketException(String info, Throwable t,
			ILongLinkConnectionListener longLinkConnectionListener) {
		// TODO Auto-generated method stub
		
		new LongLinkException(info, t,longLinkConnectionListener);;
		
	}
	

	
	
}
