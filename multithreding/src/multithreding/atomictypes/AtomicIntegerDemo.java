package multithreding.atomictypes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

	private static AtomicInteger counter = new AtomicInteger();

	public static void main(String[] args) throws InterruptedException {
		List<Thread> allThreads = new ArrayList<Thread>();
		int numOfIncrements = 10000;

		for (int i = 0; i < numOfIncrements; i++) {
			var t = new Thread(AtomicIntegerDemo::increment);
			allThreads.add(t);
			t.start();
		}

		for (Thread thread : allThreads) {
			thread.join();
		}

		System.out.println(counter);
	}

	public static void increment() {
		counter.incrementAndGet();
	}
}
