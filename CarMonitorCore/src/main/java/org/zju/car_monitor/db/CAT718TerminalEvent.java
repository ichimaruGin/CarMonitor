package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

/**
 * @author jiezhen 7/21/13
 */
@Entity
@DiscriminatorValue("CAT718")
public class CAT718TerminalEvent extends TerminalEvent{
	
	public static CAT718TerminalEvent findLatestEventByTerminalId(String terminalID) {
		List<?> list = Hibernate.currentSession().createCriteria(CAT718TerminalEvent.class).createAlias("terminal", "terminal")
			.add(Restrictions.eq("terminal.terminalId", terminalID)).addOrder(Order.desc("createdAt")).list();
		if (list == null || list.size() == 0) return null;
		else return (CAT718TerminalEvent) list.get(0);
	}
}
