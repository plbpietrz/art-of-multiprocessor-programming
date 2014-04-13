package rhx.tut.thread._02mutual_exclusion;

import rhx.tut.thread.common.LocalThread;

/**
 * Created by rhinox on 2014-04-13.
 */
public class Filter implements Lock {

    private final int[] level;
    private final int[] victim;

    public Filter(final int n) {
        level = new int[n];
        victim = new int[n];

        for (int i = 0; i < n; ++i) {
            level[i] = 0;
        }
    }

    @Override
    public void lock() {
        int me = LocalThread.getLocalThreadId(Thread.currentThread());
        for (int i = 0; i < level.length; ++i) {
            level[me] = i;
            victim[i] = me;
            // there exists a k that is different from me
            for (int k = 0; k < level.length; ++k) {
                while ((k != me) && (level[k] >= i && victim[i] == me)) {
                }
                k = 0;
            }
        }
    }

    @Override
    public void unlock() {
        int me = LocalThread.getLocalThreadId(Thread.currentThread());
        level[me] = 0;
    }
}
