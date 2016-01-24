package rhx.tut.thread._01intro;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Dining philosophers deadlock example.
 * Created by rhin0x on 24.01.16.
 */
public class DiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        List<Object> sticks = IntStream
                .range(0, 5)
                .mapToObj(i -> new Object())
                .collect(Collectors.toList());
        List<Philosopher> philosophers = IntStream
                .range(0, 5)
                .mapToObj(i -> new Philosopher(sticks.get(i), sticks.get((i + 1) % sticks.size())))
                .collect(Collectors.toList());

        philosophers
                .stream()
                .forEach(p -> new Thread(p).start());

        Thread.sleep(TimeUnit.MILLISECONDS.toMillis(1000));

        philosophers
                .stream()
                .forEach(Philosopher::stopDine);
    }

    private static class Philosopher<T> implements Runnable {

        private final T leftStick, rightStick;
        private volatile Boolean dine;

        public Philosopher(final T stickOne, final T stickTwo) {
            leftStick = stickOne;
            rightStick = stickTwo;
        }

        public void startDine() throws InterruptedException {
            if (dine == null) {
                dine = true;
                while (dine)
                    synchronized (leftStick) {
                        synchronized (rightStick) {
                            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(100));
                            System.out.println(String.format("Philosopher %d eats", this.hashCode()));
                        }
                    }
                System.out.println(String.format("Philosopher %d finished", this.hashCode()));
            } else {
                System.out.println(String.format("Philosopher %d finished", this.hashCode()));
                return;
            }
        }

        public void stopDine() {
            dine = false;
        }

        @Override
        public void run() {
            try {
                startDine();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
