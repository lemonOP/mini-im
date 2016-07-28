package com.james.im.bootloader;

import com.james.im.util.IUtil;
import com.james.im.util.IUtil.UtilCallBack;
import com.james.im.util.ThisNetWorkEnvironmentUtil;

/**
 * 基础环境检测
 * 
 * @author james
 * 
 */
public class BaseBootLoader implements IBootLoader, INetworkBootLoader {

	/**
	 * BASE STATE
	 */
	public volatile String BASESTATE = null;

	public BaseBootLoader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void boot() {
		// TODO Auto-generated method stub
		checkServerNetworkEnvironment();
		checkServerPortBindOrListener();

	}

	@Override
	public void checkServerNetworkEnvironment() {
		// TODO Auto-generated method stub
		IUtil util = new ThisNetWorkEnvironmentUtil();
		util.util(new UtilCallBack() {

			@Override
			public void utilSuccess(Object obj) {
				// TODO Auto-generated method stub
				BASESTATE = "true";
			}

			@Override
			public void utilFail(Object obj) {
				// TODO Auto-generated method stub
				BASESTATE = "false";
			}
		});

	}

	@Override
	public boolean checkServerPortBindOrListener() {
		// TODO Auto-generated method stub

		return false;
	}

}
