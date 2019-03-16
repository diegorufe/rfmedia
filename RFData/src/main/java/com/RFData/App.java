package com.RFData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

	public static void testFor() {

		List<Integer> listIntegers = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			listIntegers.add(i);
		}
		long timeStart = new Date().getTime();
		for (Integer integer : listIntegers) {
			integer.toString();
		}
		long timeEnd = new Date().getTime();
		System.out.println("Time: " + (timeEnd - timeStart));
	}

	public static void testForLamnda() {

		List<Integer> listIntegers = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			listIntegers.add(i);
		}
		long timeStart = new Date().getTime();
		listIntegers.forEach(v -> {
			v.toString();
		});
		long timeEnd = new Date().getTime();
		System.out.println("Time: " + (timeEnd - timeStart));
	}

	public static void main(String[] args) {
		var title = "Hello World!";
		System.out.println(title);
		testFor();
		testForLamnda();
	}
}
