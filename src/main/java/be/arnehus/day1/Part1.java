package be.arnehus.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day1\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			Integer sum = fileReader.lines()
					.map(Part1::filterDigits)
					.map(digits -> digits.get(0) + digits.get(digits.size() - 1))
					.map(Integer::valueOf)
					.mapToInt(i -> i)
					.sum();

			System.out.println(sum);
		}
	}

	private static List<String> filterDigits(String input) {
		return input.chars()
				.mapToObj(c -> String.valueOf((char) c))
				.filter("0123456789"::contains)
				.toList();
	}
}
