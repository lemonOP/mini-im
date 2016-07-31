package com.james.im.simple.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.james.im.transport.server.impl.BaseTranSportServer;
import com.james.minilog.MiniLog;

public class ReflectTest {

	private static final String TAG = ReflectTest.class.getSimpleName();

	@Test
	public void reflectTest() {
		MiniLog.d(TAG, BaseTranSportServer.class.getName());
		
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
