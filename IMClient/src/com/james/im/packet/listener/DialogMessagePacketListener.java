package com.james.im.packet.listener;


import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.james.im.log.Log;
import com.james.im.message.IMServerMessageProtocol.BaseMessage;
import com.james.im.message.IMServerMessageProtocol.Message;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;
import com.james.im.transport.task.ClientAckTask;

/**
 * 对话消息监听
 * @author James
 *
 */
public class DialogMessagePacketListener extends PacketListener implements IMessagePacketListener{

	private static final String TAG =  DialogMessagePacketListener.class.getSimpleName();
	
	private ConnectionManager connectionManager;
	
	public DialogMessagePacketListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processPacket(Packet packet,ConnectionManager connectionManager) {
		// TODO Auto-generated method stub
		this.connectionManager = connectionManager;
		Log.d(TAG, "DialogMessagePacketListener");
		
		if(packet != null){
			handlerPacket(packet);
		}else{
			Log.e(TAG,"DialogMessage Packet Exception");
		}
		
	}

	
	/**
	 * 处理包信息
	 * @param packet
	 */
	public void handlerPacket(Packet packet){
		try {
			Message message = Message.parseFrom(packet.getMessageBody());
			BaseMessage baseMessage = message.getBasemessage();
			this.connectionManager.getLongLinkServer().getPacketServer().pullPacket(packet);
			execClientAck(baseMessage.getFromId(),baseMessage.getMessageId());
			handlerHeartBeatTimer();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			Log.e(TAG,"DialogMessagePacketListener -->InvalidProtocol Exception",e);
		}
		
	}
	
	/**
	 * 执行客户端ACK
	 * @param userId
	 * @param messageId
	 */
	public  void execClientAck(String userId,String messageId){
		ClientAckTask clientAckTask = new ClientAckTask(this.connectionManager);
		clientAckTask.setUserId(userId);
		clientAckTask.setMessageId(messageId);
		clientAckTask.sendPacket();
		
	}
	
	
	/**
	 * 处理心跳定时器
	 * 
	 */
	public void handlerHeartBeatTimer(){
		if(this.connectionManager != null)
			this.connectionManager.getHeartBeatTaskManager().resetTimer();
	}
}
