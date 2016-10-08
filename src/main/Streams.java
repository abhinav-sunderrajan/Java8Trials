package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Streams {

    public static void main(String args[]) {
	List<String> stringCollection = Arrays.asList("peter", "anna", "amike", "axenia", "oxcvb", "lkshsn");
	Optional<String> reduced = stringCollection.stream().sorted().filter((s) -> s.startsWith("a"))
		.reduce((s1, s2) -> s1 + ", " + s2);
	reduced.ifPresent(System.out::println);

	reduced = stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> a.compareTo(b))
		.reduce((s1, s2) -> s1 + ", " + s2);
	reduced.ifPresent(System.out::println);

	boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
	System.out.println(noneStartsWithZ);

	int max = 1000000;
	List<String> values = new ArrayList<>(max);
	for (int i = 0; i < max; i++) {
	    UUID uuid = UUID.randomUUID();
	    values.add(uuid.toString());
	}

	long t0 = System.nanoTime();
	long count = values.stream().sorted().count();
	System.out.println(count);
	long t1 = System.nanoTime();
	long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
	System.out.println(String.format("sequential sort took: %d ms", millis));

	long t2 = System.nanoTime();
	count = values.parallelStream().sorted().count();
	System.out.println(count);
	long t3 = System.nanoTime();
	millis = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
	System.out.println(String.format("parallel sort took: %d ms", millis));

    }

}
