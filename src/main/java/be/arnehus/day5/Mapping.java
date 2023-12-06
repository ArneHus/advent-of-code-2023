package be.arnehus.day5;

import java.util.OptionalLong;

public record Mapping(long source, long destination, int range) {

	OptionalLong getDestination(long seed) {
		if (seed >= source && seed < source + range) {
			return OptionalLong.of(seed + (destination - source));
		}
		return OptionalLong.empty();
	}
}
