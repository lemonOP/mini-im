package com.james.im.transport.task;


import com.james.im.message.IMServerMessageProtocol.HeartbeatRequest;
import com.james.im.message.IMServerMessageProtocol.MessageType;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 心跳任务请求任务
 * @author James
 *
 */
public class HeartBeatReqTask extends Task {

	
	private ConnectionManager connectionManager;
	
	private Packet packet;
	
	public HeartBeatReqTask() {
		// TODO Auto-generated constructor stub
	}

	public HeartBeatReqTask(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
		this.connectionManager = connectionManager;
	}


	@Override
	public void sendPacket() {
		// TODO Auto-generated method stub
		if(this.connectionManager != null)
			this.connectionManager.getPacketWrite().putPacket(handlerPacket());
		
	}

	@Override
	public Packet handlerPacket() {
		// TODO Auto-generated method stub
		HeartbeatRequest heartbeatRequest = HeartbeatRequest.newBuilder().build();
		packet = new Packet();
		packet.setMessageLen(heartbeatRequest.toByteArray().length);
		packet.setMessageType(MessageType.HEARTBEAT_REQ_VALUE);
		packet.setMessageBody(heartbeatRequest.toByteArray());
		return packet;
	}

}
