package org.zju.car_monitor.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Uint32 {
	byte[] bts;

	public Uint32(byte fi, byte s, byte t, byte fo) {
		bts = new byte[4];
		bts[0] = fi;
		bts[1] = s;
		bts[2] = t;
		bts[3] = fo;
	}

	public int toNum() {
		ByteBuffer bb = ByteBuffer.wrap(bts);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return bb.getInt();
	}
}
