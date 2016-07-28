package com.james.im.simple;

import java.io.UnsupportedEncodingException;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.james.im.config.MessageProtocolConfig;
import com.james.im.message.IMServerMessageProtocol.BaseMessage;
import com.james.im.message.IMServerMessageProtocol.ConnectInitRequest;
import com.james.im.message.IMServerMessageProtocol.ContentType;
import com.james.im.message.IMServerMessageProtocol.Message;
import com.james.minilog.MiniLog;

public class MessageProtocolSimple {

	
	private static final String TAG = MessageProtocolSimple.class.getSimpleName();
	
	public MessageProtocolSimple() {
		// TODO Auto-generated constructor stub
	}

	
	public static void main(String[] args) {
		
		ConnectInitRequest.Builder builder = ConnectInitRequest.newBuilder();
		builder.setProtocolVersion(MessageProtocolConfig.VERSION_NUMBER);
		builder.setUserId("1001");
		builder.setNetworkEnvironment("WIFI");
		ConnectInitRequest connectInitRequest = builder.build();
		byte[] buffer = connectInitRequest.toByteArray();
		try {
			ConnectInitRequest connRequest = ConnectInitRequest.parseFrom(buffer);
			MiniLog.d(TAG, connRequest.getNetworkEnvironment());
			MiniLog.d(TAG, connRequest.getProtocolVersion());
			MiniLog.d(TAG, connRequest.getUserId());
				
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		BaseMessage.Builder baseBuilder = BaseMessage.newBuilder();
		baseBuilder.setClientMessageId("1");
		baseBuilder.setFromId("2");
		baseBuilder.setToId("3");
		baseBuilder.setMessageId("4");
		baseBuilder.setTime(System.currentTimeMillis()+"");
		BaseMessage baseMessage = baseBuilder.build();
		Message.Builder messageBuilder = Message.newBuilder();
		messageBuilder.setBasemessage(baseMessage);
		messageBuilder.setContentType(ContentType.TEXT);
		ByteString byteString;
		try {
			byteString = ByteString.copyFrom("hello  world", "UTF-8");
			messageBuilder.setMessageContent(byteString);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Message message = messageBuilder.build();
		
		try {
			Message message1 = Message.parseFrom(message.toByteArray());
			BaseMessage baseMessage1 = message1.getBasemessage();
			
			MiniLog.d(TAG, baseMessage1.getClientMessageId());
			MiniLog.d(TAG, baseMessage1.getFromId());
			MiniLog.d(TAG, baseMessage1.getToId());
			MiniLog.d(TAG, baseMessage1.getMessageId());
			MiniLog.d(TAG, baseMessage1.getTime());
			MiniLog.d(TAG, message1.getContentTypeValue());
			
			ByteString  str = message1.getMessageContent();
			MiniLog.d(TAG,str.toStringUtf8());
			
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
