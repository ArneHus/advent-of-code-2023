package be.arnehus.day6;

public class Part2 {

	public static void main(String[] args) {
		long time = 62649190;
		long distance = 553101014731074L;
		int winningsPossibilities = 0;

		for (long i = 0; i < time; i++) {
			long travelledDistance = i * (time - i);
			if (travelledDistance > distance) winningsPossibilities++;
		}

		System.out.println(winningsPossibilities);
	}
}
