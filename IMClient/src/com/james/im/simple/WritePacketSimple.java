package com.james.im.simple;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.james.im.message.IMServerMessageProtocol.BaseMessage;
import com.james.im.message.IMServerMessageProtocol.ContentType;
import com.james.im.message.IMServerMessageProtocol.Image;
import com.james.im.message.IMServerMessageProtocol.Message;
import com.james.im.message.IMServerMessageProtocol.MessageType;
import com.james.im.packet.Packet;
import com.james.im.server.PacketServer;

/**
 * 
 * @author James
 *
 */
public class WritePacketSimple {

	private PacketServer packetServer;
	
	public WritePacketSimple(PacketServer packetServer) {
		// TODO Auto-generated constructor stub
		this.packetServer = packetServer;
		pushPacket();
	}
	
	
	public void  pushPacket(){
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Packet packet = new Packet();
				packet.setMessageLen(toArray().length);
				packet.setMessageType(MessageType.BASE_MSG_VALUE);
				packet.setMessageBody(toArray());
				packetServer.pushPacket(packet);
			}
		};
	
		timer.schedule(timerTask, 1000*60, 1000*30);
	}
	
	public static void main(String[] args) {
		toArray();
	}
	
	public static byte[] toArray(){
			
			BaseMessage  baseMessage = BaseMessage.newBuilder()
					.setClientMessageId("1")
					.setMessageId("2")
					.setFromId("3")
					.setToId("5")
					.setTime(System.currentTimeMillis()+"")
					.build();
			Image.Builder builder = Image.newBuilder()
					.setImgUrl("1")
					.setName("image_01")
					.setHeight(300)
					.setWidth(400)
					.setSize(400);
			builder.setName("image_02");
			Image image = builder.build();
			image.toByteString();
			
			
			Message.Builder dialogMessageBuilder = Message.newBuilder()
					.setBasemessage(baseMessage)
					.setContentType(ContentType.IMAGE)
					.setMessageContent(image.toByteString());
			
			return dialogMessageBuilder.build().toByteArray();
	}
	
	
	
	
	

}
