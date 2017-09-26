package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/18.
 */
public class WaitSleep {
	public static void main(String[] args) throws InterruptedException {
		interruptByNotify();
	}

	public static void interruptByNotify() throws InterruptedException {
		final Object mon = new Object();
		Runnable r = () -> {
			synchronized (mon) {
				try {
					mon.wait();
					System.out.println(Thread.currentThread().getName() + " wakeup.");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " interrupt.");
				}
			}
		};

		Thread t1 = new Thread(r, "t1");
		Thread t2 = new Thread(r, "t2");
		t1.setDaemon(true);
		t2.setDaemon(true);
		t1.start();
		t2.start();

		Thread.sleep(1000);

		synchronized (mon) {
			mon.notifyAll();
		}

		Thread.sleep(5000);
	}

	public void notifyAndInterrupt() throws InterruptedException {
		final Object mon = new Object();

		Thread t = new Thread(() -> {
			synchronized (mon) {
				try {
					mon.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print("Notify ");
			}
			System.out.println("over.");
		});

		t.start();
		Thread.sleep(1000);

		// 通过内部操作打断t
		t.interrupt();

		// 通过monitor唤醒t
		synchronized (mon) {
			mon.notify();
		}


		// output: 下面两种都有可能出现，为什么??
		// Interrupt over.
		// Notify over.
	}
}
