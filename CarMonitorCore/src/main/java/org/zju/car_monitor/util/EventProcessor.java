package org.zju.car_monitor.util;

import org.apache.log4j.Logger;
import org.zju.car_monitor.db.*;

/**
 * @author jiezhen 7/21/13
 */
public final class EventProcessor {

    private static Logger logger = Logger.getLogger(EventProcessor.class);

    public static void process(byte[] messageBytes) {
        if (messageBytes == null || messageBytes.length == 0) {
            throw new RuntimeException("message bytes to be processed is null or zero length");
        }
        String event = new String(messageBytes);
        logger.info("Process event: " + event);

        if (event.length() <= 6 || !event.startsWith("&") || !event.endsWith("!")) {
            throw new RuntimeException("Unexpected event length or header or tail " + event);
        }
        String header = event.substring(1, 7);
        final EventType eventType = EventType.valueOf(header);
        final String body = event.substring(8);
        Hibernate.readWrite(new ReadWriteTask() {
            public void doWork() {
                switch (eventType) {
                    case CAT718: decodeAndSaveCAT718(body);
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
    
    //"&CAT718#<00001>#<50>#<100>#<100>#<8000>#<20>#<0>#<0>#<0>#<0>#<0>#<0>!";
    private static void decodeAndSaveCAT718(String messageBody) {
        CAT718TerminalEvent terminalEvent = new CAT718TerminalEvent();
        String[] values = messageBody.split("#");
        String terminalId = stripValue(values[0]);
        Terminal terminal = Terminal.findByTerminalId(terminalId);
        terminalEvent.setTerminal(terminal);
        terminalEvent.setProcessFlag("N");
        terminalEvent.save();
        logger.info("Saved terminalEvent for terminalId " + terminalId);
        String waterTemp = stripValue(values[1]);
        saveLongAttrValue(terminalEvent, strToLongValue(waterTemp), CAT718EventAttribute.carWaterTempAttribute());
        String oil = stripValue(values[2]);
        saveLongAttrValue(terminalEvent, strToLongValue(oil), CAT718EventAttribute.carOilAttribute());
        String carSpeed = stripValue(values[3]); 
        saveLongAttrValue(terminalEvent, strToLongValue(carSpeed), CAT718EventAttribute.carSpeedAttribute());
        String carRpm = stripValue(values[4]);
        saveLongAttrValue(terminalEvent, strToLongValue(carRpm), CAT718EventAttribute.carRpmAttribute());
        //saveLongAttrValue(terminalEvent, strToLongValue(values[5]), CAT718EventAttribute.drunkDriveAttribute);
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
