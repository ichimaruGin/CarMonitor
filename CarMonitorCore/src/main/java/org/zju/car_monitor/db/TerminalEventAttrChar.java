package org.zju.car_monitor.db;

import javax.persistence.*;

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
}
