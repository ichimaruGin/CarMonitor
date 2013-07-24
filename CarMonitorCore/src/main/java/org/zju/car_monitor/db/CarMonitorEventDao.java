package org.zju.car_monitor.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.zju.car_monitor.model.Record;
import org.zju.car_monitor.util.DbUtil;


/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 2:22 PM
 */
public class CarMonitorEventDao {
    private static final Logger logger = Logger.getLogger(CarMonitorEventDao.class);

    private static String statementText = "LOAD DATA LOCAL INFILE 'file.txt' " +
            " INTO TABLE cash_info " +
            " fields terminated by ','" +
            " (sno, currency, face_value, version, setup_date, setup_date_time, " +
            " flag, error_code1, error_code2, error_code3, machine_sno, user_number) ";

    private static Connection singleConnection = null;
    static {
        singleConnection = DbUtil.getConnection();
    }

    public static void bulkInsert(List<Record> records) {
        int failureCounts = 0;
        int max_retries = 3;
        com.mysql.jdbc.Statement statement = null;
        InputStream inputStream = null;
        //try three times before giving up.
        do{
            try {
                if (singleConnection.isValid(1) == false) {
                    singleConnection = DbUtil.getConnection();
                }
                statement = (com.mysql.jdbc.Statement)singleConnection.createStatement();
                statement.execute("SET UNIQUE_CHECKS=0; ");
                statement.execute("ALTER TABLE cash_info DISABLE KEYS");
                StringBuilder sb = new StringBuilder();
                for (Record record: records) {
                    record.toString(sb);
                }
                inputStream = IOUtils.toInputStream(sb.toString());
                statement.setLocalInfileInputStream(inputStream);
                statement.execute(statementText);

                statement.execute("SET UNIQUE_CHECKS=1; ");
                statement.execute("ALTER TABLE cash_info ENABLE KEYS; ");
            } catch (Exception e) {
                failureCounts ++;
                e.printStackTrace();
            } finally {
                try{
                    if (statement != null) {
                        statement.close();
                    }
                    if (inputStream!= null) {
                        IOUtils.closeQuietly(inputStream);
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } while (failureCounts > 0 && failureCounts < max_retries);

    }
}


