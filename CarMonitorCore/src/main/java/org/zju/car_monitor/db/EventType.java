package org.zju.car_monitor.db;

/**
 * @author jiezhen 7/21/13
 */
public enum EventType {
    CATSYS, //terminal config message from terminal to server
    CAT718, //normal message from terminal to server
    CATOBD, //error message from terminal to server
    CARSYS, //set terminal config message from server to terminal
    CAR718 //server requested message from server to terminal
}
