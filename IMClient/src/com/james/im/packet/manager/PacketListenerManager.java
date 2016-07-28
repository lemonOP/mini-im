package com.james.im.packet.manager;


import java.util.HashMap;
import java.util.Map;

import com.james.im.message.IMServerMessageProtocol.MessageType;
import com.james.im.packet.Packet;
import com.james.im.packet.listener.AckPacketListener;
import com.james.im.packet.listener.ConnectInitPacketListener;
import com.james.im.packet.listener.DialogMessagePacketListener;
import com.james.im.packet.listener.HeartBeatPacketListener;
import com.james.im.packet.listener.NoticeMessagePacketListener;
import com.james.im.packet.listener.PacketListener;
import com.james.im.transport.connection.manager.ConnectionManager;

/**
 * 包监听管理
 * @author James
 *
 */
public class PacketListenerManager {


	private static Map<Integer,PacketListener> packetListeners = new HashMap<Integer,PacketListener>();
	
	
	private ConnectionManager connectionManager;
	/**
	 * ack 消息监听
	 */
	private AckPacketListener ackPacketListener;
	
	/**
	 * 连接初始化消息监听
	 */
	private ConnectInitPacketListener connectInitPacketListener;
	
	/**
	 * 心跳消息监听
	 */
	private HeartBeatPacketListener heartBeatPacketListener;
	
	
	/**
	 * 普通消息监听
	 */
	private DialogMessagePacketListener dialogMessagePacketListener;
	

	/**
	 * 通知消息监听
	 */
	private NoticeMessagePacketListener noticeMessagePacketListener;
	
	
	
	
	public PacketListenerManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	public PacketListenerManager(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}
	
	
	
	public synchronized void putListener(int key,PacketListener packetListener){
	
		packetListeners.put(key, packetListener);
		
	}
	
	
	public synchronized void removeListener(int key,PacketListener packetListener){
		
		packetListeners.remove(key);
		
	}
	
	public synchronized PacketListener getListener(int key){
		
		return packetListeners.get(key);
	}
	
	
	
	
	/**
	 * 连接成功 注册消息监听
	 */
	public void initListener(){
		ackPacketListener = new AckPacketListener();
		connectInitPacketListener = new ConnectInitPacketListener();
		heartBeatPacketListener = new HeartBeatPacketListener();
		dialogMessagePacketListener = new DialogMessagePacketListener();
		noticeMessagePacketListener = new NoticeMessagePacketListener();
		
		putListener(MessageType.SERVER_ACK_VALUE,ackPacketListener);
		putListener(MessageType.CONNECT_INIT_RES_VALUE,connectInitPacketListener);
		putListener(MessageType.HEARTBEAT_RES_VALUE,heartBeatPacketListener);
		putListener(MessageType.BASE_MSG_VALUE,dialogMessagePacketListener);
		// todo	需要修改
		putListener(MessageType.OTHER_MSG_VALUE,noticeMessagePacketListener);
		
	
	}
	
	
	/**
	 * 选择listener
	 * @param packet
	 * @param connectionManager
	 */
	public void switchListener(Packet packet,ConnectionManager connectionManager){
		PacketListener packetListener = getListener(packet.getMessageType());
		packetListener.processPacket(packet,connectionManager);
	}


	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}


	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	

	
}
