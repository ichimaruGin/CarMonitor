package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

/**
 * @author jiezhen 7/21/13
 */
@Entity
@DiscriminatorValue("CAT718")
public class CAT718TerminalEvent extends TerminalEvent{
	
	public static CAT718TerminalEvent findLatestEvent(String terminalId) {
		List<CAT718TerminalEvent> l =  findNoOfEvents(terminalId, 1);
		if (l != null) return l.get(0);
		else return null;
	}
	
	
	public static List<CAT718TerminalEvent> findNoOfEvents(String terminalID, int number) {
		Criteria c = Hibernate.currentSession().createCriteria(CAT718TerminalEvent.class).createAlias("terminal", "terminal")
			.add(Restrictions.eq("terminal.terminalId", terminalID)).
			addOrder(Order.desc("createdAt"));
		c.setMaxResults(number);
		List<CAT718TerminalEvent> list = c.list();
		if (list == null || list.size() == 0) return null;
		return list;
	}	
	
}
