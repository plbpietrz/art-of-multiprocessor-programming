package rhx.tut.thread.common;

import java.util.concurrent.ThreadFactory;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LocalThreadFactory implements ThreadFactory {

    private int threadId = 0;

    public LocalThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        return newLocalThread(r);
    }

    public LocalThread newLocalThread(final Runnable runnable) {
        return new LocalThread(threadId++, runnable);
    }
}
