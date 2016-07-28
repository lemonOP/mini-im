package com.james.im.info;

/**
 * 长连接 信息
 * 
 * @author james
 * 
 */
public class LongLinkInfo {

	/**
	 * 用户ID
	 */
	public String userId;
	/**
	 * 设备ID
	 */
	public String deviceId;
	/**
	 * 会话ID
	 */
	public String sessionId;

	/**
	 * 是否使用SSL
	 */
	public boolean isUsedSSL;

	public LongLinkInfo() {
		// TODO Auto-generated constructor stub
	}

	public LongLinkInfo(Builder builder) {
		this.userId = builder.userId;
		this.deviceId = builder.deviceId;
		this.sessionId = builder.sessionId;
	}

	public boolean checkLongLink() {
		return this.userId != null && this.userId.length() != 0 ? (this.deviceId != null
				&& this.deviceId.length() != 0 ? this.sessionId != null
				&& this.sessionId.length() != 0 : false)
				: false;
	}

	public static class Builder {

		/**
		 * 用户ID
		 */
		private String userId;
		/**
		 * 设备ID
		 */
		private String deviceId;
		/**
		 * 会话ID
		 */
		private String sessionId;

		/**
		 * 是否使用SSL
		 */
		private boolean isUsedSSL;

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder deviceId(String deviceId) {
			this.deviceId = deviceId;
			return this;
		}

		public Builder sessionId(String sessionId) {
			this.sessionId = sessionId;
			return this;
		}

		public Builder isUsedSSL(boolean isUsedSSL) {
			this.isUsedSSL = isUsedSSL;
			return this;
		}

		public LongLinkInfo build() {

			return new LongLinkInfo(this);
		}

	}

}
