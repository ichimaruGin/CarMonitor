package org.zju.car_monitor.db;

import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

import javax.persistence.*;
import java.util.List;

/**
 * @author jiezhen 7/20/13
 */
@Entity
@Table(name = "terminal_event_attributes")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class TerminalEventAttribute extends DbObject {

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column( name = "attr_code")
    String attrCode = null;

    @Column(name = "description")
    String description = null;


    public static TerminalEventAttribute findByCode(String attrCode) {
        List list = Hibernate.currentSession().createCriteria(TerminalEventAttribute.class)
                .add(Restrictions.eq("attrCode", attrCode)).list();
        if (list == null || list.size() == 0) throw new RuntimeException("Can't find TerminalEventAttribute by code " + attrCode);
        return (TerminalEventAttribute)list.get(0);
    }
}
