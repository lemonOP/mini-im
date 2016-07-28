package com.james.im.util;

/**
 * 工具方面的基本接口
 * 
 * @author James
 * 
 */
public interface IUtil {

	interface UtilCallBack {
		void utilSuccess(Object obj);

		void utilFail(Object obj);
	}

	void util(UtilCallBack utilCallBack);

}
