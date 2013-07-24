package org.zju.car_monitor.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.zju.car_monitor.db.*;

import java.util.Properties;

/**
 * @author jiezhen 7/17/13
 */
public class Hibernate {

    private static SessionFactory factory = null;
    private static ThreadLocal<Session> localSession = new ThreadLocal<Session>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(Hibernate.class.getResourceAsStream("/hibernate.properties"));
            Configuration configuration = new Configuration().setProperties(properties);
            configuration.addAnnotatedClass(Terminal.class);
            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(Map.class);
            configuration.addAnnotatedClass(CAT718TerminalEvent.class);
            configuration.addAnnotatedClass(TerminalEvent.class);
            configuration.addAnnotatedClass(CAT718EventAttribute.class);
            configuration.addAnnotatedClass(TerminalEventAttrChar.class);
            configuration.addAnnotatedClass(TerminalEventAttrLong.class);
            configuration.addAnnotatedClass(TerminalEventAttribute.class);

            factory = configuration.buildSessionFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readOnly (ReadOnlyTask task) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = getSession();
            localSession.set(session);
            transaction = session.beginTransaction();
            task.doWork();
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
            transaction.rollback();
            session.close();
        }

    }


    public static void readWrite (ReadWriteTask task) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = getSession();
            localSession.set(session);
            transaction = session.beginTransaction();
            task.doWork();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static Session currentSession() {
        return localSession.get();
    }

    private static Session getSession() {
        Session session = factory.openSession();
        return session;
    }


}

