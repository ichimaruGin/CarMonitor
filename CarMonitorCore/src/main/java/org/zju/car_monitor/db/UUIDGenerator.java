package org.zju.car_monitor.db;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * @author jiezhen 7/17/13
 */
public class UUIDGenerator implements IdentifierGenerator {
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return UUID.randomUUID().toString();
    }
}
