package com.james.im.packet;

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
		this.messageType = PacketUtil.newInstance().headBufferToMessageType(buffer);
	}
	
	/**
	 * 读设置body
	 * @param buffer
	 */
	public void setInBody(byte[] buffer){
		this.messageBody = buffer;
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
