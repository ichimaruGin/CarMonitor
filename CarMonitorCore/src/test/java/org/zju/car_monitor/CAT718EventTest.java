package org.zju.car_monitor;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zju.car_monitor.client.Client;
import org.zju.car_monitor.server.TestServer;

/**
 * @author jiezhen 7/21/13
 */

@RunWith(JUnit4.class)
public class CAT718EventTest{

    String message = "&CAT718#<00001>#<50>#<100>#<100>#<8000>#<20>#<0>#<0>#<0>#<0>#<0>#<0>!";
    private static Logger logger = Logger.getLogger(CAT718EventTest.class);

//    public void prepare() {
//        TestServer testServer = new TestServer();
//        testServer.start();
//        logger.info("server started.");
//
//    }

    private byte[] stringToByteArray(String str) {
        char[] chars = str.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i ++){
            bytes[i] = (byte) chars[i];
        }
        return bytes;

    }
    
    private static Client client = new Client("127.0.0.1", 7001);

//    @Test
//    public void testOneMessage() {
//        byte[] bytes = stringToByteArray(message);
//        client.send(bytes);
//    }
//    
    @Test
    public void testErrMessage() {
    	String msg = "&CATOBD#<00001>#<8999>#<7123>#<A567>#<>#<>#<>#<>#<>#<B111>!";
    	byte[] bytes = stringToByteArray(msg);
    	client.send(bytes);
    }

}
