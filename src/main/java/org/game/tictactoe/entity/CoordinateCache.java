package org.game.tictactoe.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class CoordinateCache {

	private Map<Integer, Coordinate> cache;

	private static CoordinateCache instance;

	private CoordinateCache() {
		cache = new HashMap<>();
	}

	public static CoordinateCache getInstance() {
		if (instance == null)
			synchronized (CoordinateCache.class) {
				if (instance == null)
					instance = new CoordinateCache();
			}
		return instance;
	}

	public Coordinate get(int row, int col) {
		final int hashCode = Objects.hash(row, col);
		if (cache.containsKey(hashCode)) {
			Coordinate instance = cache.get(hashCode);
			instance = assertEquals(instance, row, col);
			return instance;
		} else {
			Coordinate instance = new CoordinateImpl(row, col);
			cache.put(hashCode, instance);
			return instance;
		}

	}

	private Coordinate assertEquals(Coordinate instance, int row, int col) {
		int rowCoord = instance.getRowCoordinate();
		int columnCoord = instance.getColumnCoordinate();
		if (rowCoord != row || columnCoord != col) {
			StringBuilder sb = new StringBuilder();
			sb.append("Cache not working properly. ");
			sb.append(String.format("Given {%d-%d} ", row, col));
			sb.append(String.format("Received {%d-%d} ", rowCoord, columnCoord));
			System.err.println(sb);
			return new CoordinateImpl(row, col);
		} else {
			return instance;
		}
	}

}
