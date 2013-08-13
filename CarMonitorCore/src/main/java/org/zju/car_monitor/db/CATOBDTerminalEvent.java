package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.util.Hibernate;


@Entity
@DiscriminatorValue(value = "CATOBD")
public class CATOBDTerminalEvent extends TerminalEvent {
	
	public static CATOBDTerminalEvent findLatestEvent(String terminalId) {
		List<CATOBDTerminalEvent> l =  findNoOfEvents(terminalId, 1);
		if (l != null) {
			long now = System.currentTimeMillis();
			CATOBDTerminalEvent event =  l.get(0);
			long updTime = event.createdAt.getTime();
			if (now - Constants.TIME_OUT_SECONDS > updTime) event = null;
			return event;
		}
		else return null;
	}
	
	
	public static List<CATOBDTerminalEvent> findNoOfEvents(String terminalID, int number) {
		Criteria c = Hibernate.currentSession().createCriteria(CATOBDTerminalEvent.class).createAlias("terminal", "terminal")
			.add(Restrictions.eq("terminal.terminalId", terminalID)).
			addOrder(Order.desc("createdAt"));
		c.setMaxResults(number);
		List<CATOBDTerminalEvent> list = c.list();
		if (list == null || list.size() == 0) return null;
		return list;
	}

}
