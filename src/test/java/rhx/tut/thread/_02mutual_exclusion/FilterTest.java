package rhx.tut.thread._02mutual_exclusion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rhx.tut.thread.common.LocalThread;
import rhx.tut.thread.common.LocalThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by createthreadex on 31.01.2016.
 */
public class FilterTest {

    public static final int ITERATIONS = 10000;
    private LocalThreadFactory threadFactory;

    @Before
    public void setUp() {
        threadFactory = new LocalThreadFactory();
    }

    @Test
    public void testFilterOn2Threads() throws InterruptedException {
        Filter filter = new Filter(2);
        Counter counter = new Counter();
        CountDownLatch latch = new CountDownLatch(2);
        class TestFilterRunnable implements Runnable {
            @Override
            public void run() {
                int iterations = 0;
                int localThreadId = LocalThread.getLocalThreadId();
                while(ITERATIONS > iterations++) {
                    if (iterations % 1000 == 0)
                        System.out.println(String.format("Thread %d reached %d iterations", localThreadId, iterations));
                    try {
                        filter.lock();
                        counter.incrementCounter();
                    } finally {
                        filter.unlock();
                    }
                }
                latch.countDown();
            }
        }

        threadFactory.newThread(new TestFilterRunnable()).start();
        threadFactory.newThread(new TestFilterRunnable()).start();

        Assert.assertEquals(true, latch.await(1, TimeUnit.SECONDS));
        Assert.assertEquals(20000, counter.getCounter());
    }

    private class Counter {

        private int counter;

        public int incrementCounter() {
            counter += 1;
            return counter;
        }

        public int getCounter() {
            return counter;
        }

        public void resetCounter() {
            counter = 0;
        }

        @Override
        public String toString() {
            return String.format("Counter: %d", counter);
        }
    }
}