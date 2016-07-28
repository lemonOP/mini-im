package com.james.im.packet.listener;


import com.james.im.log.Log;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;
import com.james.im.transport.task.ClientAckTask;


/**
 * 通知消息监听
 * @author james
 *
 */
public class NoticeMessagePacketListener extends PacketListener  implements IMessagePacketListener{

	private static final String TAG = NoticeMessagePacketListener.class.getSimpleName();
	
	private ConnectionManager connectionManager;
	
	
	public NoticeMessagePacketListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processPacket(Packet packet,ConnectionManager connectionManager) {
		// TODO Auto-generated method stub
		Log.d(TAG, "NoticeMessagePacketListener");
		this.connectionManager = connectionManager;
		
		if(packet != null){
			
			handlerPacket(packet);
			
		}else{
			Log.e(TAG, "NoticeMessagePacketListener  Exception ");
		}
		
	}

	@Override
	public void handlerPacket(Packet packet) {
		// TODO Auto-generated method stub
//		try {
//			NoticeMessage noticeMessage =  NoticeMessage.parseFrom(packet.getMessageBody());
//			QueuedMessage queuedMessage = noticeMessage.getQueuedMessage();
//			this.connectionManager.getLongLinkServer().getPacketServer().pullPacket(packet);
//			execClientAck(queuedMessage.getFromFigureId(),queuedMessage.getMessageId());
//			handlerHeartBeatTimer();
//		} catch (InvalidProtocolBufferException e) {
//			// TODO Auto-generated catch block
//			Log.e(TAG,"NoticeMessagePacketListener -->InvalidProtocol Exception",e);
//		}
		
	}

	@Override
	public void execClientAck(String userId, String messageId) {
		// TODO Auto-generated method stub
		
		ClientAckTask clientAckTask = new ClientAckTask(this.connectionManager);
		clientAckTask.setUserId(userId);
		clientAckTask.setMessageId(messageId);
		clientAckTask.sendPacket();
		
	}

	@Override
	public void handlerHeartBeatTimer() {
		
		// TODO Auto-generated method stub
		if(this.connectionManager != null)
			this.connectionManager.getHeartBeatTaskManager().resetTimer();
	}
	
	
	

}
