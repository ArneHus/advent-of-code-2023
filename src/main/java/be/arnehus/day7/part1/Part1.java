package be.arnehus.day7.part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day7\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			List<Hand> sortedHands = fileReader.lines()
					.map(Part1::toHand)
					.sorted()
					.toList();

			long winnings = 0;
			for (int i = 0; i < sortedHands.size(); i++) {
				winnings += sortedHands.get(i).bid() * (i + 1);
			}

			System.out.println(winnings);
		}
	}

	private static Hand toHand(String input) {
		String hand = input.split(" ")[0];
		int bid = Integer.parseInt(input.split(" ")[1]);
		return new Hand(hand, Type.getTypeOfHand(hand), bid);
	}
}
