package be.arnehus.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    private static final String symbols = "*-+/$=@#%&";
    private static final String numbers = "01234567889";
    private static final List<Position> surrounding = List.of(
            new Position(-1, -1),
            new Position(-1, 0),
            new Position(-1, 1),
            new Position(0, -1),
            new Position(0, 1),
            new Position(1, -1),
            new Position(1, 0),
            new Position(1, 1)
    );

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src\\main\\java\\be\\arnehus\\day3\\input.txt"));

        List<Integer> allNumbers = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {

            String line = lines.get(lineIndex);
            for (int charIndex = 0; charIndex < line.length(); charIndex++) {

                String character = String.valueOf(line.charAt(charIndex));
                if (symbols.contains(character)) {
                    Set<Integer> adjacentNumbers = findAdjacentNumbers(lines, lineIndex, charIndex);
                    allNumbers.addAll(adjacentNumbers);
                }
            }
        }

        System.out.println(allNumbers.stream().mapToInt(i -> i).sum());
    }

    private static Set<Integer> findAdjacentNumbers(List<String> lines, int currentLine, int currentCharacter) {
        Set<Integer> adjacentNumbers = new HashSet<>();
        for (Position position: surrounding) {
            int lineToCheck = currentLine + position.lineIndex();
            int charToCheck = currentCharacter + position.charIndex();

            String character = String.valueOf(lines.get(lineToCheck).charAt(charToCheck));
            if (numbers.contains(character)) {
                int fullNumber = findFullNumber(lines.get(lineToCheck), charToCheck);
                adjacentNumbers.add(fullNumber);
            }
        }
        return adjacentNumbers;
    }

    private static int findFullNumber(String line, int charachterIndex) {
        int initialCharacterIndex = charachterIndex;
        String initialCharacter = String.valueOf(line.charAt(charachterIndex++));
        String character = String.valueOf(line.charAt(charachterIndex));

        StringBuilder charactersAfter = new StringBuilder();
        while(numbers.contains(character)) {
            charactersAfter.append(character);
            if (charachterIndex != line.length() - 1) {
                character = String.valueOf(line.charAt(++charachterIndex));
            } else {
                break;
            }
        }

        charachterIndex = initialCharacterIndex - 1;
        character = String.valueOf(line.charAt(charachterIndex));
        StringBuilder charactersBefore = new StringBuilder();
        while(numbers.contains(character)) {
            charactersBefore.insert(0, character);
            if (charachterIndex != 0) {
                character = String.valueOf(line.charAt(--charachterIndex));
            } else {
                break;
            }
        }

        return Integer.parseInt(charactersBefore + initialCharacter + charactersAfter);
    }
}
