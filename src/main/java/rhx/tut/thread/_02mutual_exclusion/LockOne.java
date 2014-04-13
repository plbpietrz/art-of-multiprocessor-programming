package rhx.tut.thread._02mutual_exclusion;

import rhx.tut.thread.common.LocalThread;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LockOne implements Lock {

    private boolean[] flag;

    @Override
    public void lock() {
        int i = LocalThread.getLocalThreadId(Thread.currentThread()); //lets hope the id is [0,1] or this will not work :)
        int j = 1 - i;
        flag[i] = true;
        while (flag[j]) {
        }
    }

    @Override
    public void unlock() {
        int i = LocalThread.getLocalThreadId(Thread.currentThread());
        flag[i] = false;
    }
}
