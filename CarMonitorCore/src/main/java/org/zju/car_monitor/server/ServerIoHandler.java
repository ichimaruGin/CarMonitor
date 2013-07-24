package org.zju.car_monitor.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.zju.car_monitor.model.Record;
import org.zju.car_monitor.util.CopyOnReadRecordList;

/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 11:33 AM
 */
public class ServerIoHandler extends IoHandlerAdapter{

    private static Logger logger = Logger.getLogger(IoHandlerAdapter.class);


    public ServerIoHandler() {
        super();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("session opened: " + session.toString());
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("session closed: " + session.toString());
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        Record record = (Record)message;
        //final byte [] bts = simpleBuffer.array();
        CopyOnReadRecordList.getInstance().add(record);
        //logger.info(record.toString());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }
}
