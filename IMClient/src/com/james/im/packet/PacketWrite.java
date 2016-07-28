package com.james.im.packet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

import com.james.im.configurationmanager.PacketConfiguration;
import com.james.im.exception.LongLinkException;
import com.james.im.log.Log;
import com.james.im.transport.connection.listener.ILongLinkConnectionListener;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 包写出
 * 
 * @author james
 * 
 */
public class PacketWrite implements IPacket {

	private static final String TAG = PacketWrite.class.getSimpleName();
	
	private Socket mSocket;

	private OutputStream outputStream;
	
	private ConnectionManager connectionManager;
	
	private ILongLinkConnectionListener longLinkConnectionListener;
	
	private LinkedBlockingQueue<Packet> linkedBlockingQueue = new LinkedBlockingQueue<>(PacketConfiguration.CAPACITY);
	
	
			
	public PacketWrite() {
		// TODO Auto-generated constructor stub
	}

	public PacketWrite(Socket mSocket) {
		this.mSocket = mSocket;
		if(this.mSocket != null){
			handlerPacket();
		}
	}
	
	public PacketWrite(Socket mSocket,ILongLinkConnectionListener longLinkConnectionListener){
		this.mSocket = mSocket;
		this.longLinkConnectionListener = longLinkConnectionListener;
		if(this.mSocket != null){
			handlerPacket();
		}
	}

	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub
		try {
			outputStream = this.mSocket.getOutputStream();
			
			loop();// 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void loop(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				do{
						Packet packet;
						try {
							packet = linkedBlockingQueue.take();
							if(packet != null){
							 byte[] buffer = PacketUtil.newInstance().toBuffer(packet.getMessageLen(), packet.getMessageType(), packet.getMessageBody());
							 try {
								outputStream.write(buffer, 0, buffer.length);
								outputStream.flush();
							 }catch(SocketException e){
								handlerPacketException("SocketException", e, longLinkConnectionListener);
							 }catch(IOException e) {
								handlerPacketException("IOException", e, longLinkConnectionListener);
							 }catch (Exception e){
								handlerPacketException("Exception", e, longLinkConnectionListener);
							 }
							}
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
				}while( outputStream != null && linkedBlockingQueue != null );
				
			}
		}).start();
	}
	
	
	public void putPacket(Packet packet){
		if(linkedBlockingQueue == null)throw new NullPointerException("linkedBlockingQueue is null");
		synchronized (this) {
			Log.d(TAG, "queue size="+linkedBlockingQueue.size());
			linkedBlockingQueue.add(packet);
		}
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
		
		if(outputStream != null){
			try {
				
				outputStream.flush();
				outputStream.close();
				outputStream = null;
				
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
