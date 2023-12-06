package be.arnehus.day6;

import java.util.Map;

public class Part1 {

	private static final Map<Integer, Integer> races = Map.ofEntries(
			Map.entry(62, 553),
			Map.entry(64, 1010),
			Map.entry(91, 1473),
			Map.entry(90, 1074)
	);

	public static void main(String[] args) {
		long result = 1;

		for (int time: races.keySet()) {
			int distance = races.get(time);
			int winningsPossibilities = 0;

			for (int i = 0; i < time; i++) {
				int travelledDistance = i * (time - i);
				if (travelledDistance > distance) winningsPossibilities++;
			}

			result *= winningsPossibilities;
		}

		System.out.println(result);
	}
}
