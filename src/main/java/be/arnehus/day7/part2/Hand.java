package be.arnehus.day7.part2;

import java.util.List;

public record Hand(String hand, Type type, long bid) implements Comparable<Hand>{
	private static final List<String> cardOrder = List.of("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J");

	@Override
	public int compareTo(Hand other) {
		if (type == other.type) {
			for (int i = 0; i < 5; i++) {
				if (hand.charAt(i) != other.hand.charAt(i)) {
					return cardOrder.indexOf(String.valueOf(other.hand.charAt(i))) - cardOrder.indexOf(String.valueOf(hand.charAt(i)));
				}
			}
		}
		return other.type.ordinal() - type.ordinal();
	}
}
