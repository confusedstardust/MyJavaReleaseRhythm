package Java8Feature;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class Java8SampleIndex {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// try any feature in here
	}

	/**
	 * This sample will using StreamAPI and Lambda to filter a list
	 * 
	 * First demo to get the names whose length is greater than or equal to 5
	 * 
	 * Second demo to get Even numbers up to 20, this method will be improved in
	 * Java9; In current version if without limit method ,will appear infinite
	 * streams, which may cause memory problem
	 *
	 * Third demo to sort the list according to element content
	 */
	public static void StreamAPIAndLambdaFeature() {
		List<String> names = Arrays.asList("Eva", "Alice", "Bob", "Charlie", "Dave");
		names.stream()
				.filter(name -> name.length() >= 5)
				.forEach(System.out::println);
		// output: Charlie Alice

		Stream<Integer> iterate = Stream.iterate(0, n -> n + 2).limit(10);
		// output: 0 2 4 6 8 10 12 14 16 18

		Collections.sort(names, (a, b) -> a.compareTo(b));
		// output: [Alice, Bob, Charlie, Dave, Eva]
		System.out.println(names);
	}

	/**
	 * This sample will using dateAPI to create date and get date
	 */
	public static void DateAPIFeature() {
		// get current date time
		LocalDate currentDate = LocalDate.now();
		System.out.println("Current Date: " + currentDate);

		// create date output: Birthday: 1990-01-01
		LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 1);
		System.out.println("Birthday: " + birthday);
	}

	/**
	 * This sample will handle the value could be null ,to avoid null pointer
	 * exception output:Name is not available
	 */
	public static void OptionalClassFeature() {
		Optional<String> name = Optional.ofNullable(null);
		if (name.isPresent()) {
			System.out.println("Name: " + name.get());
		} else {
			System.out.println("Name is not available");
		}
	}

	public static void CollectionsMethod() {
		List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "Dave", "Eva"));
		names.forEach(System.out::println);
		// output:[Alice, Bob, Charlie, Dave, Eva]
		names.removeIf(name -> name.startsWith("A"));
		// output:Alice
		names.replaceAll(String::toUpperCase);
		// output:[ALICE, BOB, CHARILE, DAVE, EVA]
	}
}

/**
 * 
 * Using this interface,you can overwrite the method in implementation class
 */
interface IDefaultFeature {
	default void test() {
		System.out.println("This is default method!");
	}
}
