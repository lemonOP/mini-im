package com.james.im.packet;

import org.apache.commons.lang3.ArrayUtils;

import com.james.im.configurationmanager.PacketConfiguration;

/**
 * 整理包结构工具
 * 
 * @author james
 * 
 */
public class PacketUtil {

	private static volatile PacketUtil packetUtil;

	private PacketUtil() {
		// TODO Auto-generated constructor stub
	}

	public static PacketUtil newInstance() {
		synchronized (PacketUtil.class) {
			if (packetUtil == null) {
				packetUtil = new PacketUtil();
			}
			return packetUtil;
		}

	}

	/**
	 * 各类消息toBuffer
	 * 
	 * @param headBuffer
	 * @param messageTypeBuffer
	 * @param messageBodyBuffer
	 * @return
	 */
	public byte[] toBuffer(int head, int messageType,byte[] messageBodyBuffer) {
		
		byte[] headBuffer = intToByteArray(head+PacketConfiguration.HEADLEN);
		byte[] messageTypeBuffer = {(byte) messageType};
		byte[] tempheadBuffer = ArrayUtils.addAll(headBuffer, messageTypeBuffer);
		byte[] packetBuffer = ArrayUtils.addAll(tempheadBuffer,messageBodyBuffer);
		return packetBuffer;
	}
	
	

	/**
	 * 头buffer 转 bodyLen
	 * 
	 * @param buffer
	 * @return
	 */
	public int headBufferToMessageBodyLen(byte[] buffer) {
		byte[] messageBodyLen = ArrayUtils.subarray(buffer, 0, 4);
		return byteArrayToInt(messageBodyLen)-PacketConfiguration.HEADLEN;
	}

	/**
	 * 头buffer 转 messageType
	 * 
	 * @param buffer
	 * @return
	 */
	public int headBufferToMessageType(byte[] buffer) {
		byte[] messageTypeBuffer = ArrayUtils.subarray(buffer, 4, 5);
		return messageTypeBuffer[0];
	}

	
	/**
	 * bytearray to int
	 * 
	 * @param b
	 * @return
	 */
	private  int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16
				| (b[0] & 0xFF) << 24;
	}

	/**
	 * int to bytearray
	 * 
	 * @param a
	 * @return
	 */
	private  byte[] intToByteArray(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF),
				(byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}

}
