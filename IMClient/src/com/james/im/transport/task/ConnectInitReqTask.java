package com.james.im.transport.task;

import com.james.im.configurationmanager.PacketConfiguration;
import com.james.im.log.Log;
import com.james.im.message.IMServerMessageProtocol.ConnectInitRequest;
import com.james.im.message.IMServerMessageProtocol.MessageType;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;
import com.james.minilog.MiniLog;


/**
 * 连接初始化 任务
 * @author James
 *
 */
public class ConnectInitReqTask extends Task {

	
	private ConnectionManager connectionManager;
	
	private static final String TAG = ConnectionManager.class.getSimpleName();

	private Packet packet;
	
	public ConnectInitReqTask() {
		// TODO Auto-generated constructor stub
	}
	
	public ConnectInitReqTask(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}
	
	
	@Override
	public void sendPacket() {
		// TODO Auto-generated method stub
		if(this.connectionManager != null)
			if(handlerPacket() != null){
				this.connectionManager.getPacketWrite().putPacket(packet);
			}
	}
	

	@Override
	public Packet handlerPacket() {
		// TODO Auto-generated method stub
		if(connectionManager.getLongLinkInfo() != null){
			ConnectInitRequest connectInitRequest = 
				ConnectInitRequest
				.newBuilder()
				.setProtocolVersion(PacketConfiguration.PROTOCOLVERSION)//长连接协议版本号
				.setUserId(connectionManager.getLongLinkInfo().userId)// 用户UserID
				.setNetworkEnvironment("WIFI")
				.build();
		
			packet = new Packet();
			packet.setMessageLen(connectInitRequest.toByteArray().length);
			packet.setMessageType(MessageType.CONNECT_INIT_REQ_VALUE);
			packet.setMessageBody(connectInitRequest.toByteArray());
			return packet;
		}else{
			Log.e(TAG, "longLink info is null,please setting longlink info");
			return null;
		}
		
	}


	
	
}
