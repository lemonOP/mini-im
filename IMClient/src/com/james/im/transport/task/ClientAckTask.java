package com.james.im.transport.task;


import com.james.im.message.IMServerMessageProtocol.ClientAck;
import com.james.im.message.IMServerMessageProtocol.MessageType;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 客户端ACK 任务
 * @author james
 *
 */
public class ClientAckTask extends Task {

	private ConnectionManager connectionManager;
	
	private String userId ;

	private String messageId;
	
	private Packet packet;
	
	public ClientAckTask() {
		// TODO Auto-generated constructor stub
	}

	public ClientAckTask(ConnectionManager connectionManager){
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
		ClientAck clientAck = ClientAck.newBuilder()
				.setUserId(userId)
				.setMessageId(messageId)
				.build();
		packet = new Packet();
		packet.setMessageLen(clientAck.toByteArray().length);
		packet.setMessageType(MessageType.CLIENT_ACK_VALUE);
		packet.setMessageBody(clientAck.toByteArray());
		return packet;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	
	

}
