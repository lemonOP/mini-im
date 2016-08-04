package com.james.im.simple;

import org.apache.commons.lang3.ArrayUtils;

import com.james.im.log.Log;
import com.james.im.packet.Packet;
import com.james.im.packet.PacketUtil;
import com.james.minilog.MiniLog;

public class ByteSimple {

	private static final String TAG = ByteSimple.class.getSimpleName();
	public ByteSimple() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		byte[] buffer = "sadasdasd".getBytes();
		int messageLen = buffer.length;
		int messageType = 1;
		Packet packet = new Packet();
		packet.setMessageLen(messageLen);
		packet.setMessageType(messageType);
		packet.setMessageBody(buffer);
		
		byte[] tempBuffer = PacketUtil.newInstance().toBuffer( packet.getMessageType(), packet.getMessageBody());
		
		Packet packet2 = new Packet();
		byte[] tempheadArray = ArrayUtils.subarray(tempBuffer, 0, 4);
		packet2.setInHead(tempheadArray);
		byte[] messageBodyArray = ArrayUtils.subarray(tempBuffer, 4,tempBuffer.length);
		packet2.setInBody(messageBodyArray);
		
		
		Log.d(TAG, packet2.getMessageLen()+"");
		Log.d(TAG, packet2.getMessageType()+"");
		Log.d(TAG, packet2.getMessageBody()+"");
		Log.d(TAG, new String(packet2.getMessageBody())+"");
	}

}
