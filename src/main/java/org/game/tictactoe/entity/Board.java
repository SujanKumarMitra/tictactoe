package org.game.tictactoe.entity;

import java.util.Set;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-15
 */
public interface Board {
	Cell[][] getBoard();
	
	Coordinate getBounds();

	void setCell(Cell cell, Coordinate coord) throws CoordinateOutOfBoundsException;

	Cell getCell(Coordinate coord) throws CoordinateOutOfBoundsException;
	
	Set<Coordinate> getUnfilledPositions();
}
