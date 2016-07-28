package com.james.im.packet.listener;


import com.google.protobuf.InvalidProtocolBufferException;
import com.james.im.exception.LongLinkException;
import com.james.im.log.Log;
import com.james.im.message.IMServerMessageProtocol.ConnectInitResponse;
import com.james.im.message.IMServerMessageProtocol.ConnectInitResultCode;
import com.james.im.packet.Packet;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 链接初始化包监听
 * 
 * @author james
 *
 */
public class ConnectInitPacketListener extends PacketListener {

	private static final String TAG = ConnectInitPacketListener.class.getSimpleName();
	
	
	private ConnectionManager  connectionManager;
	public ConnectInitPacketListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processPacket(Packet packet,ConnectionManager connectionManager) {
		// TODO Auto-generated method stub
		//  初始化连接成功之后，进行定时心跳
		this.connectionManager = connectionManager;
		if(packet != null){
			try {
				ConnectInitResponse connectInitResponse =  ConnectInitResponse.parseFrom(packet.getMessageBody());
				// 初始化成功
				if(connectInitResponse.getCodeValue() == ConnectInitResultCode.SUCCESS_VALUE){
					Log.d(TAG, "init success");
					if(this.connectionManager != null){
						this.connectionManager.getHeartBeatTaskManager().startTimer();
						this.connectionManager.setConnectionRegister(true);
					}
					
				// 初始化失败	
				}else if(connectInitResponse.getCodeValue() == ConnectInitResultCode.FAIL_VALUE){
					Log.d(TAG, "server back exception ,please Check longlinkInfo object ");
				}
				
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				Log.e(TAG,"ConnectInitPacketListener -->InvalidProtocol Exception",e);
			}
			
		}else{
			
			 new LongLinkException("ConnectInit Packet Exception");
			 
		}
		
		
		
		
	}

}
