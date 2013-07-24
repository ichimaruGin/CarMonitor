package org.zju.carmonitor.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.zju.carmonitor.client.CarMonitorUIService;

public class CarMonitorUIServiceImpl extends RemoteServiceServlet implements CarMonitorUIService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}