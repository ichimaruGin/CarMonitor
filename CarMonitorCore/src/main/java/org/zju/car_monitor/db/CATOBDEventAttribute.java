package org.zju.car_monitor.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CATOBD")
public class CATOBDEventAttribute extends TerminalEventAttribute{
	 public static String CAR_OBD_ERR = "CAR_OBD_ERR";
	 
	 private static CATOBDEventAttribute carObdErrAttribute = null;
	 
	 public static CATOBDEventAttribute carObdErrEventAttribute()  {
		 if (carObdErrAttribute == null) {
			 carObdErrAttribute = (CATOBDEventAttribute) findByCode(CAR_OBD_ERR);
		 }
		 return carObdErrAttribute;
	 }

}
