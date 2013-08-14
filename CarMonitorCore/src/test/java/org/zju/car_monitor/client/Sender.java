package org.zju.car_monitor.client;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

/**
 * User: jiezhen
 * Date: 3/21/13
 * Time: 8:24 AM
 */
public class Sender extends Thread{

    private static Logger logger = Logger.getLogger(Sender.class);
    private byte[] hexMessage;
    private long interval = 0;
    private Client client;

    private String removeSpace(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Sender(byte[] bytes) {
        new Sender(bytes, 0);
    }

    public Sender(byte[] bytes, long interval)  {
        logger.info("Creating sender");
        this.hexMessage = bytes;
        this.interval = interval;
        logger.info("End creating sender");
    }

    public Sender(String message, long interval) {
        this.hexMessage = hexStringToByteArray(removeSpace(message));
        this.interval = interval;
    }

    public void send(){
        if (client == null) client = new Client("localhost", 7001);
        client.send(hexMessage);
    }

    public static byte[] hexStringToByteArray(String s) {

        return DatatypeConverter.parseHexBinary(s);

    }

    @Override
    public void run() {

        for (;;) {
            send();
            if (interval == 0) break;
            else {
                try{
                    Thread.sleep(interval);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
