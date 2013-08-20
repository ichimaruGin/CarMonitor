package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.util.CarCache;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.XmlUtil;

/**
 * @author jiezhen 7/21/13
 */
@Entity
@DiscriminatorValue("CAT718")
public class CAT718TerminalEvent extends TerminalEvent{
	
	public static CAT718TerminalEvent findLatestEvent(String terminalId) {
		List<CAT718TerminalEvent> l =  findNoOfEvents(terminalId, 1);
		
		if (l != null) {
			long now = System.currentTimeMillis();
			CAT718TerminalEvent event =  l.get(0);
			long updTime = event.createdAt.getTime();
			if (now - Constants.TIME_OUT_SECONDS * 1000 > updTime) event = null;
			return event;
		}
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
	
    public static String createCAT718EventsXML(final String terminalId, final String type) {
  	String xml = (String) Hibernate.readOnly(new ReadOnlyTask<String>() {

			public String doWork() {
				List<CAT718TerminalEvent> list = CAT718TerminalEvent.findNoOfEvents(terminalId, 100);
				StringBuilder builder = new StringBuilder();
				builder.append("<List>");
				
				if (list != null) {
					for (CAT718TerminalEvent event: list) {
						builder.append("<event>");
						TerminalEventAttrLong attr = TerminalEventAttrLong.getEventAttrLongByEventIdAndType(event.getId(), type);
						builder.append(XmlUtil.pair("terminal", event.getTerminal().getTerminalId()));
						Car car = CarCache.getCarByTerminalUUID(event.getTerminal().getId());
						builder.append(XmlUtil.pair("carRegNumber", car.getRegNumber()));
						builder.append(XmlUtil.pair("carDriverName", car.getDriverName()));
						builder.append(XmlUtil.pair("time", attr.getCreatedAt().toString()));
						if (type.equals(Constants.CAR_SPEED_PARAM)) {
							builder.append(XmlUtil.pair("value", attr.getAttrValue() + " 公里每小时"));
						} else if (type.equals(Constants.CAR_RPM_PARAM)) {
							builder.append(XmlUtil.pair("value", (attr.getAttrValue() /4) + " 转每分钟"));
						} else if (type.equals(Constants.CAR_WATER_TEMP_PARAM)) {
							builder.append(XmlUtil.pair("value", (attr.getAttrValue() - 40) + " 摄氏度"));
						}
						
						builder.append("</event>");
					}
				}
				builder.append("<List>");
				
				return builder.toString();
				
			}
  		
  	});
  	
  	return xml;
  }

	
}
