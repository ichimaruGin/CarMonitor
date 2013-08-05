package org.zju.car_monitor.util;

import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(Hibernate.class);

    static {
        try {
        	logger.info("Start to initializing Hibernate");
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(Terminal.class);
            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(Map.class);
            configuration.addAnnotatedClass(CAT718TerminalEvent.class);
            configuration.addAnnotatedClass(TerminalEvent.class);
            configuration.addAnnotatedClass(CAT718EventAttribute.class);
            configuration.addAnnotatedClass(TerminalEventAttrChar.class);
            configuration.addAnnotatedClass(TerminalEventAttrLong.class);
            configuration.addAnnotatedClass(TerminalEventAttribute.class);
            configuration.addAnnotatedClass(CATOBDEventAttribute.class);
            configuration.addAnnotatedClass(CATOBDTerminalEvent.class);
            
            logger.info("After adding classes");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/car_monitor_db?useUnicode=true&amp;characterEncoding=UTF-8");
            configuration.setProperty("hibernate.connection.charSet", "utf8");
            configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
            configuration.setProperty("hibernate.connection.characterSetResults", "utf8");
            configuration.setProperty("hibernate.connection.connectionCollation", "utf8_unicode_ci");
            configuration.setProperty("hibernate.connection.useUnicode", "true");
            configuration.setProperty("hibernate.connection.username","car_db_user");
            configuration.setProperty("hibernate.connection.password","password");
            configuration.setProperty("hibernate.c3p0.min_size", "5");
            configuration.setProperty("hibernate.c3p0.max_size", "20");
            configuration.setProperty("hibernate.c3p0.timeout","1800");
            configuration.setProperty("hibernate.c3p0.max_statements", "50");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
            logger.info("After setting properties");
            factory = configuration.buildSessionFactory();
            logger.info("End creating factory");
        } catch (Exception ex) {
            logger.info(ex);
            ex.printStackTrace();
        }
    }
    
    public static Object readOnly(ReadOnlyTask task) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = getSession();
            localSession.set(session);
            transaction = session.beginTransaction();
            return task.doWork();
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
            transaction.rollback();
            session.close();
        }
        return null;

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
    	if (factory == null) {
    		throw new RuntimeException("factory is null, it was not initialized properly");
    	}
        Session session = factory.openSession();
        return session;
    }


}

