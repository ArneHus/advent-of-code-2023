package be.arnehus.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Part1 {

	private static final String LEFTRIGHT = "LRLRRLLRRLRLRRLLRRLRRLLRRLRRRLRRLRRLRRRLRRLRLRLRLLLRRRLRLLRRLRLRRRLRRLLRRLRRRLRRLRLRLRRRLRLRRRLLRLLRRLRRRLLRRLRLLLRRLRLRLLRRLRLRRRLLRLLRRRLRLLRRRLRRLRRLRRRLRRRLLRLLRRRLRRRLRRLRRRLLRRRLRLRRLLRRLRLRLRRLRRLRLLRRRLRRRLLLRLRLRRRLLRRLRRRLRLRRLLRRLRRLLRLRLRRRLRLRLRLRRRLRLLRRRLRRRLRRLLLRRRR";

	public static void main(String[] args) throws IOException {
		File input = Paths.get("src\\main\\java\\be\\arnehus\\day8\\input.txt").toFile();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
			Map<String, Direction> directions = fileReader.lines()
					.map(s -> s.split(" = "))
					.collect(Collectors.toMap(s -> s[0], s -> toDirection(s[1])));

			int takenSteps = 0;
			int directionPointer = 0;
			String currentElement = "AAA";
			while(!currentElement.equals("ZZZ")) {
				char direction = LEFTRIGHT.charAt(directionPointer++);
				if(directionPointer == LEFTRIGHT.length()) directionPointer = 0;

				Direction directionToTake = directions.get(currentElement);
				if (direction == 'L') currentElement = directionToTake.left();
				else currentElement = directionToTake.right();

				takenSteps++;
			}

			System.out.println(takenSteps);
		}
	}

	private static Direction toDirection(String input) {
		String[] directions = input.substring(1, input.length() - 1).split(", ");
		return new Direction(directions[0], directions[1]);
	}
}
