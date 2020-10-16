package org.game.tictactoe.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * {@link Coordinate} is used to depict a particular position in the
 * {@link Board} <br>
 * Since, creating a new instance every time is costly and we might have a big
 * pool of unused {@link Coordinate} objects; <br>
 * a cache is used.<br>
 * This cache follows Singleton pattern and returns a {@link Coordinate} object,
 * from the inbuilt map.
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class CoordinateCache {

	/**
	 * Holds instances of {@link Coordinate}
	 */
	private Map<Integer, Coordinate> cache;

	/**
	 * Holds singleton instance
	 */
	private static CoordinateCache instance;

	/**
	 * Logger instance
	 */
	private Logger logger;

	/**
	 * Constructs a cache
	 */
	private CoordinateCache() {
		cache = new HashMap<>();
		logger = Logger.getLogger(getClass().getName());
		logger.info("instantiated");
	}

	/**
	 * Returns an instance of this class
	 * 
	 * @return the instance
	 */
	public static CoordinateCache getInstance() {
		if (instance == null)
			synchronized (CoordinateCache.class) {
				if (instance == null) {
					instance = new CoordinateCache();
				}
			}
		return instance;
	}

	/**
	 * Returns an instance of {@link Coordinate} from the pool
	 * 
	 * @param row the row coordinate
	 * @param col the column coordinate
	 * @return an instance
	 */
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

	/**
	 * Checks whether the instance return by the {@link #cache} equals to the params
	 * provided in {@link #get(int, int)} <br>
	 * Used to check whether the hash function is correctly working or not
	 * 
	 * @param instance instance returned by {@link #cache}
	 * @param row      the row arg
	 * @param col      the row arg
	 * @return if matches then same instance; else creates new instance with
	 *         supplies args and returns it
	 */
	private Coordinate assertEquals(Coordinate instance, int row, int col) {
		if (notMatches(row, col, instance)) {
			StringBuilder sb = new StringBuilder();
			sb.append("Cache not working properly. ");
			sb.append(String.format("Given {%d-%d} ", row, col));
			sb.append(String.format("Received {%d-%d} ", instance.getRowCoordinate(), instance.getColumnCoordinate()));
			logger.warning(sb.toString());
			return new CoordinateImpl(row, col);
		} else {
			return instance;
		}
	}

	/**
	 * Helper method to check whether the args supplied are matching with the instance or not
	 * 
	 * @param row      the row arg
	 * @param col      the column arg
	 * @param instance the instance to check
	 * @return if matches then {@code true}, else {@code false}
	 */
	private boolean notMatches(int row, int col, Coordinate instance) {
		int rowCoord = instance.getRowCoordinate();
		int columnCoord = instance.getColumnCoordinate();
		return rowCoord != row || columnCoord != col;
	}

}
