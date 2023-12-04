package be.arnehus.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day4\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			int score = fileReader.lines()
					.map(str -> str.substring(10))
					.map(Part1::toNumbers)
					.map(Part1::calculateScore)
					.mapToInt(i -> i)
					.sum();

			System.out.println(score);
		}
	}

	private static int calculateScore(List<List<Integer>> numbers) {
		List<Integer> winning = numbers.get(0);
		List<Integer> own = numbers.get(1);
		int score = 0;
		for (int number: own) {
			if (winning.contains(number) && score == 0) {
				score = 1;
			} else if (winning.contains(number)) {
				score *= 2;
			}
		}

		return score;
	}
	
	private static List<List<Integer>> toNumbers(String input) {
		String[] lists = input.split("\\|");
		List<Integer> winning = parseIntegers(lists[0]);
		List<Integer> own = parseIntegers(lists[1]);
		return List.of(winning, own);
	}

	private static List<Integer> parseIntegers(String list) {
		List<Integer> result = new ArrayList<>();
		for (String number: list.split(" ")) {
			if (!number.isEmpty()) {
				result.add(Integer.parseInt(number.trim()));
			}
		}
		return result;
	}
}
