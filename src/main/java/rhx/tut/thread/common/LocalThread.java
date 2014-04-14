package rhx.tut.thread.common;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LocalThread extends Thread {

    private int threadId;

    public LocalThread(int threadId, Runnable runnable) {
        super(runnable);
        this.threadId = threadId;
    }

    public static <T extends Thread> int getLocalThreadId(T localThread) {
        if (localThread instanceof LocalThread)
            return ((LocalThread) localThread).getThreadId();
        else
            return (int) localThread.getId();
    }

    @Override
    public void run() {
        System.out.println("LocalThread " + threadId + " started");
        super.run();
    }

    public int getThreadId() {
        return threadId;
    }
}
