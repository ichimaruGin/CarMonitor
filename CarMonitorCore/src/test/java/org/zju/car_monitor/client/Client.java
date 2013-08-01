package org.zju.car_monitor.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 11:21 PM
 */
public class Client {

    Logger logger = Logger.getLogger(Client.class);
    private IoSession session = null;
    ConnectFuture cf = null;
    public Client(String ipAddress, int port) {
        logger.info("Starting client");
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30*1000L);
        connector.setHandler(new ClientIoHandler());
        cf = connector.connect(new InetSocketAddress(ipAddress,port));
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        cf.awaitUninterruptibly();
        session = cf.getSession();
        logger.info("End starting client");
    }

    public void send(byte[] bytes)  {
        IoBuffer ioBuffer = IoBuffer.allocate(198);
        ioBuffer.put(bytes);
        ioBuffer.flip();
        session.write(ioBuffer);
    }


}
