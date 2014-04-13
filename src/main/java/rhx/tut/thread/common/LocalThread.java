package rhx.tut.thread.common;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LocalThread extends Thread {

    private int threadId;

    public LocalThread(int threadId) {
        this.threadId = threadId;
    }

    public static <T extends Thread> int getLocalThreadId(T localThread) {
        if (localThread instanceof LocalThread)
            return ((LocalThread) localThread).getThreadId();
        else
            return (int) localThread.getId();
    }

    public int getThreadId() {
        return threadId;
    }
}
