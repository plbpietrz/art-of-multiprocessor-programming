package rhx.tut.thread._02mutual_exclusion;

/**
 * Generic critical section locking interface.
 * Created by rhinox on 2014-04-13.
 */
public interface Lock {

    void lock();

    void unlock();

}
