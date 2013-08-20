package org.zju.car_monitor.util;

import java.util.HashMap;

import org.zju.car_monitor.db.Terminal;

public class TerminalCache extends AbstractCache{
	
	public static HashMap<String, Terminal> data = new HashMap<String, Terminal>();
	public static Terminal getTerminal(String terminalId) {
		if (shouldExpire()) {
			data = new HashMap<String, Terminal>();
			advanceTimer();
		}
		Terminal terminal = data.get(terminalId);
		if (terminal == null) {
			terminal = Terminal.findByTerminalId(terminalId);
			data.put(terminalId, terminal);
		}
		
		return terminal;
		
	}
	
	
}
