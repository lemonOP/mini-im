package com.james.im.packet;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.james.im.transport.connection.channel.Channel;
import com.james.im.util.ThreadPoolUtil;
import com.james.minilog.MiniLog;

/**
 * 包读处理
 * 
 * @author James
 * 
 */
public class PacketReader implements IPacket {

	private static final String TAG = PacketReader.class.getSimpleName();
	private volatile Channel channel;
	byte[] headBuffer = new byte[4];
	
	public PacketReader(Channel channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
		handlerPacket();
	}

	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub
		ThreadPoolUtil.getCacheThreadPool().submit(new Runnable() {
			

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Packet packet = null;
					InputStream inputStream = channel.getInputStream();
					do {
						
						int tempHeadCount = inputStream.read(headBuffer, 0,headBuffer.length);
						MiniLog.d(TAG, "tempHeadCount="+tempHeadCount);
						if(tempHeadCount <=0){
							handlerPacketException("SocketException", new SocketException());
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
									handlerPacketException("SocketException", new SocketException());
									break;
								}
								MiniLog.d(TAG, "tempMessageBodyCount"+tempMessageBodyCount);
								if (tempMessageBodyCount == messageBodyArray.length) {
									packet.setInBody(messageBodyArray);
									// 回调消息
									//backToListener(packet);
								}else{
									MiniLog.e(TAG, " body message take fail ,exception message");
								}
							}else{
								
								if(packet.getMessageType()>=0){
									//backToListener(packet);
								}else{
									MiniLog.e(TAG, " head message take fail ,exception message");
								}
							}
							
						}

					} while ( inputStream != null );

				}catch(SocketTimeoutException e){
					handlerPacketException("SocketTimeoutException", e);
				}catch(SocketException e){
					handlerPacketException("SocketException", e);
				}catch (IOException e) {
					handlerPacketException("IOException", e);
				}catch(Exception  e){
					handlerPacketException("Exception", e);
				}
			}
		});
		
		
	}
	

	@Override
	public void handlerPacketException(String info, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		if(this.channel != null){
			try {
				this.channel.getInputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
