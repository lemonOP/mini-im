package com.james.im.simple.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.james.im.IMServer;
import com.james.minilog.MiniLog;

public class IMServerTest {

	private static final String TAG = IMServerTest.class.getSimpleName();

	@Test
	public void f() {
		MiniLog.d(TAG, "begin f");
		IMServer imserver = new IMServer();
		imserver.startIMServer();
		MiniLog.d(TAG, "end f");
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
