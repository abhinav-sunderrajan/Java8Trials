package main;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ExecutorsJava8 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

	ExecutorService executor = Executors.newSingleThreadExecutor();

	// Notice the lack of anonymous classes in Java 8. It knws what it
	// wants.
	executor.submit(() -> {

	    try {
		String name = Thread.currentThread().getName();
		System.out.println("Foo " + name);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Bar " + name);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	});

	List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3", () -> "task3",
		() -> "task4", () -> "task5", () -> "task6");

	executor.invokeAll(callables).parallelStream().map(future1 -> {
	    try {
		return future1.get();
	    } catch (Exception e) {
		throw new IllegalStateException(e);
	    }
	}).forEach(System.out::println);

	AtomicInteger atomicInt = new AtomicInteger(0);
	IntStream.range(0, 1000).forEach(i -> executor.submit(atomicInt::incrementAndGet));
	stop(executor);
	System.out.println(atomicInt.get());

    }

    private static void stop(ExecutorService executor) throws InterruptedException {
	System.out.println("attempt to shutdown executor");
	executor.shutdown();
	executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
