package org.zju.car_monitor.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Record {
	private Date date;
	private Timestamp time;
	private int flag;
	private int err1;
	private int err2;
	private int err3;
	private String currency;
	private String version;
	private int value;
	private int charNum;
	private String uid;
	private String cashierCode;
	private long userId;

    public StringBuilder toString(StringBuilder sb) {
        sb.append(uid +",");
        sb.append(currency +",");
        sb.append(value +",");
        sb.append(version + ",");
        sb.append(date.toString() +",");
        sb.append(time.toString() + ",");
        sb.append(flag + ",");
        sb.append(err1 + ",");
        sb.append(err2 +",");
        sb.append(err3 +",");
        sb.append(cashierCode +",");
        sb.append(userId+"\n");
        return sb;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return toString(sb).toString();
    }
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getErr1() {
		return err1;
	}

	public void setErr1(int err1) {
		this.err1 = err1;
	}

	public int getErr2() {
		return err2;
	}

	public void setErr2(int err2) {
		this.err2 = err2;
	}

	public int getErr3() {
		return err3;
	}

	public void setErr3(int err3) {
		this.err3 = err3;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getCharNum() {
		return charNum;
	}

	public void setCharNum(int charNum) {
		this.charNum = charNum;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCashierCode() {
		return cashierCode;
	}

	public void setCashierCode(String cashierCode) {
		this.cashierCode = cashierCode;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
