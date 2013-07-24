package org.zju.car_monitor.server;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.zju.car_monitor.model.Record;
import org.zju.car_monitor.model.Uint16;
import org.zju.car_monitor.model.Uint32;


/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 12:10 PM
 */
public class RecordParser {
    private static Logger logger = Logger.getLogger(RecordParser.class);

    public static Record parseSingleNoImageRecord(byte[] srcBts) {
        byte[] bts = new byte[srcBts.length - 66];
        System.arraycopy(srcBts, 60, bts, 0, bts.length);
        return parseRecord(bts);
    }

    /**
     * Construct the bts into List of Record
     * @param bts the source bts, which from FSN or the format is same as FSN.
     * @return the List of Record.
     */
    private static Record parseRecord(byte [] bts) {
    	Uint32 counter = new Uint32(bts[20], bts[21], bts[22], bts[23]);
//    	System.out.println(validNum.toNum());
        Record rec = new Record();
        Timestamp t = Uint16.get1(bts,  34).toTime(
                Uint16.get1(bts, 32).toDate());
        rec.setDate(new Date(t.getYear(), t.getMonth(), t.getDate()));
        rec.setTime(Uint16.get1(bts,  34).toTime(
                Uint16.get1(bts, 32).toDate()));
        rec.setFlag(Uint16.get1(bts,  36).toNum());
        rec.setErr1(Uint16.get1(bts, 38).toNum());
        rec.setErr2(Uint16.get1(bts, 40).toNum());
        rec.setErr3(Uint16.get1(bts,  42).toNum());
        rec.setCurrency(Uint16.arrayTostring(Uint16.get4(bts, +44)));
        if ("CNY".equalsIgnoreCase(rec.getCurrency())) {
            int versionNum = Uint16.get1(bts,  52).toNum();
            if (versionNum == 0)
                rec.setVersion("1990");
            else if (versionNum == 1)
                rec.setVersion("1999");
            else if (versionNum == 2)
                rec.setVersion("2005");
        } else {
            rec.setVersion("9999");
        }
        rec.setValue(Uint16.get1(bts,  54).toNum());
        rec.setCharNum(Uint16.get1(bts,  56).toNum());

        rec.setUid(Uint16.arrayTostring(Uint16.get12(bts, 58)));
        String cashierCode = Uint16.arrayTostring(Uint16.get24(bts, 82));
        rec.setCashierCode(cashierCode);
        Uint16 userId = Uint16.get1(bts, 130);
        rec.setUserId(userId.toNum());
        return rec;
    }
}
