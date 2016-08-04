package com.james.im.packet;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数据包
 * @author james
 *
 */
public class Packet {

	
	/**
	 * 消息长度
	 */
	private int messageLen;
	/**
	 * 消息类型
	 * 
	 */
	private int messageType = -1;
	/**
	 * 消息内容
	 */
	private byte[] messageBody;
	
	
	public Packet() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * 读设置头部
	 * @param buffer
	 */
	public void setInHead(byte[] buffer){
		this.messageLen = PacketUtil.newInstance().headBufferToMessageBodyLen(buffer);
	}
	
	/**
	 * 读设置body
	 * @param buffer
	 */
	public void setInBody(byte[] buffer){
		byte[] messageTypeBuffer = ArrayUtils.subarray(buffer, 0, 1);// 可自行添加 需要的内容
		this.messageType = PacketUtil.newInstance().headBufferToMessageType(messageTypeBuffer);
		byte[] messageContentBuffer =  ArrayUtils.subarray(buffer, 1, buffer.length);
		this.messageBody = messageContentBuffer;
	}
	
	
	/**
	 * 写设置头部
	 * @param buffer
	 */
	public void setOutHead(byte[] buffer){
			
	}
	
	/**
	 * 写设置body
	 * @param buffer
	 */
	public void setOutBody(byte[] buffer){
		
	}
	
	public int getMessageLen() {
		return messageLen;
	}


	public void setMessageLen(int messageLen) {
		this.messageLen = messageLen;
	}



	public int getMessageType() {
		return messageType;
	}



	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}



	public byte[] getMessageBody() {
		return messageBody;
	}



	public void setMessageBody(byte[] messageBody) {
		this.messageBody = messageBody;
	}


	
	
	
}
