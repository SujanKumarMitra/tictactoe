package org.game.tictactoe.entity;

import java.util.Set;

/**
 * This entity represents the playing board, and maintains the state of the
 * board
 * 
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 */
public interface Board {
	/**
	 * Returns the 2-d {@code array} representation of board
	 * 
	 * @return the board
	 * @see Cell
	 */
	Cell[][] getBoard();

	/**
	 * Returns the boundary limits {@code (exclusive index)} of the board
	 * 
	 * @return the bounds
	 */
	Coordinate getBounds();

	/**
	 * Sets a particular cell value in the board, the position is depicted by the
	 * {@code coord}
	 * 
	 * @param cell  the cell
	 * @param coord the board coordinates
	 * @throws CoordinateOutOfBoundsException if {@code coords} are out of bounds of
	 *                                        board
	 */
	void setCell(Cell cell, Coordinate coord) throws CoordinateOutOfBoundsException;

	/**
	 * Returns the particular cell value in the board, the position is depicted by
	 * the {@code coord}
	 * 
	 * @param coord the board coordinates
	 * @return the cell
	 * @throws CoordinateOutOfBoundsException if {@code coords} are out of bounds of
	 *                                        board
	 */
	Cell getCell(Coordinate coord) throws CoordinateOutOfBoundsException;

	/**
	 * Returns a set of {@link Coordinate}s of {@link Cell#EMPTY} positions
	 * currently in the board.
	 * 
	 * @return the set
	 */
	Set<Coordinate> getUnfilledPositions();
}
