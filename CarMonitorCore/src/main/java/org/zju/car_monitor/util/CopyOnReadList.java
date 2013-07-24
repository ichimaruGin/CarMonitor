package org.zju.car_monitor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 10:34 PM
 */
public class CopyOnReadList<T> {

    private final Lock lock = new ReentrantLock() ;
    private List<T> items = new ArrayList<T>() ;
    private Integer counter = 0;

    public void add(T item) {
        lock.lock();
        try{
            items.add(item);
        }finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        int size = 0;
        try{
            size = items.size();
        }
        finally {
            lock.unlock();
        }
        return size;
    }
    public List<T> makeSnapShot(int number) {

        if (getSize() < number) {
            return null;
        }
        return makeSnapShot();
    }
    public List<T> makeSnapShot() {
        List<T> copy = new ArrayList<T>(), ret;
        lock.lock();

        try{
            ret = items;
            items = copy;
        } finally {
            lock.unlock();
        }

        return ret;
    }

}
