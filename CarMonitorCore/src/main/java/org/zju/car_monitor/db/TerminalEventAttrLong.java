package org.zju.car_monitor.db;

import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

import javax.persistence.*;
import java.util.List;

/**
 * @author jiezhen 7/20/13
 */



@Entity
@Table(name = "terminal_event_attr_long")
public class TerminalEventAttrLong extends DbObject{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    TerminalEvent event = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_id")
    TerminalEventAttribute attribute = null;

    @Column(name = "attr_value")
    long attrValue = 0L;


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

    public long getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(long attrValue) {
        this.attrValue = attrValue;
    }
    
    public static TerminalEventAttrLong getEventAttrLongByEventIdAndType(String eventId, String attrCode) {
    	List<TerminalEventAttrLong> list = Hibernate.currentSession().createCriteria(TerminalEventAttrLong.class).createAlias("event","event").createAlias("attribute", "attribute").
    	add(Restrictions.eq("event.id", eventId)).add(Restrictions.eq("attribute.attrCode", attrCode )).list();
    	if (list == null || list.size() == 0) return null;
    	else return list.get(0);
    }
    
    public static List<TerminalEventAttrLong> getEventAttrLongByEventId(String eventId) {
        return Hibernate.currentSession().createCriteria(TerminalEventAttrLong.class).createAlias("event", "event").add(Restrictions.eq("event.id", eventId)).list();
    }
}
