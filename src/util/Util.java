package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

	public static List<String> readInputFile(String resource) {
		List<String> result = new ArrayList<>(1024);
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream(resource)))) {
			
			String line;
			while ((line = in.readLine()) != null) {
				result.add(line);
			}
			
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		
		return result;
	}
	
	public static List<Integer> readIntegerInputFile(String resource) {
		return readInputFile(resource).stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}
	
}
