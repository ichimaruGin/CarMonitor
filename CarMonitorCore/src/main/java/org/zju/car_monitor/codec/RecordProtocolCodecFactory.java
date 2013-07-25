package org.zju.car_monitor.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * User: jiezhen
 * Date: 3/21/13
 * Time: 2:42 PM
 */
public class RecordProtocolCodecFactory implements ProtocolCodecFactory{
    private final DataDecoder decoder;

    public RecordProtocolCodecFactory() {
        decoder = new DataDecoder();
    }
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        throw new UnsupportedOperationException("no encoder defined");
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
