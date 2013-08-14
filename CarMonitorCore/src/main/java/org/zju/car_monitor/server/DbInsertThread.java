package org.zju.car_monitor.server;

import java.util.List;

import org.apache.log4j.Logger;
import org.zju.car_monitor.db.CarMonitorEventDao;
import org.zju.car_monitor.model.Record;
import org.zju.car_monitor.util.CopyOnReadList;
import org.zju.car_monitor.util.CopyOnReadRecordList;

/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 11:07 PM
 */
public class DbInsertThread extends Thread {

    private static Logger logger = Logger.getLogger(DbInsertThread.class);

    private static int MIN_SIZE = 5000;

    private static int SLEEP_SECONDS = 1;

    private static long FIVE_MINUTES = 5 * 1000;

    private static CopyOnReadList copyOnReadRecordList = CopyOnReadRecordList.getInstance();
    @Override
    public void run() {
        long lastInsertTime = System.currentTimeMillis();
        while(true) {
            sleepSeconds(SLEEP_SECONDS);
            long now = System.currentTimeMillis();
            List<Record> recordList = null;
            if ((now - lastInsertTime) > FIVE_MINUTES) {
                recordList = copyOnReadRecordList.makeSnapShot();
                lastInsertTime = now;
            } else {
                recordList = copyOnReadRecordList.makeSnapShot(MIN_SIZE);
            }
            
            if (recordList != null && recordList.size() != 0) {
                logger.info("Fetching " + recordList.size() + " records, inserting..");
                CarMonitorEventDao.bulkInsert(recordList);
                lastInsertTime = System.currentTimeMillis();
            }
        }
    }

    private void sleepSeconds(int seconds) {
        try{
            Thread.sleep(seconds * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
