package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
	
	@FunctionalInterface
	interface Converter<F, T> {
	    T convert(F from);
	    default double sqrt(int a) {
	        return Math.sqrt(a);
	    }
	}
	
	public static void main(String args[]){
		
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia","anna");
		Collections.sort(names, (a,b)->a.compareTo(b));
		System.out.println(names);
		Converter<String, Integer> convertor=from->Integer.valueOf(from);
		Integer i=convertor.convert("12345");
		System.out.println("converted int:"+i);
		System.out.println("square root from functional interface:"+convertor.sqrt(i));
		
	}

}
