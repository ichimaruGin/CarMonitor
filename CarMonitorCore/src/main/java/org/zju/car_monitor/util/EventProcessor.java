package org.zju.car_monitor.util;

import org.apache.log4j.Logger;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.db.CAT718EventAttribute;
import org.zju.car_monitor.db.CAT718TerminalEvent;
import org.zju.car_monitor.db.CATOBDEventAttribute;
import org.zju.car_monitor.db.CATOBDTerminalEvent;
import org.zju.car_monitor.db.EventType;
import org.zju.car_monitor.db.Terminal;
import org.zju.car_monitor.db.TerminalEvent;
import org.zju.car_monitor.db.TerminalEventAttrChar;
import org.zju.car_monitor.db.TerminalEventAttrLong;
import org.zju.car_monitor.db.TerminalEventAttribute;
import org.zju.car_monitor.db.TerminalException;

/**
 * @author jiezhen 7/21/13
 */
public final class EventProcessor {

    private static Logger logger = Logger.getLogger(EventProcessor.class);

    public static void process(byte[] messageBytes) {
        if (messageBytes == null || messageBytes.length == 0) {
            throw new RuntimeException("message bytes to be processed is null or zero length");
        }
        final String event = new String(messageBytes);
        logger.info("Process event: " + event);

        if (event.length() <= 6 || !event.startsWith("&") || !event.endsWith("!")) {
            throw new RuntimeException("Unexpected event length or header or tail " + event);
        }
        String header = event.substring(1, 7);
        final EventType eventType = EventType.valueOf(header);
        Hibernate.readWrite(new ReadWriteTask() {
            public void doWork() {
                switch (eventType) {
                    case CAT718: decodeAndSaveCAT718(event); break;
                    case CATOBD: decodeAndSaveCATOBD(event); break;
                }

            }
        });


    }

    private static Long strToLongValue(String value) {
        if (value == null || value.length() == 0)
            return null;
        return Long.parseLong(value);
    }
    
    private static String stripValue(String value) {
    	if (value.length() < 2 || value.charAt(0) != '<' || value.charAt(value.length() -1) != '>') {
    		logger.error("Not a valid value " + value);
    		return "";
    	}
    	return value.substring(1, value.length() -1);
    }
    
    private static void saveTerminalEvent(TerminalEvent terminalEvent, String terminalId, String body) {
    	Terminal terminal = TerminalCache.getTerminal(terminalId);
        terminalEvent.setTerminal(terminal);
        terminalEvent.setProcessFlag("N");
        terminalEvent.setRawMessage(body);
        terminalEvent.save();
    }
    
    //&CATOBD#<1>#<2>#<3>#<4>#<5>#<6>#<7>#<8>#<9>#<10>!
	private static void decodeAndSaveCATOBD(String message) {
		CATOBDTerminalEvent terminalEvent = new CATOBDTerminalEvent();
        String[] values = parseValues(message);
        String terminalId = stripValue(values[0]);
        saveTerminalEvent(terminalEvent, terminalId, message);
        logger.info("Saved CATOBD terminalEvent for terminalId " + terminalId);
        for(int i = 1; i<=9 ; i ++) {
        	String value = stripValue(values[i]);
        	if (value == null  || value.length() == 0) break; 
        		else if ( value.length() != 4 ) {
        		logger.error("Wrong error code received: " + value);
        		continue;
        	}
        	char m = value.charAt(0);
        	String convertedCode = "";
        	switch (m) {
        		case '0' : convertedCode = "P0"; break;
        		case '1' : convertedCode = "P1"; break;
        		case '2' : convertedCode = "P2"; break;
        		case '3' : convertedCode = "P3"; break;
        		case '4' : convertedCode = "C0"; break;
        		case '5' : convertedCode = "C1"; break;
        		case '6' : convertedCode = "C2"; break;
        		case '7' : convertedCode = "C3"; break;
        		case '8' : convertedCode = "B0"; break;
        		case '9' : convertedCode = "B1"; break;
        		case 'A' : convertedCode = "B2"; break;
        		case 'B' : convertedCode = "B3"; break;
        		case 'C' : convertedCode = "U0"; break;
        		case 'D' : convertedCode = "U1"; break;
        		case 'E' : convertedCode = "U2"; break;
        		case 'F' : convertedCode = "U3"; break;
        		default : convertedCode = "UN"; break; //unknown;
        	}
        	
        	String convertedValue = convertedCode + value.substring(1);
        	saveCharAttrValue(terminalEvent, convertedValue, CATOBDEventAttribute.carObdErrEventAttribute());
        	saveToException(terminalId, convertedValue);
        }
		
	}
	
	
	private static void saveToException(String terminalId, String value) {
		
		TerminalException exception = new TerminalException();
		Terminal terminal = TerminalCache.getTerminal(terminalId);
		exception.setTerminal(terminal);
		exception.setCode(Constants.EXCEPTION_CODE_OBD_ERR);
		exception.setProcessFlag("N");
		exception.setCharValue(value);
	}
	private static String[] parseValues(String message) {
        final String body = message.substring(8).replace(">!", ">");
        String[] values = body.split("#");
        return values;
	}
    
