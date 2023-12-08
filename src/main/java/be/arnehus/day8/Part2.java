package be.arnehus.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {

	private static final String LEFTRIGHT = "LRLRRLLRRLRLRRLLRRLRRLLRRLRRRLRRLRRLRRRLRRLRLRLRLLLRRRLRLLRRLRLRRRLRRLLRRLRRRLRRLRLRLRRRLRLRRRLLRLLRRLRRRLLRRLRLLLRRLRLRLLRRLRLRRRLLRLLRRRLRLLRRRLRRLRRLRRRLRRRLLRLLRRRLRRRLRRLRRRLLRRRLRLRRLLRRLRLRLRRLRRLRLLRRRLRRRLLLRLRLRRRLLRRLRRRLRLRRLLRRLRRLLRLRLRRRLRLRLRLRRRLRLLRRRLRRRLRRLLLRRRR";

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day8\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			Map<String, Direction> directions = fileReader.lines()
					.map(s -> s.split(" = "))
					.collect(Collectors.toMap(s -> s[0], s -> toDirection(s[1])));

			List<String> elements = new ArrayList<>(directions.keySet().stream().filter(s -> s.endsWith("A")).toList());
			List<Integer> steps = new ArrayList<>();

			for (String element: elements) {
				int takenSteps = 0;
				int directionPointer = 0;
				String currentElement = element;
				while(!currentElement.endsWith("Z")) {
					char direction = LEFTRIGHT.charAt(directionPointer++);
					if(directionPointer == LEFTRIGHT.length()) directionPointer = 0;

					Direction directionToTake = directions.get(currentElement);
					if (direction == 'L') currentElement = directionToTake.left();
					else currentElement = directionToTake.right();

					takenSteps++;
				}
				steps.add(takenSteps);
			}

			System.out.println(lcm(steps));
		}
	}

	private static Direction toDirection(String input) {
		String[] directions = input.substring(1, input.length() - 1).split(", ");
		return new Direction(directions[0], directions[1]);
	}

	private static long gcd(long x, long y) {
		return (y == 0) ? x : gcd(y, x % y);
	}

	public static long lcm(List<Integer> numbers) {
		return numbers.stream().map(i -> (long)i).reduce(1L, (x, y) -> x * (y / gcd(x, y)));
	}
}
