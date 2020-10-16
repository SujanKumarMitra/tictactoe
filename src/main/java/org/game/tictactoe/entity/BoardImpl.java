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
		initCache();
		this.board = createBoard(3);
		fillBoard();
		this.bound = cache.get(3, 3);
	}

	public BoardImpl(Cell[][] cells) {
		assertEqualsRowsAndColumns(cells);
		initCache();
		this.board = cells;
		this.bound = cache.get(cells.length, cells[0].length);
	}

	public BoardImpl(Board board) {
		initCache();
		this.board = deepCloneCells(board.getBoard());
		Coordinate bounds = board.getBounds();
		this.bound = cache.get(bounds.getRowCoordinate(), bounds.getColumnCoordinate());
	}

	public BoardImpl(int boardSize) {
		initCache();
		this.board = createBoard(boardSize);
		this.bound = cache.get(boardSize, boardSize);
	}

	private Cell[][] createBoard(int size) {
		return new Cell[size][size];
	}

	private void initCache() {
		this.cache = CoordinateCache.getInstance();
	}

	private Cell[][] deepCloneCells(Cell[][] cells) {
		int rows = cells.length;
		int cols = cells[0].length;
		Cell[][] clones = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				clones[i][j] = cells[i][j];
			}
		}
		return clones;
	}

	private void assertEqualsRowsAndColumns(Cell[][] cells) {
		int rows = cells.length;
		for (int i = 0; i < rows; i++) {
			if (cells[i].length != rows) {
				throw new IllegalArgumentException("board should be square");
			}
		}
	}

	private void fillBoard() {
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
