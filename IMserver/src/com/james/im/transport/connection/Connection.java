package com.james.im.transport.connection;

import com.james.im.transport.connection.channel.Channel;


/**
 * 处理端到端网络点
 * @author James
 *
 */
public class Connection  {

	
	private static final String TAG = Connection.class.getSimpleName();
	
	
	private Channel channel;
	
	public Connection() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
	
	
	

}
