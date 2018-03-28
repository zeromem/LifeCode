package org.zeromem.lifecode.hack.concurrent.peterson;

/**
 * @author zeromem
 * @date 2018/2/24
 * 《多处理器编程的艺术》 Peterson锁
 * lock中while(flag[other] && victim == me)两个判断不能交换
 * flag和victim必须是volatile的，否则会出现死锁。
 * 指令重排会导致单个线程内的指令执行顺序变化，即flag[i]=true和victim=i不按指定顺序执行。
 * 导致另一个线程的条件判断失败。
 *
 * 为变量添加volatile后，会产生内存屏障，因此Peterson锁实践中并不会比其他锁更高效
 */
public class PetersonLock {
    public static int result = 0;

    private boolean[] flag = new boolean[2];
    private int victim;

    public void lock(int id) {
        int i = id;
        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) { } // && 两个子条件位置不能换
    }

    public void unlock(int id) {
        flag[id] = false;

    }

    public static void main(String[] args) throws InterruptedException {
        PetersonLock lock = new PetersonLock();
        Thread t0 = new Thread(new AccTask(lock, 0));
        Thread t1 = new Thread(new AccTask(lock, 1));
        t0.start();
        t1.start();
        t0.join();
        t1.join();

        System.out.println(result);
    }

}

class AccTask implements Runnable {
    PetersonLock lock;
    int id;

    public AccTask(PetersonLock lock, int id) {
        this.lock = lock;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            lock.lock(id);
            PetersonLock.result++;
            System.out.println(id + " --- " + i);
            lock.unlock(id);
        }
    }
}
