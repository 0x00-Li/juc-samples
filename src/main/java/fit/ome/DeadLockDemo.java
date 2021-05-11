package fit.ome;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void run() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " get lock1");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "  get lock2");
                }

            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " get lock2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                }

            }
        }, "t2");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws IOException {
        new DeadLockDemo().run();
        System.in.read();
    }
}
