package org.zju.car_monitor.db;

import javax.persistence.*;

/**
 * @author jiezhen 7/20/13
 */
@Entity
@Table(name = "terminal_events")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class TerminalEvent extends DbObject{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    Terminal terminal = null;

    @Column(name = "process_flag")
    String processFlag = null;

    @Column(name = "process_message")
    String processMessage = null;

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public String getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }

    public String getProcessMessage() {
        return processMessage;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }

}
