package org.zju.car_monitor.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jiezhen 7/21/13
 */
@Entity
@DiscriminatorValue("CAT718")
public class CAT718TerminalEvent extends TerminalEvent{

}
