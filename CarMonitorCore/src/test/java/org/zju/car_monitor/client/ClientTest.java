package org.zju.car_monitor.client;

import org.apache.log4j.Logger;

/**
 * User: jiezhen
 * Date: 3/21/13
 * Time: 10:06 AM
 */
public class ClientTest {

    private static Logger logger = Logger.getLogger(ClientTest.class);

    private static String testMessage = "55 aa 55 aa 01 00 84 00 00 00 31 00 57 00 52 " +
            "00 2d 00 30 00 30 00 30 00 2d 00 2d 00 4e 00 39 00 41 00 31 00 32 00 32 00 " +
            "35 00 30 00 32 00 38 00 39 00 30 00 00 00 00 00 00 00 00 00 14 00 0a 00 07 " +
            "00 1a 00 00 00 01 00 2d 00 53 00 4e 00 6f 00 01 00 00 00 00 00 01 00 02 00 " +
            "03 00 39 42 c1 98 01 00 00 00 00 00 00 00 43 00 4e 00 59 00 00 00 02 00 0a " +
            "00 0a 00 4f 00 59 00 39 00 39 00 38 00 34 00 38 00 38 00 30 00 39 00 00 00 " +
            "00 00 57 00 52 00 2d 00 30 00 30 00 30 00 2d 00 2d 00 4e 00 39 00 41 00 31 " +
            "00 32 00 32 00 35 00 30 00 32 00 38 00 39 00 30 00 00 00 00 00 00 00 00 00 " +
            "00 00 cd 1b aa aa 55 55";


    public static void main(String args[]) {
        int noOfClientThreads = 200;

        for (int i = 0; i < noOfClientThreads; i ++) {
            Sender sender = new Sender(testMessage, 40);
            logger.info("started sender # " + i);
            sender.start();
        }

    }
}
