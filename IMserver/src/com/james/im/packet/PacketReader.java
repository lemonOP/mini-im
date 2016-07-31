package com.james.im.packet;

import java.io.IOException;
import java.io.InputStream;

import com.james.im.execption.IMConnectionException;
import com.james.im.transport.connection.channel.Channel;
import com.james.minilog.MiniLog;

/**
 * 包读处理
 * 
 * @author James
 * 
 */
public class PacketReader implements IPacketHandler {

	private static final String TAG = PacketReader.class.getSimpleName();
	private volatile Channel channel;
	byte[] head = new byte[4];
	
	public PacketReader(Channel channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;

	}

	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub
		InputStream inputStream = channel.getInputStream();
	
		
		do {
			
			try {
				int headCount = inputStream.read(head, 0, head.length);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				MiniLog.e(TAG,e);
				new IMConnectionException(e);
			}
			
		} while (this.channel.getSocket() != null
				&& this.channel.getInputStream() != null);
	}

	@Override
	public void handlerPacketException(String info, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
