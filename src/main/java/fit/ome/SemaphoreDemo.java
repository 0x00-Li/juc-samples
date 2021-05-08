package fit.ome;

import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * <p>
 * 用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源
 * <p>
 * 场景：
 * <p>
 * 可以用于限流，资源有明确访问数量限制的场景
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws IOException {
        // 默认非公平 NonfairSync
        // 可以通过指定第二个参数，明确是公平还是非公平
        // 公平锁的场景，会优先将锁分配给等待时间最长的（FIFO）原则
        // 非公平的场景，同时通知所有等待线程，争抢锁
//        final Semaphore sph = new Semaphore(1,true);
        final Semaphore sph = new Semaphore(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sph.acquire();
                    System.out.println("name t1:" + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    sph.release();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sph.acquire();
                    System.out.println("name t2:" + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    sph.release();
                }

            }
        });
        t1.start();
        t2.start();
        System.in.read();
    }
}
