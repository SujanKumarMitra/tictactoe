package org.game.tictactoe.entity;

/**
 * This entity is used to depict a coordinate {@code (index-inclusive)} in the
 * {@link Board}
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 */
public interface Coordinate {
	/**
	 * Returns row coordinate for {@link Board}
	 * 
	 * @return the row index
	 */
	int getRowCoordinate();

	/**
	 * Returns column coordinate for {@link Board}
	 * 
	 * @return the column index
	 */
	int getColumnCoordinate();
}
