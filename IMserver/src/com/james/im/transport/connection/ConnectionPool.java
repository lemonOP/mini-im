package com.james.im.transport.connection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.james.im.execption.IMConnectionException;
import com.james.minilog.MiniLog;

/**
 * 通讯管道池
 * 
 * @author James
 * 
 */

public class ConnectionPool {

	public static final String TAG = ConnectionPool.class.getSimpleName();

	private static final int CONNECTION_NUMBER = 10;

	private static Map<String, Connection> connectionPool = new ConcurrentHashMap<>(
			CONNECTION_NUMBER);

	public ConnectionPool() {

	}

	/**
	 * 添加连接点
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public int add(String key, Connection value) {

		try {
			if (connectionPool.size() > CONNECTION_NUMBER)
				return 0;
			boolean isContains = connectionPool.containsKey(key);
			if (isContains) {
				remove(key);
				connectionPool.put(key, value);
			} else {
				connectionPool.put(key, value);
			}
			MiniLog.d(TAG, "connection Pool size = " + connectionPool.size());
			return connectionPool.size();
		} catch (Exception e) {
			MiniLog.e(TAG, e);
			try {
				throw new IMConnectionException();
			} catch (IMConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 删除连接点
	 * 
	 * @param key
	 * @return
	 */
	public int remove(String key) {

		try {
			if (connectionPool.size() > CONNECTION_NUMBER)
				return 0;
			boolean isContains = connectionPool.containsKey(key);
			if (isContains) {
				connectionPool.remove(key);
				return connectionPool.size();
			}
			MiniLog.d(TAG, "connectionPool remove key=" + key
					+ " connection Pool size" + connectionPool.size());

		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 迭代连接点
	 */
	public void iterator() {

		Set<Map.Entry<String, Connection>> set = connectionPool.entrySet();
		Iterator<Entry<String, Connection>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<String, Connection> entry = iterator.next();
			MiniLog.d(TAG, "connection key" + entry.getKey());
			MiniLog.d(TAG, "connection value" + entry.getValue());
		}

	}
	
	

	public static Map<String, Connection> getConnectionPool() {
		return connectionPool;
	}

	public static void setConnectionPool(Map<String, Connection> connectionPool) {
		ConnectionPool.connectionPool = connectionPool;
	}

}
