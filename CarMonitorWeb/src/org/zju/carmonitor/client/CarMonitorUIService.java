package org.zju.carmonitor.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CarMonitorUIService")
public interface CarMonitorUIService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use CarMonitorUIService.App.getInstance() to access static instance of CarMonitorUIServiceAsync
     */
    public static class App {
        private static CarMonitorUIServiceAsync ourInstance = GWT.create(CarMonitorUIService.class);

        public static synchronized CarMonitorUIServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
