package org.zju.car_monitor.util;

import org.zju.car_monitor.db.TerminalException;

public class DrunkDriveCheck {
	
	public static long MAX_DRUNK_VALUE = 600;
	
	private static boolean lastIsDrunk = false;
	
	public static void checkDrunkDrive(String terminalId, long value) {
		
		if (value > MAX_DRUNK_VALUE) {
			 if (!lastIsDrunk) {
				TerminalException exception = new TerminalException();
				exception.setCode(TerminalException.EXCEPTION_CODE_DRUNK);
				exception.setProcessFlag("N");
				exception.setTerminal(TerminalCache.getTerminal(terminalId));
				exception.setLongValue(value);
				exception.save();
				lastIsDrunk = true;
			 }
			} else if (value <= MAX_DRUNK_VALUE) {
				lastIsDrunk = false;
			}
	}

}
