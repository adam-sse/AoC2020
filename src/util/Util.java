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
	
	public static int[]	toArray(List<Integer> list) {
		int[] array = new int[list.size()];
		int i = 0;
		for (Integer x : list) {
			array[i++] = x;
		}
		return array;
	}
	
	public static List<List<String>> readGroupedLineInputFile(String resource) {
		List<String> lines = readInputFile(resource);
		
		List<List<String>> groups = new ArrayList<>(1024);
		
		List<String> currentGroupLines = new ArrayList<>(10);
		for (String line : lines) {
			if (line.isEmpty()) {
				groups.add(currentGroupLines);
				currentGroupLines = new ArrayList<>(100);
				
			} else {
				currentGroupLines.add(line);
			}
		}
		
		if (!currentGroupLines.isEmpty()) {
			groups.add(currentGroupLines);
		}
		
		return groups;
	}
	
}
