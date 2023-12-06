package be.arnehus.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

public class Part1 {

	private static final List<Long> seeds = new ArrayList<>(List.of(41218238L, 421491713L, 1255413673L, 350530906L, 944138913L, 251104806L, 481818804L, 233571979L, 2906248740L, 266447632L, 3454130719L, 50644329L, 1920342932L, 127779721L, 2109326496L, 538709762L, 3579244700L, 267233350L, 4173137165L, 60179884L));
	private static final List<String> steps = List.of("seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water", "water-to-light", "light-to-temperature", "temperature-to-humidity", "humidity-to-location");

	public static void main(String[] args) throws IOException {
		for (String step: steps) {
			File input = Paths.get("src\\main\\java\\be\\arnehus\\day5\\steps\\"+ step +".txt").toFile();

			try(BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
				List<Mapping> mapping = fileReader.lines()
						.map(Part1::toMapping)
						.toList();

				seeds.replaceAll(aLong -> findInMapping(mapping, aLong));
			}
		}

		long result = seeds.stream().mapToLong(l -> l).min().getAsLong();
		System.out.println(result);
	}

	private static Mapping toMapping(String input) {
		int range = Integer.parseInt(input.split(" ")[2]);
		long sourceStart = Long.parseLong(input.split(" ")[1]);
		long destinationStart = Long.parseLong(input.split(" ")[0]);

		return new Mapping(sourceStart, destinationStart, range);
	}

	private static long findInMapping(List<Mapping> mapping, long seed) {
		for (Mapping map: mapping) {
			OptionalLong destination = map.getDestination(seed);
			if (destination.isPresent()) return destination.getAsLong();
		}
		return seed;
	}
}
