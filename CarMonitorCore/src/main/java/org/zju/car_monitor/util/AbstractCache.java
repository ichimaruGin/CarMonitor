package org.zju.car_monitor.util;

import java.util.HashMap;


public class AbstractCache {
	
	protected static long expireTime = 900000;
	
	protected static long lastVisitTime = System.currentTimeMillis();
	
	public static boolean shouldExpire() {
		long currentTime = System.currentTimeMillis();
		return (currentTime - lastVisitTime > expireTime);
	}
	
	public static void advanceTimer() {
		lastVisitTime = System.currentTimeMillis();
	}
	
	public static void expire() {
		lastVisitTime = 0;
	}

	
}