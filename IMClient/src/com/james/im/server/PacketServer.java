package com.james.im.server;

import com.james.im.exception.LongLinkException;
import com.james.im.log.Log;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 *  
 * 包服务
 * 读写服务 
 * 此服务面向于主工程 
 * @author james
 *
 */
public class PacketServer implements IPacketServer {

	private LongLinkServer longLinkServer;
	
	private static final String TAG = PacketServer.class.getSimpleName();
	
	/**
	 * 消息回调
	 */
	private IPacketHandlerReceiver packetHandlerReceiver;
	
	
	public PacketServer() {
		// TODO Auto-generated constructor stub
	}

	
	public PacketServer(LongLinkServer longLinkServer){
		
		this.longLinkServer = longLinkServer;
		setPacketHandlerReceiverListener(this.longLinkServer.packetHandlerReceiver);
	}
	
	
	
	
	
	@Override
	public void pushPacket(Packet packet) {
		// TODO Auto-generated method stub
		ConnectionManager connectionManager = this.longLinkServer.getConnectionManager();
		if(connectionManager != null){
			if(connectionManager.isConnectionState()){
				if(connectionManager.isConnectionRegister()){
					connectionManager.getPacketWrite().putPacket(packet);
				}else{
					Log.e(TAG, "connection not register");
				}
			}else{
				Log.e(TAG, "not connection");
			}
		}else{
			//重连
			if(this.longLinkServer != null){
				
				this.longLinkServer.getConnectionManager()
								   .getResetConnectionManger()
								   .startResetConnection();
				
			}else{
				
				Log.e(TAG, "longlinkServer is null",new LongLinkException("longlinkServer is null"));
				
			}
		}
		
	}

	@Override
	public void pullPacket(Packet packet) {
		// TODO Auto-generated method stub
		// pull 到主工程中
		if(packetHandlerReceiver != null)
			this.packetHandlerReceiver.onReceive(packet);
		
	}

	
	
	
	/**
	 * 设置消息监听
	 * @param packetHandlerReceiver
	 */
	public void setPacketHandlerReceiverListener(IPacketHandlerReceiver packetHandlerReceiver){
		this.packetHandlerReceiver = packetHandlerReceiver;
	}
	
	
	
	
	
	
	
	
	
	
}
