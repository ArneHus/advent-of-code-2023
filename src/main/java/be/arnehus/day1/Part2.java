package be.arnehus.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Part2 {

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day1\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			Integer sum = fileReader.lines()
					.map(Part2::convertWordsToNumbers)
					.map(Part2::filterDigits)
					.map(digits -> digits.get(0) + digits.get(digits.size() - 1))
					.mapToInt(Integer::valueOf)
					.sum();

			System.out.println(sum);
		}
	}

	private static String convertWordsToNumbers(String input) {
		StringBuilder sb = new StringBuilder(input);

		Optional<Digit> firstContainedDigit = containsDigit(sb.toString());
		while(firstContainedDigit.isPresent()) {
			Digit digit = firstContainedDigit.get();
			int index = sb.indexOf(digit.name().toLowerCase());
			sb.insert(index + 1, digit.getIntValue().toString());

			firstContainedDigit = containsDigit(sb.toString());
		}

		return sb.toString();
	}

	private static Optional<Digit> containsDigit(String input) {
		return Arrays.stream(Digit.values())
				.filter(digit ->  input.contains(digit.name().toLowerCase()))
				.findFirst();
	}

	private static List<String> filterDigits(String input) {
		return input.chars()
				.mapToObj(c -> String.valueOf((char) c))
				.filter("0123456789"::contains)
				.toList();
	}
}
