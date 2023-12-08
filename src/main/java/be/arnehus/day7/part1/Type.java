package be.arnehus.day7.part1;

import java.util.stream.Collectors;

public enum Type {
	FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD;

	static Type getTypeOfHand(String hand) {
		if (isFiveOfAKind(hand)) return FIVE_OF_A_KIND;
		if (isFourOfAKind(hand)) return FOUR_OF_A_KIND;
		if (isFullHouse(hand)) return FULL_HOUSE;
		if (isThreeOfAKind(hand)) return THREE_OF_A_KIND;
		if (isTwoPair(hand)) return TWO_PAIR;
		if (isOnePair(hand)) return ONE_PAIR;
		return HIGH_CARD;
	}

	private static boolean isFiveOfAKind(String hand) {
		return hand.equals(String.valueOf(hand.charAt(0)).repeat(5));
	}

	private static boolean isFourOfAKind(String hand) {
		int duplicates = 0;
		for (char character: hand.toCharArray()) {
			for (char other: hand.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 4) return true;
			else duplicates = 0;
		}
		return false;
	}

	private static boolean isFullHouse(String hand) {
		int duplicates = 0;
		for (char character: hand.toCharArray()) {
			for (char other: hand.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 3) {
				int other = hand.chars().filter(c -> c != character).findFirst().getAsInt();
				return hand.chars().filter(c -> c != character).allMatch(c -> c == other);
			} else duplicates = 0;
		}
		return false;
	}

	private static boolean isThreeOfAKind(String hand) {
		int duplicates = 0;
		for (char character: hand.toCharArray()) {
			for (char other: hand.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 3) return true;
			else duplicates = 0;
		}
		return false;
	}

	private static boolean isTwoPair(String hand) {
		int duplicates = 0;
		for (char character: hand.toCharArray()) {
			for (char other: hand.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 2) {
				String other = hand.chars().filter(c -> c != character).mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining(""));
				if (isOnePair(other)) return true;
				else duplicates = 0;
			} else duplicates = 0;
		}
		return false;
	}

	private static boolean isOnePair(String cards) {
		int duplicates = 0;
		for (char character: cards.toCharArray()) {
			for (char other: cards.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 2) return true;
			else duplicates = 0;
		}
		return false;
	}
}
