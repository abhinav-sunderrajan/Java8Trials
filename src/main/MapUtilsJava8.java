package main;

import java.util.HashMap;
import java.util.Map;

public class MapUtilsJava8 {

    public static void main(String[] args) {
	Map<Integer, String> map = new HashMap<>();
	for (int i = 0; i < 10; i++)
	    map.putIfAbsent(i, "val" + i);

	map.forEach((id, val) -> System.out.println(val));
	map.computeIfPresent(3, (num, val) -> val + num);
	map.forEach((id, val) -> System.out.println(val));

	System.out.println(map.getOrDefault(42, "key not found"));

    }

}
