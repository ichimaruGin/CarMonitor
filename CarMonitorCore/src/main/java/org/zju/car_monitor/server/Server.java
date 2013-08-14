package org.zju.car_monitor.server;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * User: jiezhen
 * Date: 3/19/13
 * Time: 10:55 PM
 */
public class Server {
    public static Logger logger = Logger.getLogger(Server.class);
    public static final int port = 7001;

    DbInsertThread insertThread = null;

    public Server(){
        //insertThread = new DbInsertThread();
    }

    public void start() {
//        insertThread.start();
//        logger.info("insertion thread started.");
        ServerSocketAcceptor.start(port);
        logger.info("server socket acceptor started.");
    }

    public static void main(String args[]) throws IOException{
        new Server().start();
    }

}
