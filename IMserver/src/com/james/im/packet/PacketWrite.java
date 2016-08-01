package com.james.im.packet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

import com.james.im.config.PacketConfiguration;
import com.james.im.transport.connection.channel.Channel;
import com.james.im.util.PacketUtil;
import com.james.im.util.ThreadPoolUtil;

/**
 * 包写处理
 * 
 * @author james
 * 
 */
public class PacketWrite implements IPacket {

	private Channel channel;

	private LinkedBlockingQueue<Packet> linkedBlockingQueue = new LinkedBlockingQueue<>(
			PacketConfiguration.CAPACITY);

	public PacketWrite(Channel channel) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
	}

	@Override
	public void handlerPacket() {
		// TODO Auto-generated method stub

	}

	public void loop() {
		ThreadPoolUtil.getCacheThreadPool().submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				OutputStream outputStream = channel.getOutputStream();
				do {
					Packet packet;
					try {
						packet = linkedBlockingQueue.take();
						if (packet != null) {
							byte[] buffer = PacketUtil.newInstance().toBuffer(
									packet.getMessageLen(),
									packet.getMessageType(),
									packet.getMessageBody());
							try {
								outputStream.write(buffer, 0, buffer.length);
								outputStream.flush();
							} catch (SocketException e) {
								handlerPacketException("SocketException", e);
							} catch (IOException e) {
								handlerPacketException("IOException", e);
							} catch (Exception e) {
								handlerPacketException("Exception", e);
							}
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} while (outputStream != null && linkedBlockingQueue != null);

			}
		});
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
