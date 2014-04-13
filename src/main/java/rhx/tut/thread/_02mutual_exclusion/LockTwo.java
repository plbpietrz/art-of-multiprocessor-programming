package rhx.tut.thread._02mutual_exclusion;

import rhx.tut.thread.common.LocalThread;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LockTwo implements Lock {

    private int victim;

    @Override
    public void lock() {
        int i = LocalThread.getLocalThreadId(Thread.currentThread());
        victim = i;
        while (victim == i) {
        }
    }

    @Override
    public void unlock() {
    }
}
