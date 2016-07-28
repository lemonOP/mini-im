package com.james.im.packet.listener;


import com.google.protobuf.InvalidProtocolBufferException;
import com.james.im.log.Log;
import com.james.im.message.IMServerMessageProtocol.ServerAck;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * Ack 消息监听
 * @author James
 *
 */
public class AckPacketListener extends PacketListener {

	private static final String TAG = AckPacketListener.class.getSimpleName();
	
	@SuppressWarnings("unused")
	private ConnectionManager connectionManager;
	
	public AckPacketListener() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void processPacket(Packet packet,ConnectionManager connectionManager) {
		// TODO Auto-generated method stub
		this.connectionManager = connectionManager;
		if(packet != null){
			try {
				ServerAck  serverAck = ServerAck.parseFrom(packet.getMessageBody());
				Log.d(TAG, "serverAck-->ClientMessageId"+serverAck.getClientMessageId());
				Log.d(TAG, "serverAck-->MessageId"+serverAck.getMessageId());
				this.connectionManager.getLongLinkServer().getPacketServer().pullPacket(packet);
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				Log.e(TAG,"AckPacketListener --> InvalidProtocol Exception",e);
			}
			
		}else{
			Log.e(TAG,"Ack Packet Exception");
		}
		
		
		
		
	}

}
