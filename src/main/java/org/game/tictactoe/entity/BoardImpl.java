package org.game.tictactoe.entity;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-16
 */
public class BoardImpl implements Board {

	private Cell[][] board;
	private Coordinate bound;
	private CoordinateCache cache;

	public BoardImpl() {
		board = new Cell[3][3];
		cache = CoordinateCache.getInstance();
		bound = cache.get(3, 3);
		initBoard();
	}

	private void initBoard() {
		for (Cell[] row : board) {
			Arrays.fill(row, Cell.EMPTY);
		}
	}

	@Override
	public Cell[][] getBoard() {
		return board;
	}

	@Override
	public Coordinate getBounds() {
		return bound;
	}

	@Override
	public void setCell(Cell cell, Coordinate coord) throws CoordinateOutOfBoundsException {
		try {
			board[coord.getRowCoordinate()][coord.getColumnCoordinate()] = cell;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CoordinateOutOfBoundsException(e.getMessage());
		}
	}

	@Override
	public Cell getCell(Coordinate coord) throws CoordinateOutOfBoundsException {
		try {
			return board[coord.getRowCoordinate()][coord.getColumnCoordinate()];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CoordinateOutOfBoundsException(e.getMessage());
		}
	}

	@Override
	public Set<Coordinate> getUnfilledPositions() {
		Set<Coordinate> unfilledPositions = getSet();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == Cell.EMPTY) {
					Coordinate coord = cache.get(row, col);
					unfilledPositions.add(coord);
				}
			}
		}
		return unfilledPositions;
	}

	private Set<Coordinate> getSet() {
//		return new HashSet<>();
		return new LinkedHashSet<>();
	}

}
