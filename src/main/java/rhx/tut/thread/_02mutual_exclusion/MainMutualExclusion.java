package rhx.tut.thread._02mutual_exclusion;

import rhx.tut.thread.common.LocalThread;
import rhx.tut.thread.common.LocalThreadFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by rhinox on 2014-04-13.
 */
public class MainMutualExclusion implements Runnable {

    private final Counter counter;
    private int incrementCount;

    public MainMutualExclusion(final Counter counter, final int incrementCount) {
        this.counter = counter;
        this.incrementCount = incrementCount;
    }

    public static void main(String[] args) throws InterruptedException {
        int incrementCount = 1000;
        Lock lock = new LockOne(2);
        final Counter counter = new Counter(0, lock);
        final LocalThreadFactory factory = new LocalThreadFactory();

        Thread threadOne = factory.newLocalThread(new MainMutualExclusion(counter, incrementCount));
        Thread threadTwo = factory.newLocalThread(new MainMutualExclusion(counter, incrementCount));

        threadOne.start();
        threadTwo.start();

        while (threadOne.isAlive())
            Thread.sleep(TimeUnit.SECONDS.toMillis(1l));
        while (threadTwo.isAlive())
            Thread.sleep(TimeUnit.SECONDS.toMillis(1l));

        System.out.println(counter.getValue());
    }

    @Override
    public void run() {
        int incCount = 0;
        while (0 < incrementCount--) {
            counter.getAndIncrement();
            if (++incCount % 50 == 0) {
                System.out.println("Thread:" + LocalThread.getLocalThreadId(Thread.currentThread()) + ", iteration: " + incCount);
            }
        }
    }
}
