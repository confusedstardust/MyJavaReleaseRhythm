import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * The interface in Java 9 version supports private methods for sharing code
 * within the interface
 *
 */
public class Java9SampleIndex {
	public interface ISampleInterface {

		private void log(String message) {
			System.out.println(message);
		}

		default void defaultMethod() {
			log("call private method");
		}

	}

	public static void main(String[] args) {

	}

	/**
	 * This sample improved the StreamAPI, imported new methods:takeWhile,iterate
	 * .etc
	 * 
	 * TakeWhile method to take the elements which the number less than 5
	 * 
	 * DropWhile method to drop the elements which the number less than 5
	 * 
	 * For the old version, we need to use limit method to get define the length
	 */
	public static void StreamAPIImproveFeature() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		numbers.stream()
				.takeWhile(n -> n < 5)
				.forEach(System.out::println);
		// output: 1 2 3 4

		numbers.stream()
				.dropWhile(n -> n < 5)
				.forEach(System.out::println);
		// output: 5 6 7 8 9

		Stream<Integer> newIterate = Stream.iterate(0, n -> n < 20, n -> n + 2);
		newIterate.forEach(System.out::println);
		// output: 0 2 4 6 8 10 12 14 16 18

		Stream<String> emptyStream = Stream.ofNullable(null);
		emptyStream.forEach(System.out::println);
		// no output

	}

	/**
	 * OptionalClass got improved in java9 'ifPresentOrElse', 'or' methods added
	 */
	public static void OptionalClassImproveFeature() {
		Optional<String> name = Optional.ofNullable(null);
		name.ifPresentOrElse(value -> System.out.println(value), () -> System.out.println("element is null"));
		// output:element is null

		Optional<String> fallback = name.or(() -> Optional.of("is null"));
		System.out.println(fallback.get());
		// output: is null
	}

	/**
	 * This sample will show the create immutable collection difference between the
	 * Java 8 and Java 9
	 * Arrays.asList created list is fixed in size, but the values of the elements
	 * can be modified,not completely immutable
	 * List.of created list,once created, elements cannot be added, deleted or
	 * modified
	 */
	public static void CollectionMethod() {

		List<String> java8SampleList = Arrays.asList("a", "b", "c");

		List<String> java9SampleList = List.of("a", "b", "c");
	}

	/**
	 * This sample will show how to create a get request and get response in Java 8
	 * version
	 */
	public static void HttpJava8Sample() {
		try {
			URL url = new URL("http://example.com");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// read response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * In Java 9 version, HTTP client API was imported, it supports HTTP/2 and
	 * simplifies the code for handling HTTP requests and asynchronous operations
	 * are supported
	 * CompletableFuture class added some methods in this version, such as
	 * newIncompleteFuture,completeAsync .etc
	 */
	public static CompletableFuture<HttpResponse<String>> HttpJava9Sample() {
		try {
			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://example.com"))
					.GET()
					.build();
			// get response
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// get response by async way
			CompletableFuture<HttpResponse<String>> asyncResponse = client.sendAsync(request,
					HttpResponse.BodyHandlers.ofString());
			return asyncResponse;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

}
