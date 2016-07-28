package com.james.im.info;

/**
 * 服务配置 
 * 1:端口 
 * 2:可配置多少客户端链接服务限制
 * 
 * @author James
 * 
 */
public class ServerInfo {

	/**
	 * 端口
	 */
	public int port = 9001;

	/**
	 * 链接数量
	 */
	public int connNumber = 5;

	public ServerInfo() {
		// TODO Auto-generated constructor stub
	}

	public ServerInfo(Builder builder) {
		this.port = builder.port;
		this.connNumber = builder.connNumber;
	}

	public static class Builder {
		/**
		 * 端口
		 */
		private int port;

		/**
		 * 链接数量
		 */
		private int connNumber;

		public Builder port(int port) {
			this.port = port;
			return this;
		}

		public Builder connNumber(int connNumber) {
			this.connNumber = connNumber;
			return this;
		}

		public ServerInfo build() {
			return new ServerInfo(this);
		}

	}

}
