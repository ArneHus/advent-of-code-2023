package be.arnehus.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    public static void main(String[] args) throws IOException {
        File input = Paths.get("src\\main\\java\\be\\arnehus\\day2\\input.txt").toFile();

        try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
            Integer sum = fileReader.lines()
                    .map(Part2::toGrabbedCubes)
                    .map(Part2::toMaxAmountOfColor)
                    .map(game -> {
                        int number = game.keySet().stream().findFirst().get();
                        return toPower(game.get(number));
                    })
                    .mapToInt(i -> i)
                    .sum();

            System.out.println(sum);
        }
    }

    private static Map<Integer, List<Map<Color, Integer>>> toGrabbedCubes(String input) {
        String[] splitLine = input.split(": ");
        int game = Integer.parseInt(splitLine[0].substring(5));
        String[] grabs = splitLine[1].split("; ");

        List<Map<Color, Integer>> transformedGrabs = new ArrayList<>();
        for (String grab: grabs) {
            Map<Color, Integer> transformedGrab = new HashMap<>();

            for (String grabbedColor: grab.split(", ")) {
                int amount = Integer.parseInt(grabbedColor.split(" ")[0]);
                Color color = Color.valueOf(grabbedColor.split(" ")[1].toUpperCase());
                transformedGrab.put(color, amount);
            }

            transformedGrabs.add(transformedGrab);
        }

        return Map.of(game, transformedGrabs);
    }

    private static Map<Integer, Map<Color, Integer>> toMaxAmountOfColor(Map<Integer, List<Map<Color, Integer>>> input) {
        int game = input.keySet().stream().findFirst().get();

        Map<Color, Integer> maxGrab = new HashMap<>();
        for (Map<Color, Integer> grab: input.get(game)) {
            grab.forEach((color, amount) -> maxGrab.merge(color, amount, Math::max));
        }

        return Map.of(game, maxGrab);
    }

    private static int toPower(Map<Color, Integer> input) {
        return input.values().stream()
                .reduce(1, (v1, v2) -> v1 * v2);
    }
}
