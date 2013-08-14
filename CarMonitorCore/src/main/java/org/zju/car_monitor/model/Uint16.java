package org.zju.car_monitor.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class Uint16 {
	byte[] bts;

	public Uint16(byte first, byte second) {
		bts = new byte[2];
		bts[0] = first;
		bts[1] = second;
	}

	public int toNum() {
		int i = 0;
		i |= bts[1] & 0xFF;
		i <<= 8;
		i |= bts[0] & 0xFF;
		return i;
	}

	public static void copyToIntArray(Uint16[] from, int[] to) {
		if (from.length != to.length) {
//			P.ptl("length not match");
		}
		for (int i = 0, len = to.length; i < len; i++) {
			to[i] = from[i].toNum();
		}
	}

	//TODO
	/**
	 * 判断是否为协议连接开始
	 * @param b
	 * @return
	 */
	public static boolean isConnectionStart(Uint16[] msg) {
		return false;
	}

	//TODO
	/**
	 * 判断是否为协议连接结束
	 * @param b
	 * @return
	 */
	public static boolean isConnectionEnd(Uint16[] msg) {
		return false;
	}

	private static Uint16[] getn(byte[] b, int n, int spos) {
		Uint16 unit[] = new Uint16[n];
		for (int i = 0; i < n; i++) {
			unit[i] = new Uint16(b[spos + i * 2], b[spos + i * 2 + 1]);
		}
		return unit;
	}

	public static Uint16 get1(byte[] b, int spos) {
		Uint16 unit = new Uint16(b[spos], b[spos + 1]);
		return unit;
	}

	public static Uint16[] get2(byte[] b, int spos) {
		return getn(b, 2, spos);
	}

	public static Uint16[] get4(byte[] b, int spos) {
		return getn(b, 4, spos);
	}

	public static Uint16[] get6(byte[] b, int spos) {
		return getn(b, 6, spos);
	}

	public static Uint16[] get12(byte[] b, int spos) {
		return getn(b, 12, spos);
	}

	public static Uint16[] get24(byte[] b, int spos) {
		return getn(b, 24, spos);
	}


	public static String arrayTostring(Uint16[] units) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = units.length; i < len; i++) {
			if (units[i].toNum() != 0) {
				sb.append(units[i].toChar());
			}
		}
		return sb.toString();
	}

	public static String bytes2HexString(Uint16[] uints) {
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[uints.length * 2];
		for(int i = 0; i < uints.length; i++) {
			b[i*2] = uints[i].bts[0];
			b[i*2 + 1] = uints[i].bts[1];
		}

		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public char toChar() {
		return (char) bts[0];
	}

	public Date toDate() {
		int num = toNum();
		int y = num / 512;
		int m = (num - y * 512) / 32;
		int d = num - y * 512 - m * 32;
		
		Calendar cal = Calendar.getInstance();
		// The second parameter is the month of year, it's equals to m-1
		cal.set(y + 1980, m - 1, d); 
		
		Date date = new Date(cal.getTimeInMillis());
		return date;
	}

	public Timestamp toTime(Date date) {
		int num = toNum();
		int h = num / 2048;
		int mi = (num - h * 2048) / 32;
		int se = (num - h * 2048 - mi * 32);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) , cal.get(Calendar.DATE), h, mi, se);
		return new Timestamp(cal.getTimeInMillis());
	}
}
