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
            @Override
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

    //<1>#<2>#<3>#<4>#<5>#<6>#<7>#<8>#<9>#<10>#<11>#<12>!
    private static void decodeAndSaveCAT718(String messageBody) {
        CAT718TerminalEvent terminalEvent = new CAT718TerminalEvent();
        String[] values = messageBody.split("#");
        String terminalId = values[0];
        Terminal terminal = Terminal.findByTerminalId(terminalId);
        terminalEvent.setTerminal(terminal);
        terminalEvent.setProcessFlag("N");
        terminalEvent.save();

        saveLongAttrValue(terminalEvent, strToLongValue(values[1]), CAT718EventAttribute.carWaterTempAttribute);
        saveLongAttrValue(terminalEvent, strToLongValue(values[2]), CAT718EventAttribute.carOilAttribute);
        saveLongAttrValue(terminalEvent, strToLongValue(values[3]), CAT718EventAttribute.carSpeedAttribute);
        saveLongAttrValue(terminalEvent, strToLongValue(values[4]), CAT718EventAttribute.carRpmAttribute);

        //saveLongAttrValue(terminalEvent, strToLongValue(values[5]), CAT718EventAttribute.drunkDriveAttribute);
        saveCharAttrValue(terminalEvent, (values[6]), CAT718EventAttribute.tiedDriveStateAttribute);
        saveCharAttrValue(terminalEvent, (values[7]), CAT718EventAttribute.carLatitudeAttribute);
        saveCharAttrValue(terminalEvent, (values[7]), CAT718EventAttribute.carLongitudeAttribute);
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
