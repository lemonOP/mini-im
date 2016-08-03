package com.james.im.transport.connection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.james.im.execption.IMConnectionException;
import com.james.minilog.MiniLog;

/**
 * 通讯管道池
 * temp Connection pool 
 * @author James
 * 
 */
public class ConnectionPool {

	public static final String TAG = ConnectionPool.class.getSimpleName();
   
	private static volatile ConnectionPool INSTANCE;
	
	private static final int CONNECTION_NUMBER = 10;

	private volatile static Map<String, Connection> tempconnectionPool = new ConcurrentHashMap<>(CONNECTION_NUMBER);
	
	private ReadWriteLock rw = new ReentrantReadWriteLock();
	private Lock w = rw.writeLock();
	private Lock r = rw.readLock();

	private ConnectionPool() {

	}
	
	public static synchronized ConnectionPool newInstance(){
		
		if(INSTANCE == null){
			INSTANCE = new ConnectionPool();
		}
		
		return INSTANCE;
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
			if (tempconnectionPool.size() > CONNECTION_NUMBER)
				return 0;
			boolean isContains = tempconnectionPool.containsKey(key);
			w.lock();
			if (isContains) {
				remove(key);
				tempconnectionPool.put(key, value);
			} else {
				tempconnectionPool.put(key, value);
			}
			MiniLog.d(TAG, "connection Pool size = " + tempconnectionPool.size());
			return tempconnectionPool.size();
		} catch (Exception e) {
			MiniLog.e(TAG, e);
			try {
				throw new IMConnectionException(e);
			} catch (IMConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			w.unlock();
		}
		return 0;
	}

	/**
	 * 获取连接点
	 * 
	 * @param key
	 * @return
	 */
	public Connection get(String key) {
		if (tempconnectionPool.size() <= 0)
			return null;
		boolean isContains = tempconnectionPool.containsKey(key);

		if (isContains) {
			r.lock();
			try {
				return tempconnectionPool.get(key);
			} finally {
				r.unlock();
			}

		}
		return null;
	}

	/**
	 * 删除连接点
	 * 
	 * @param key
	 * @return
	 */
	public int remove(String key) {

		try {
			if (tempconnectionPool.size() > CONNECTION_NUMBER)
				return 0;
			boolean isContains = tempconnectionPool.containsKey(key);
			if (isContains) {
				synchronized (this) {
					tempconnectionPool.remove(key);
				}
				return tempconnectionPool.size();
			}
			MiniLog.d(TAG, "connectionPool remove key=" + key
					+ " connection Pool size" + tempconnectionPool.size());

		} catch (Exception e) {
			MiniLog.e(TAG, e);
			try {
				throw new IMConnectionException(e);
			} catch (IMConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 迭代连接点
	 */
	public void iterator() {

		Set<Map.Entry<String, Connection>> set = tempconnectionPool.entrySet();
		Iterator<Entry<String, Connection>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<String, Connection> entry = iterator.next();
			MiniLog.d(TAG, "connection key" + entry.getKey());
			MiniLog.d(TAG, "connection value" + entry.getValue());
		}

	}
	
	
	/**
	 * shutdown temp connection
	 *  
	 */
	public void shutdown(){
		if(tempconnectionPool != null && tempconnectionPool.size() > 0){
			Set<Map.Entry<String, Connection>> set = tempconnectionPool.entrySet();
			Iterator<Entry<String, Connection>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<String, Connection> entry = iterator.next();
				MiniLog.d(TAG, "connection key" + entry.getKey());
				MiniLog.d(TAG, "connection value" + entry.getValue());
				Connection conn = entry.getValue();
				conn.shutdown();
			}
		}
	}

	public static Map<String, Connection> getConnectionPool() {
		return tempconnectionPool;
	}

	public static void setConnectionPool(Map<String, Connection> connectionPool) {
		ConnectionPool.tempconnectionPool = connectionPool;
	}
	
	
	
	
	
	
	

}
