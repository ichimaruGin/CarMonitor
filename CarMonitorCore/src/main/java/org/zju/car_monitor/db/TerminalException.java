package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.XmlUtil;

@Entity
@Table(name = "terminal_exceptions")
public class TerminalException extends DbObject implements Constants{
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
	Terminal terminal = null;
	
	@Column(name = "code")
	String code = null;
	
	@Column(name = "long_value")
	Long longValue = 0L;
	
	@Column(name = "char_value")
	String charValue = null;
	
	@Column(name = "process_flag")
	String processFlag = null;

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getLongValue() {
		return longValue;
	}
	
	public String getCharValue() {
		return charValue;
	}
	
	public void setCharValue(String value) {
		this.charValue = value;
	}

	public void setLongValue(Long value) {
		this.longValue = value;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	
	public static List<TerminalException> findNoOfEventsByCode(String terminalID, int number, String code) {
		Criteria c = Hibernate.currentSession().createCriteria(TerminalException.class).createAlias("terminal", "terminal")
			.add(Restrictions.eq("terminal.terminalId", terminalID))
			.add(Restrictions.eq("code", code))
			.addOrder(Order.desc("createdAt"));
		c.setMaxResults(number);
		List<TerminalException> list = c.list();
		if (list == null || list.size() == 0) return null;
		return list;
	}
	
	public static TerminalException findUnProcessEventsByCode(String terminalID, String code) {
		
		Criteria c = Hibernate.currentSession().createCriteria(TerminalException.class).createAlias("terminal", "terminal")
				.add(Restrictions.eq("terminal.terminalId", terminalID)).add(Restrictions.eq("processFlag", "N"))
				.add(Restrictions.eq("code",code)).
				addOrder(Order.desc("createdAt"));
			c.setMaxResults(1);
			List<TerminalException> list = c.list();
			if (list == null || list.size() == 0) return null;
			return list.get(0);
		
	}
	
	public static void processException(String id) {
		Criteria c = Hibernate.currentSession().createCriteria(TerminalException.class).add(Restrictions.eq("id", id));
		TerminalException e = (TerminalException) c.list().get(0);
		e.setProcessFlag("Y");
		e.saveOrUpdate();
	}
	
	
    public static String createExceptionsXML(final String terminalId, final String type) {
    	String xml = (String) Hibernate.readOnly(new ReadOnlyTask<String>() {

			public String doWork() {
				List<TerminalException> list = TerminalException.findNoOfEventsByCode(terminalId, 100, type);
				StringBuilder builder = new StringBuilder();
				builder.append("<List>");
				
				if (list != null) {
					for (TerminalException exception: list) {
						builder.append("<event>");
						builder.append(XmlUtil.pair("id", exception.getId()));
						builder.append(XmlUtil.pair("time", exception.getCreatedAt().toString()));
						if (type.equals(Constants.EXCEPTION_CODE_HIGH_SPEED)) {
							builder.append(XmlUtil.pair("value", exception.getLongValue() + " 公里每小时"));
						} else if (type.equals(Constants.EXCEPTION_CODE_TIRED_DRIVE)) {
							builder.append(XmlUtil.pair("value",  exception.getLongValue() + " 分钟"));
						} else if (type.equals(Constants.EXCEPTION_CODE_OBD_ERR)) {
							builder.append(XmlUtil.pair("value", exception.getCharValue()));
						} else if (type.equals(Constants.EXCEPTION_CODE_DRUNK)) {
							builder.append(XmlUtil.pair("value", exception.getLongValue()));
						}
						String process;
						if ("N".equals(exception.getProcessFlag())) {
							process = "未处理";
						}else {
							process = "已处理";
						}
						builder.append(XmlUtil.pair("processFlag", process));
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