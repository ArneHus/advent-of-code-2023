package be.arnehus.day4;

import java.util.List;

public record ScratchCard(int cardNumber, List<Integer> winningNumbers, List<Integer> ownNumbers) {

	ScratchCard copy() {
		return new ScratchCard(cardNumber, winningNumbers, ownNumbers);
	}

	public String toString() {
		return " "+cardNumber;
	}
}
