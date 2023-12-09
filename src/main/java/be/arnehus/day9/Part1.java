package be.arnehus.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        Path input = Paths.get("src\\main\\java\\be\\arnehus\\day9\\input.txt");
        List<String> lines = Files.readAllLines(input);

        long sum = 0;
        for (String line: lines) {
            List<List<Integer>> allRows = getAllRows(line);
            Collections.reverse(allRows);
            allRows.get(0).add(0);

            for (int i = 1; i < allRows.size(); i++) {
                List<Integer> previousRow = allRows.get(i - 1);
                List<Integer> currentRow = allRows.get(i);
                int nextNumber = previousRow.get(previousRow.size()-1) + currentRow.get(currentRow.size()-1);
                currentRow.add(nextNumber);
            }

            List<Integer> lastRow = allRows.get(allRows.size()-1);
            sum += lastRow.get(lastRow.size()-1);
        }

        System.out.println(sum);
    }

    private static List<List<Integer>> getAllRows(String line) {
        List<Integer> numbers = new ArrayList<>(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
        List<List<Integer>> allRows = new ArrayList<>(List.of(numbers));

        List<Integer> nextRow = calculateNextRow(numbers);
        do {
            allRows.add(nextRow);
            nextRow = calculateNextRow(nextRow);
        } while (nextRow.stream().anyMatch(i -> i != 0));
        allRows.add(nextRow);

        return allRows;
    }

    private static List<Integer> calculateNextRow(List<Integer> numbers) {
        List<Integer> nextRow = new ArrayList<>();
        for (int i = 1; i < numbers.size(); i++) {
            nextRow.add(numbers.get(i) - numbers.get(i -1));
        }
        return nextRow;
    }
}
