package be.arnehus.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part2 {

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day4\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			List<ScratchCard> originalCards = fileReader.lines().map(Part2::toScratchCard).toList();
			List<ScratchCard> scratchCards = new ArrayList<>(originalCards);

			for(int card = 0; card < scratchCards.size(); card++) {
				int matchingNumbers = getMatchingNumbers(scratchCards.get(card));
				List<ScratchCard> wonCopies = getWonCopies(originalCards, scratchCards.get(card), matchingNumbers);
				scratchCards.addAll(wonCopies);
			}

			System.out.println(scratchCards.size());
		}
	}

	private static List<ScratchCard> getWonCopies(List<ScratchCard> originalCards, ScratchCard winningCard, int matchingNumbers) {
		return originalCards.stream()
				.filter(card -> card.cardNumber() > winningCard.cardNumber() && card.cardNumber() <= winningCard.cardNumber() + matchingNumbers)
				.map(ScratchCard::copy)
				.toList();
	}

	private static int getMatchingNumbers(ScratchCard card) {
		int matchingNumbers = 0;
		for (int number: card.ownNumbers()) {
			if (card.winningNumbers().contains(number)) {
				matchingNumbers++;
			}
		}
		return matchingNumbers;
	}
	
	private static ScratchCard toScratchCard(String input) {
		String[] card = input.split(":");
		int cardNumber = Integer.parseInt(card[0].substring(5).trim());
		String[] lists = card[1].split("\\|");
		List<Integer> winning = parseIntegers(lists[0]);
		List<Integer> own = parseIntegers(lists[1]);

		return new ScratchCard(cardNumber, winning, own);
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
