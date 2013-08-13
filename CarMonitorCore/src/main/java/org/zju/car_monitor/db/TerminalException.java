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
	
	public static List<TerminalException> findNoOfEvents(String terminalID, int number) {
		Criteria c = Hibernate.currentSession().createCriteria(TerminalException.class).createAlias("terminal", "terminal")
			.add(Restrictions.eq("terminal.terminalId", terminalID)).
			addOrder(Order.desc("createdAt"));
		c.setMaxResults(number);
		List<TerminalException> list = c.list();
		if (list == null || list.size() == 0) return null;
		return list;
	}
	
	public static List<TerminalException> findUnProcessEvents(String terminalID) {
		
		Criteria c = Hibernate.currentSession().createCriteria(TerminalException.class).createAlias("terminal", "terminal")
				.add(Restrictions.eq("terminal.terminalId", terminalID)).add(Restrictions.eq("processFlag", "N")).
				addOrder(Order.desc("createdAt"));
			List<TerminalException> list = c.list();
			if (list == null || list.size() == 0) return null;
			return list;
		
	}
	
	public static void processException(String id) {
		Criteria c = Hibernate.currentSession().createCriteria(TerminalException.class).add(Restrictions.eq("id", id));
		TerminalException e = (TerminalException) c.list().get(0);
		e.setProcessFlag("Y");
		e.saveOrUpdate();
	}
	

}