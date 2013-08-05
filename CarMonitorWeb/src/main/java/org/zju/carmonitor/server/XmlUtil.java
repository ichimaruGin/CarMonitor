package org.zju.carmonitor.server;

/**
 * @author jiezhen 7/24/13
 */
public class XmlUtil {

    public static String pair(String name, String value) {
        return (new StringBuilder().append("<").append(name).append(">").append(value).append("</").append(name).append(">").toString());
    }
    
    public static String pair(String name, long value) {
    	return (new StringBuilder().append("<").append(name).append(">").append(value).append("</").append(name).append(">").toString());
    }
}
