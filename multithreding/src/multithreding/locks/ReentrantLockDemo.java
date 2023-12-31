package multithreding.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockDemo {

	private static int counter;
	private Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {

		var thisInstance = new ReentrantLockDemo();
		var es = Executors.newFixedThreadPool(4);
//		IntStream.range(0, 10000).forEach((i) -> es.execute(thisInstance::incrementWithLock));
		IntStream.range(0, 10000).forEach((i) -> es.execute(thisInstance::increment));

		terminateExecutorService(es);

		System.out.println("Counter: " + counter);
	}

	private static void terminateExecutorService(ExecutorService es) throws InterruptedException {
		es.shutdown();
		es.awaitTermination(3, TimeUnit.SECONDS);
		es.shutdownNow();
	}

	public void increment() {
		synchronized (this) {
			counter++;
		}
	}

	public void incrementWithLock() {
		try {
			lock.lock();
			counter++;

		} finally {
			lock.unlock();
		}
	}
}
