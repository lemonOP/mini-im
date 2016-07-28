package com.james.im.simple;

import com.james.im.server.LongLinkServer;
import com.james.im.server.PacketHandlerReceiver;

public class TestSimple {

	public TestSimple() {
		// TODO Auto-generated constructor stub
	}

	
	public static void main(String[] args) {
		
		LongLinkServer longLinkServer = new 
				LongLinkServer
				.Builder()
				.host("localhost")
				.port(9501)
				.packetHandlerReceiver(new PacketHandlerReceiver())
				.build();
		longLinkServer.settingAddress();
		longLinkServer.settingLongLinkInfo(
				"231231", 
				"77OS3I20-23NSD123-123SD-231A", 
				"123123ASDAS123ASD123435SDF34TSDF"
				);
		longLinkServer.startLink();
		//new WritePacketSimple(longLinkServer.getPacketServer());
		
		
	}
	
	
}
