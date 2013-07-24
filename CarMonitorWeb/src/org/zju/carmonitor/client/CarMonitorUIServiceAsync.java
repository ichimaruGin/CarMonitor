package org.zju.carmonitor.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarMonitorUIServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
