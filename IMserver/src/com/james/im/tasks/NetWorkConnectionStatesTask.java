package com.james.im.tasks;

import java.io.IOException;
import java.io.InputStream;

import com.james.minilog.MiniLog;
import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.task.Task;

public class NetWorkConnectionStatesTask extends Task {

	public IHandler handler;

	String[] str = {"ping baidu.com\n","ping taobao.com\n","ping tencent.com\n"};

	private static final String TAG = NetWorkConnectionStatesTask.class.getSimpleName();
	public NetWorkConnectionStatesTask(Builder builder) {
		this.handler = builder.handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MiniLog.d(TAG, "NetWorkConnectionStatesTask");
		InputStream inputStream = null;
		for (int i = 0; i < str.length; i++) {
			try {
				Process process = Runtime.getRuntime().exec(str[i]);
				inputStream  = process.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				int backCount = 0;
				while((len=inputStream.read(buffer) )!= 0){
					String str = new String(buffer);
					if(str.indexOf("ttl") > 0){
						this.handler.back(true);
						MiniLog.d(TAG, "connection success");
					}else{
						this.handler.back(false);
						MiniLog.d(TAG, "connection fail");
					}
					if(backCount == 2)break;
					backCount++;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}

	public static class Builder {
		private IHandler handler;

		public Builder handler(IHandler handler) {
			this.handler = handler;
			return this;
		}

		public NetWorkConnectionStatesTask build() {
			return new NetWorkConnectionStatesTask(this);
		}

	}

}
