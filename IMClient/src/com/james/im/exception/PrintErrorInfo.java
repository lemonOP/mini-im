package com.james.im.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.james.im.log.Log;

/**
 * 打印错误信息
 * @author james
 *
 */
public class PrintErrorInfo {

	private static final String TAG = PrintErrorInfo.class.getSimpleName();
	
	public PrintErrorInfo() {
		// TODO Auto-generated constructor stub
	}

	public static void printError(Throwable ex){
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		
		Log.e(TAG, "错误堆栈信息[ "+sw.toString()+" ]",ex);
		
	}
	
}

