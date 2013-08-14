package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

/**
 * @author jiezhen 7/20/13
 */
@Entity
@Table(name = "terminal_event_attr_char")
public class TerminalEventAttrChar extends DbObject{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    TerminalEvent event = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_id")
    TerminalEventAttribute attribute = null;

    @Column(name = "attr_value")
    String attrValue = null;


    public TerminalEvent getEvent() {
        return event;
    }

    public void setEvent(TerminalEvent event) {
        this.event = event;
    }

    public TerminalEventAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(TerminalEventAttribute attribute) {
        this.attribute = attribute;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }
    
    public static TerminalEventAttrChar getEventAttrCharByEventIdAndType(String eventId, String attrCode) {
    	List<TerminalEventAttrChar> list = Hibernate.currentSession().createCriteria(TerminalEventAttrChar.class).createAlias("event","event").createAlias("attribute", "attribute").
    	add(Restrictions.eq("event.id", eventId)).add(Restrictions.eq("attribute.attrCode", attrCode )).list();
    	if (list == null || list.size() == 0) return null;
    	else return list.get(0);
    }


    public static List<TerminalEventAttrChar> getEventAttrCharByEventId(String eventId) {
        return Hibernate.currentSession().createCriteria(TerminalEventAttrChar.class).createAlias("event", "event").add(Restrictions.eq("event.id", eventId)).list();
    }
}
