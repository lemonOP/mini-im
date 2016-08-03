package com.james.im.util;

import org.apache.commons.lang3.ArrayUtils;


/**
 * 整理包结构工具
 * 
 * @author james
 * 
 */
public class PacketUtil implements IUtil {

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
		
		byte[] headBuffer = intToByteArray(head);
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
		byte[] messageBodyLen = ArrayUtils.subarray(buffer, 0, buffer.length);
		return byteArrayToInt(messageBodyLen);
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

	@Override
	public void util(UtilCallBack utilCallBack) {
		// TODO Auto-generated method stub
		
	}

}