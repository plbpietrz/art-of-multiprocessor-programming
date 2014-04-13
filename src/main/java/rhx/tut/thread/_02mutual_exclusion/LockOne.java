package rhx.tut.thread._02mutual_exclusion;

/**
 * Created by rhinox on 2014-04-13.
 */
public class LockOne implements Lock {

    private boolean[] flag;

    @Override
    public void lock() {
        int i = (int) Thread.currentThread().getId(); //lets hope the id is [0,1] or this will not work :)
        int j = 1 - i;
        flag[i] = true;
        while (flag[j]) {
        }
    }

    @Override
    public void unlock() {
        int i = (int) Thread.currentThread().getId();
        flag[i] = false;
    }
}
