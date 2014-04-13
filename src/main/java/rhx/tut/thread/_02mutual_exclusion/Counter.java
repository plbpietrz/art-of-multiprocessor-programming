package rhx.tut.thread._02mutual_exclusion;

/**
 * Created by rhinox on 2014-04-13.
 */
public class Counter {

    private int value;
    private Lock lock;

    public Counter(final int c, Lock lock) {
        this.value = c;
        this.lock = lock;
    }

    public int getAndIncrement() {
        lock.lock();
        try {
            int tmp = value;
            value = tmp + 1;
            return tmp;
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        return value;
    }
}
