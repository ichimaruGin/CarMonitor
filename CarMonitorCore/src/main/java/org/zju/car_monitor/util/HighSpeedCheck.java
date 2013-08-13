package org.zju.car_monitor.util;

import org.zju.car_monitor.db.TerminalException;

public class HighSpeedCheck {
	
	public static long MAX_SPEED_ALLOWED = 120;
	private static boolean lastIsHighSpeed = false;
	public static void checkHighSpeed(String terminalId, long speed) {
		if (speed > MAX_SPEED_ALLOWED) {
		 if (!lastIsHighSpeed) {
			TerminalException exception = new TerminalException();
			exception.setCode(TerminalException.EXCEPTION_CODE_HIGH_SPEED);
			exception.setProcessFlag("N");
			exception.setTerminal(TerminalCache.getTerminal(terminalId));
			exception.setLongValue(speed);
			exception.save();
			lastIsHighSpeed = true;
		 }
		} else if (speed <= MAX_SPEED_ALLOWED) {
			lastIsHighSpeed = false;
		}
		
	}

}
