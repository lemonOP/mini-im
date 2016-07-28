package com.james.im.packet.listener;

import com.james.im.exception.LongLinkException;
import com.james.im.log.Log;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 心跳消息监听
 * @author james
 *
 */
public class HeartBeatPacketListener extends PacketListener {

	private static final String TAG = HeartBeatPacketListener.class.getSimpleName();
	
	public HeartBeatPacketListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processPacket(Packet packet,ConnectionManager connectionManager) {
		// TODO Auto-generated method stub
		Log.d(TAG, "heartbeat packet");
		if(packet != null){
			 connectionManager.getHeartBeatTaskManager().resetTimer();// 重置心跳
		}else{
			new LongLinkException("HeartBeat Packet Exception ");
		}
		
	}

}
