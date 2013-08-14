package org.zju.car_monitor.db;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	
	@Column(name = "raw_message") 
	String rawMessage = null;

    public String getRawMessage() {
		return rawMessage;
	}

	public void setRawMessage(String rawMessage) {
		this.rawMessage = rawMessage;
	}

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
