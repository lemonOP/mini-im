package com.james.im.simple.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.james.im.server.LongLinkServer;
import com.james.im.server.PacketHandlerReceiver;

public class ClientTest {
	@Test
	public void test() {
		LongLinkServer longLinkServer = new 
				LongLinkServer
				.Builder()
				.host("localhost")
				.port(9001)
				.packetHandlerReceiver(new PacketHandlerReceiver())
				.build();
		longLinkServer.settingAddress();
		longLinkServer.settingLongLinkInfo(
				"231231", 
				"77OS3I20-23NSD123-123SD-231A", 
				"123123ASDAS123ASD123435SDF34TSDF"
				);
		longLinkServer.startLink();
		
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
