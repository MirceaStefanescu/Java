package multithreding.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadFactoryDemo {

	public static void main(String[] args) {

		int threadNumber = 4;
		var es = Executors.newFixedThreadPool(threadNumber, new DefaultThreadFactory());

		// thread factory with Runnable
		IntStream.range(0, threadNumber).forEach(i -> {
			es.submit(() -> System.out.println(Thread.currentThread().getName()));
		});

		// thread factory with Callable
		IntStream.range(0, threadNumber).forEach(i -> {
			Future<Integer> future = es.submit(() -> {
				System.out.println(Thread.currentThread().getName());
				return i;
			});
			try {
				System.out.println("Result: " + future.get());
			} catch (InterruptedException | ExecutionException e) {
			}
		});

		es.shutdown();
	}

}

class DefaultThreadFactory implements ThreadFactory {

	private AtomicInteger counter = new AtomicInteger();

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, "Custom Thread Factory | Thread #" + counter.getAndIncrement());
	}

}