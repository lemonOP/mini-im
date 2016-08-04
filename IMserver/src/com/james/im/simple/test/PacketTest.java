package com.james.im.simple.test;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Test;

import com.james.im.packet.Packet;
import com.james.im.util.PacketUtil;
import com.james.minilog.MiniLog;

public class PacketTest {
	private static final String TAG = PacketTest.class.getSimpleName();

	@Test
	public void fPacketTest() {
		byte[] buffer = "sadasdasd".getBytes();
		MiniLog.d(TAG, "buffer length = " + buffer.length);
		int messageLen = buffer.length;
		int messageType = 1;
		Packet packet = new Packet();
		packet.setMessageLen(messageLen);
		packet.setMessageType(messageType);
		packet.setMessageBody(buffer);
		byte[] tempBuffer = PacketUtil.newInstance().toBuffer(packet.getMessageType(), packet.getMessageBody());
		
		Packet packet2 = new Packet();
		byte[] tempheadArray = ArrayUtils.subarray(tempBuffer, 0, 4);
		packet2.setInHead(tempheadArray);
		byte[] messageBodyArray = ArrayUtils.subarray(tempBuffer, 4,tempBuffer.length);
		packet2.setInBody(messageBodyArray);
		
		
		MiniLog.d(TAG, packet2.getMessageLen()+"");
		MiniLog.d(TAG, packet2.getMessageType()+"");
		MiniLog.d(TAG, packet2.getMessageBody()+"");
		MiniLog.d(TAG, new String(packet2.getMessageBody())+"");
	
	}
}
