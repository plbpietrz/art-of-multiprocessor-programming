package rhx.tut.thread._02mutual_exclusion;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LockTwo implements Lock {

    private int victim;

    @Override
    public void lock() {
        int i = (int) Thread.currentThread().getId();
        victim = i;
        while (victim == i) {
        }
    }

    @Override
    public void unlock() {
    }
}
