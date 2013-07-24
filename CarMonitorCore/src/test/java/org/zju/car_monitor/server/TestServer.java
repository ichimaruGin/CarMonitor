package org.zju.car_monitor.server;

/**
 * @author jiezhen 7/21/13
 */
public class TestServer extends Thread {

    @Override
    public void run() {
        Server server = new Server();
        server.start();
    }
}
