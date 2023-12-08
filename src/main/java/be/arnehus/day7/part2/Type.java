package be.arnehus.day7.part2;

import java.util.stream.Collectors;

public enum Type {
	FIVE_OF_A_KIND(new int[]{5}), FOUR_OF_A_KIND(new int[]{4}), FULL_HOUSE(new int[]{3, 2}), THREE_OF_A_KIND(new int[]{3}), TWO_PAIR(new int[]{2, 2}), ONE_PAIR(new int[]{2}), HIGH_CARD(new int[]{0});

	private final int[] amountOfCards;

	Type(int[] amountOfCards) {
		this.amountOfCards = amountOfCards;
	}

	static Type getTypeOfHand(String hand) {
		if (isOfSort(hand, FIVE_OF_A_KIND)) return FIVE_OF_A_KIND;
		if (isOfSort(hand, FOUR_OF_A_KIND)) return FOUR_OF_A_KIND;
		if (isFullHouse(hand)) return FULL_HOUSE;
		if (isOfSort(hand, THREE_OF_A_KIND)) return THREE_OF_A_KIND;
		if (isTwoPair(hand)) return TWO_PAIR;
		if (isOfSort(hand, ONE_PAIR)) return ONE_PAIR;
		return HIGH_CARD;
	}

	private static boolean isOfSort(String hand, Type type) {
		String handWithoutJ = hand.replace("J", "");
		int numberOfJ = hand.length() - handWithoutJ.length();
		int duplicates = numberOfJ;
		if (duplicates == type.amountOfCards[0]) return true;

		for (char character: handWithoutJ.toCharArray()) {
			for (char other: handWithoutJ.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == type.amountOfCards[0]) return true;
			else duplicates = numberOfJ;
		}
		return false;
	}

	private static boolean isFullHouse(String hand) {
		String handWithoutJ = hand.replace("J", "");
		int numberOfJ = hand.length() - handWithoutJ.length();
		int duplicates = numberOfJ;
		for (char character: handWithoutJ.toCharArray()) {
			for (char other: handWithoutJ.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 3) {
				int other = handWithoutJ.chars().filter(c -> c != character).findFirst().getAsInt();
				return handWithoutJ.chars().filter(c -> c != character).allMatch(c -> c == other);
			}
			else duplicates = numberOfJ;
		}
		return false;
	}

	private static boolean isTwoPair(String hand) {
		String handWithoutJ = hand.replace("J", "");
		int numberOfJ = hand.length() - handWithoutJ.length();
		int duplicates = numberOfJ;
		for (char character: handWithoutJ.toCharArray()) {
			for (char other: handWithoutJ.toCharArray()) {
				if (character == other) duplicates++;
			}
			if (duplicates == 2) {
				String other = handWithoutJ.chars().filter(c -> c != character).mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining(""));
				if (isOfSort(other, ONE_PAIR)) return true;
				else duplicates = numberOfJ;
			}
			else duplicates = numberOfJ;
		}
		return false;
	}
}
