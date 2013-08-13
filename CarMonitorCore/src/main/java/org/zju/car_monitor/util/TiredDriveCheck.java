package org.zju.car_monitor.util;

import java.util.HashMap;

import org.zju.car_monitor.db.Terminal;
import org.zju.car_monitor.db.TerminalException;

public class TiredDriveCheck {
	
	private static HashMap<String, Long> t1 = new HashMap<String, Long>();
	private static HashMap<String, Long> t2 = new HashMap<String, Long>();
	

	private static boolean exceed20Mins(long existing, long now) {
		return (now - existing) > Config.TIRED_DRIVE_REST_TIME;
	}
	
	private static boolean exceed3hours(long existing, long now) {
		return (now - existing) > Config.TIRED_DRIVE_MAX_DRIVE_TIME;
	}
	
	public static void checkTiredDrive(String terminalId, long rpm) {
		
		// case 1: 
		long ct = System.currentTimeMillis();
		if (rpm > 0) {
			Long tt1 = t1.get(terminalId);
			if (tt1 == null) {
				t1.put(terminalId, ct);
			} else if (exceed3hours(tt1, ct)) {
				TerminalException exception = new TerminalException();
				Terminal terminal = TerminalCache.getTerminal(terminalId);
				exception.setTerminal(terminal);
				exception.setProcessFlag("N");
				exception.setCode(TerminalException.EXCEPTION_CODE_TIRED_DRIVE);
				exception.setLongValue((ct - tt1)/60000);
				exception.save();
				t1.put(terminalId, ct);
				t2.put(terminalId, null);
			}
		}else if (rpm == 0) {
			Long tt2 = t2.get(terminalId);
			if (tt2 != null) {
				if (exceed20Mins(tt2, ct) ) {
					t1.put(terminalId, ct);
					t2.put(terminalId, ct);
				}
			}else {
				t2.put(terminalId, ct);
			}
			
		} 	
	}
	

}
