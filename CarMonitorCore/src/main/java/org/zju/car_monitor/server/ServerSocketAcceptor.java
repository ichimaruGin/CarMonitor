package org.zju.car_monitor.server;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.zju.car_monitor.codec.RecordProtocolCodecFactory;

/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 11:02 PM
 */
public class ServerSocketAcceptor {

    private static Logger logger = Logger.getLogger(ServerSocketAcceptor.class);

    public static void start(int port) {
        try{
            IoAcceptor ioAcceptor = new NioSocketAcceptor();
            ioAcceptor.setHandler(new ServerIoHandler());
            //ioAcceptor.getSessionConfig().setReadBufferSize(BUFFER_SIZE);
            ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new RecordProtocolCodecFactory()));
            logger.info("started acceptor, binding to address " + port);
            ioAcceptor.bind(new InetSocketAddress(port));
        }catch (Exception e) {
            logger.error("failed to start acceptor ", e);
        }

    }
}