    //"&CAT718#<00001>#<50>#<100>#<100>#<8000>#<20>#<0>#<0>#<0>#<0>#<0>#<0>!";
    private static void decodeAndSaveCAT718(String message) {
        CAT718TerminalEvent terminalEvent = new CAT718TerminalEvent();
        String[] values = parseValues(message);
        String terminalId = stripValue(values[0]);
        saveTerminalEvent(terminalEvent, terminalId, message);
        logger.info("Saved CAT718 terminalEvent for terminalId " + terminalId);
        String waterTemp = stripValue(values[1]);
        saveLongAttrValue(terminalEvent, strToLongValue(waterTemp), CAT718EventAttribute.carWaterTempAttribute());
        String oil = stripValue(values[2]);
        saveLongAttrValue(terminalEvent, strToLongValue(oil), CAT718EventAttribute.carOilAttribute());
        String carSpeed = stripValue(values[3]);
        long speed = strToLongValue(carSpeed);
        saveLongAttrValue(terminalEvent, speed, CAT718EventAttribute.carSpeedAttribute());
        HighSpeedCheck.checkHighSpeed(terminalId, speed);
        String carRpm = stripValue(values[4]);
        long rpm = strToLongValue(carRpm);
        saveLongAttrValue(terminalEvent, rpm, CAT718EventAttribute.carRpmAttribute());
        TiredDriveCheck.checkTiredDrive(terminalId, rpm);
        String alochoLevel = stripValue(values[5]);
        long aloch = strToLongValue(alochoLevel);
        DrunkDriveCheck.checkDrunkDrive(terminalId, aloch);
        saveLongAttrValue(terminalEvent, aloch, CAT718EventAttribute.drunkDriveAttribute());
        saveCharAttrValue(terminalEvent, (stripValue(values[6])), CAT718EventAttribute.tiedDriveStateAttribute());
        saveCharAttrValue(terminalEvent, (stripValue(values[7])), CAT718EventAttribute.carLatitudeAttribute());
        saveCharAttrValue(terminalEvent, (stripValue(values[8])), CAT718EventAttribute.carLongitudeAttribute());
        // skip the last reserved field
    }

    private static void saveCharAttrValue(TerminalEvent event, String value, TerminalEventAttribute attribute) {
        if (value != null) {
            TerminalEventAttrChar eventAttrChar = new TerminalEventAttrChar();
            eventAttrChar.setAttribute(attribute);
            eventAttrChar.setEvent(event);
            eventAttrChar.setAttrValue(value);
            eventAttrChar.save();
        }
    }

    private static void saveLongAttrValue(TerminalEvent event, Long value, TerminalEventAttribute attribute) {
        if (value != null) {
            TerminalEventAttrLong eventAttrLong = new TerminalEventAttrLong();
            eventAttrLong.setAttribute(attribute);
            eventAttrLong.setEvent(event);
            eventAttrLong.setAttrValue(value);
            eventAttrLong.save();
        }
    }

}
