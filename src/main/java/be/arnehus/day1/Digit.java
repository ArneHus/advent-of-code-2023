package be.arnehus.day1;

public enum Digit {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

	private final Integer intValue;

	Digit(int intValue) {
		this.intValue = intValue;
	}

	public Integer getIntValue() {
		return intValue;
	}
}
